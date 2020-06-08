package com.thenebula.craftofduty.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.VoteManager;
import com.thenebula.craftofduty.enums.GAMEMODE;

public class PlayerLeave implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDisconnect(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		GAMEMODE gm = CraftofDuty.gmm.getGameMode();

		if (VoteManager.Players.contains(player)) {
			VoteManager.Players.remove(player);
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				VoteManager.updateTab(p);
			}
		}

		if (gm == GAMEMODE.FreeForAll || gm == GAMEMODE.TeamDeathMatch) {
		}
	}
}
