package com.thenebula.craftofduty.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUNS_CommandHandler implements CommandExecutor
{
    private HashMap<String, SubCommand> commands;
    
    public GUNS_CommandHandler()
    {
        commands = new HashMap<String, SubCommand>();
        loadCommands();
    }

    private void loadCommands()
    {
    	//commands.put("giveall", new giveall());
    	commands.put("give", new CMD_GUNS_Give());
    }

    public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args){
        String cmd = cmd1.getName();

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        
        if(cmd.equalsIgnoreCase("guns")){ 
            if(args == null || args.length < 1){
                player.sendMessage(ChatColor.AQUA +""+ ChatColor.BOLD +"Craft Of Duty Guns");
                player.sendMessage(ChatColor.WHITE +"Type /guns help for help" );

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
            	player.sendMessage("Command not found! " + ChatColor.BOLD + "Try /guns help");
            }catch(Exception e){e.printStackTrace(); player.sendMessage(ChatColor.BLUE +"Type /guns help for help" );
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
