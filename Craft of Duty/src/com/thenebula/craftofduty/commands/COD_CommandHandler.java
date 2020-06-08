package com.thenebula.craftofduty.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COD_CommandHandler implements CommandExecutor
{
    private HashMap<String, SubCommand> commands;
    
    public COD_CommandHandler()
    {
        commands = new HashMap<String, SubCommand>();
        loadCommands();
    }

    private void loadCommands()
    {
    	//commands.put("giveall", new giveall());
    	commands.put("stats", new CMD_COD_Stats());
    	commands.put("vote", new CMD_COD_Vote());
    }

    public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args){
        String cmd = cmd1.getName();

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        
        if(cmd.equalsIgnoreCase("cod")){ 
            if(args == null || args.length < 1){
                player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD +"CRAFT of DUTY");
                player.sendMessage(ChatColor.WHITE +"Type /cod help for help" );

                return true;
            }
            if(args[0].equalsIgnoreCase("help")){
                help(player);
                return true;
            }
            String sub = args[0];

            Vector<String> l  = new Vector<String>();
            l.addAll(Arrays.asList(args));
            l.remove(0);
            args = (String[]) l.toArray(new String[0]);
            try{
            commands.get(sub).onCommand(player, args);
            }catch(NullPointerException ne){
            	player.sendMessage("Command not found! " + ChatColor.BOLD + "Try /cod help");
            }catch(Exception e){e.printStackTrace(); player.sendMessage(ChatColor.BLUE +"Type /cod help for help" );
            }
            return true;
        }
        return false;
    }
    
    public void help(Player p){
        for(SubCommand v: commands.values()){
        	v.help(p);
        }
    }
}
