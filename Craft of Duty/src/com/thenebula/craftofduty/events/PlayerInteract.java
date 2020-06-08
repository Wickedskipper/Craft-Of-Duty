package com.thenebula.craftofduty.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.enums.GAMEMODE;
import com.thenebula.craftofduty.guns.Gun;
import com.thenebula.craftofduty.guns.GunUtil;

public class PlayerInteract implements Listener {
	CraftofDuty plugin;

	public void Init(CraftofDuty p) {
		plugin = p;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent evt) {
		GAMEMODE gm = CraftofDuty.gmm.getGameMode();
		if(gm == GAMEMODE.DevMode || gm == GAMEMODE.FreeForAll || gm == GAMEMODE.TeamDeathMatch) {
			Player player = evt.getPlayer();
			Action action = evt.getAction();
			ItemStack held = player.getItemInHand();
			int dur = held.getDurability();
			Material mat = Material.getMaterial(held.getTypeId());
			int maxdur = mat.getMaxDurability();
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				Gun gun = GunUtil.getGunByItemStack(held);
				if (gun != null) {
					held.setDurability((short) gun.Shoot(player, dur, maxdur));
				}
			}	
			if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
				Gun gun = GunUtil.getGunByItemStack(held);
				if (gun != null) {
					gun.Reload(player);
				}
			}	
		}
	}
}
