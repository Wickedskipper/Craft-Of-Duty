package com.thenebula.craftofduty.guns;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Python implements Gun {
	// private Guns plugin;
	// private boolean isReloading;
	private String Name = "Python";
	private int rAmmo = -33;
	private int Damage = 4;
	private double Velocity = 1.05;

	public void Give(Player player) {
		ItemStack Gun = new ItemStack(Material.GOLD_HOE);
		ItemMeta meta = Gun.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		// Set Item Description
		lore.add(ChatColor.GRAY + "Semi-automatic revolver.");
		lore.add(ChatColor.GRAY
				+ "Carrying a slow reload, this revolver provides ");
		lore.add(ChatColor.GRAY + "high damage provided with accuracy. ");
		meta.setDisplayName(ChatColor.WHITE + "Python");
		meta.setLore(lore);
		Gun.setItemMeta(meta);
		player.getInventory().addItem(Gun);
	}
	
	@SuppressWarnings("deprecation")
	public void Reload(Player player) {
		Material Ammo = Material.INK_SACK;
		int AmmoCost = 1;
		if (player.getInventory().contains(Ammo, AmmoCost)) {
			player.getInventory().removeItem(new ItemStack(Ammo, AmmoCost));
			player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 10, 1);
			//Delay Everything after by 5 Seconds
			player.getItemInHand().setDurability((short) (rAmmo));
			player.updateInventory();
			player.sendMessage("Reloaded");
		} else
			player.sendMessage("Out Of Mags");
	}

	public int Shoot(Player player, int dur, int maxdur) {
		if (dur == maxdur + 4) {
			player.sendMessage("Out Of Ammo");
			player.playSound(player.getLocation(), Sound.CHEST_OPEN, 20, 1);
			return dur;
		} else {
			Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
			snowball.setShooter(player);
			snowball.setVelocity(player.getEyeLocation().getDirection().multiply(Velocity));
			player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 1);
			return (short)(dur + 6);
		} 
	}
	
	public String getName() {
		return Name;
	}
	public int getDamage() {
		return Damage;
	}
}
