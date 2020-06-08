package com.thenebula.craftofduty.guns;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class P90 implements Gun {
	// private Guns plugin;
	// private boolean isReloading;
	private String Name = "P90";
	private int rAmmo = -59;
	private int Damage = 3;
	private double Velocity = 0; //TODO: Change velocity
	
	public void Give(Player player) {
		ItemStack Gun = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = Gun.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		// Set Item Description
		lore.add(ChatColor.GRAY + "Fully automatic with high capacity.");
		lore.add(ChatColor.GRAY + "Effective at close to medium range.");
		lore.add(ChatColor.RED + "Wickedskippers loves this gun");
		meta.setDisplayName(ChatColor.WHITE + "P90");
		meta.setLore(lore);
		Gun.setItemMeta(meta);
		player.getInventory().addItem(Gun);
	}
	
	@SuppressWarnings("deprecation")
	public void Reload(Player player) {
		Material Ammo = Material.DIAMOND;
		int AmmoCost = 1;
		if (player.getInventory().contains(Ammo, AmmoCost)) {
			player.getInventory().removeItem(new ItemStack(Ammo, AmmoCost));
			player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 10, 1);
			//Delay Everything after by 2 Seconds
			player.getItemInHand().setDurability((short) (rAmmo));
			player.updateInventory();
			player.sendMessage("Reloaded");
		} else
			player.sendMessage("Out Of Mags");
	}

	public int Shoot(Player player, int dur, int maxdur) {
		if (dur == maxdur + 39) {
			player.sendMessage("Out Of Ammo");
			player.playSound(player.getLocation(), Sound.CHEST_OPEN, 20, 1);
			return dur;
		} else {
			Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
			snowball.setShooter(player);
			snowball.setVelocity(player.getEyeLocation().getDirection().multiply(Velocity));
			player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 1);
			return (short)(dur + 32);
		}
	}
	
	public String getName() {
		return Name;
	}
	public int getDamage() {
		return Damage;
	}
}
