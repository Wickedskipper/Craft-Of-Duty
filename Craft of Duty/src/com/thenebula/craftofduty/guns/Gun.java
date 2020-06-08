package com.thenebula.craftofduty.guns;

import org.bukkit.entity.Player;

public interface Gun {
	
	public void Give(Player player);
	
	public int Shoot(Player player, int dur, int maxdur);
	
	public void Reload(Player player);
	
	public String getName();
	public int getDamage();
}
