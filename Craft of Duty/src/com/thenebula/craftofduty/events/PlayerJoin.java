package com.thenebula.craftofduty.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mcsg.double0negative.tabapi.TabAPI;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.VoteManager;
import com.thenebula.craftofduty.enums.GAMEMODE;

public class PlayerJoin implements Listener {
	public static CraftofDuty plugin;
	
	public static boolean voteStarted = false;
	
	public static void Init(CraftofDuty p) {
		plugin = p;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		GAMEMODE gm = CraftofDuty.gmm.getGameMode();
		
		if(!CraftofDuty.xpm.userExists(player.getName())) {
			CraftofDuty.xpm.registerPlayer(player);
		}
		
		if(gm == GAMEMODE.FreeForAll || gm == GAMEMODE.TeamDeathMatch) {
			player.sendMessage(ChatColor.AQUA + "Welcome To Craft Of Duty. " + ChatColor.BLUE + gm);
			player.teleport(player.getWorld().getSpawnLocation());
			
			VoteManager.Players.add(player); // Add player to game.
			
			if (VoteManager.voting == false) {
				if (CraftofDuty.gmm.getNeededPlayersForVote(gm) <= VoteManager.Players.size()) {
					VoteManager.startVote(gm);
				} else {
					Bukkit.broadcastMessage(ChatColor.AQUA + "[COD] " + ChatColor.WHITE + (CraftofDuty.gmm.getNeededPlayersForVote(gm) - VoteManager.Players.size()) + " more players needed to start a map vote");
				}
			} else {
				player.setScoreboard(VoteManager.voteBoard);
				TabAPI.setPriority(plugin, player, 2);
			}
			
			for (final Player target : Bukkit.getOnlinePlayers())
	        {
	            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	                public void run() {
	                	VoteManager.updateTab(target);
	                }
	            }, 20L);
	        }
		}
	} //Event Ends
}

