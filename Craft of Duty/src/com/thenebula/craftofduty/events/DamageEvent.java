package com.thenebula.craftofduty.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.enums.GAMEMODE;
import com.thenebula.craftofduty.guns.Gun;
import com.thenebula.craftofduty.guns.GunUtil;

public class DamageEvent implements Listener {
	CraftofDuty plugin;

	public void Init(CraftofDuty p) {
		plugin = p;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		GAMEMODE gm = CraftofDuty.gmm.getGameMode();
		if(gm == GAMEMODE.DevMode || gm == GAMEMODE.FreeForAll || gm == GAMEMODE.TeamDeathMatch) {
			if ((event.getDamager() instanceof Snowball)) {
				Snowball snowball = (Snowball)event.getDamager();
				Player player = (Player)snowball.getShooter();
				Gun gun = GunUtil.getGunByItemStack(player.getItemInHand());
				if (gun != null) {
					event.setDamage(gun.getDamage());
				}
			}
		}
	}
}
