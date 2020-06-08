package com.thenebula.craftofduty.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.PlayerManager;
import com.thenebula.craftofduty.enums.GAMEMODE;
import com.thenebula.craftofduty.guns.Gun;
import com.thenebula.craftofduty.guns.GunUtil;

public class DeathEvent implements Listener {

	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) { //Still needs tests.
		GAMEMODE gm = CraftofDuty.gmm.getGameMode();
		if(gm == GAMEMODE.DevMode || gm == GAMEMODE.FreeForAll || gm == GAMEMODE.TeamDeathMatch) {
			if (event.getEntityType() == EntityType.PLAYER) {
				Gun gun;
				Boolean KilledByPlayer = false;
				
				Player player = event.getEntity();
				Player killer = player.getKiller();
				event.setDroppedExp(0);
				
				try {
					gun = GunUtil.getGunByItemStack(killer.getItemInHand());
				} catch (Exception ex) {
					gun = null;
				}
				
				if (gun != null) {
					event.setDeathMessage(ChatColor.BLUE + killer.getName() + ChatColor.WHITE + " [" + gun.getName() + "] " + ChatColor.GOLD + player.getName());
					KilledByPlayer = true;
				} else if (gun == null && killer.getItemInHand().getType() == Material.IRON_SWORD) {
					event.setDeathMessage(ChatColor.BLUE + killer.getName() + ChatColor.WHITE + " [Knife] " + ChatColor.GOLD + player.getName());
					KilledByPlayer = true;
				} else {
					event.setDeathMessage(ChatColor.WHITE + "[Died] "+ ChatColor.GOLD + player.getName());
					KilledByPlayer = false;
				}
				
				PlayerManager.addDeath(player, KilledByPlayer); // Deaths first! If player is killed by another player, tab will be updated ONLY after addKill();
				
				if (KilledByPlayer) {
					killer.sendMessage(ChatColor.YELLOW + "400+");
					
					PlayerManager.addKill(killer);
				}
			}
		}
	}
}
