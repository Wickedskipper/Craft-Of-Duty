package com.thenebula.craftofduty.guns;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Barrett implements Gun {
	// private Guns plugin;
	// private boolean isReloading;
	private String Name = "Barrett .50cal";
	private int rAmmo = -59;
	private int Damage = 3;
	private double Velocity = 0; //TODO: Change velocity

	public void Give(Player player) {
		ItemStack Gun = new ItemStack(Material.WOOD_HOE);
		ItemMeta meta = Gun.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		// Set Item Description
		lore.add(ChatColor.GRAY + "Semi-automatic sniper rifle.");
		lore.add(ChatColor.GRAY + "Best used at long range");
		meta.setDisplayName(ChatColor.WHITE + "Barrett .50cal");
		meta.setLore(lore);
		Gun.setItemMeta(meta);
		player.getInventory().addItem(Gun);
	}
	
	@SuppressWarnings("deprecation")
	public void Reload(Player player) { //TODO: Update reload method
		ItemStack PistolAmmo = new ItemStack(Material.INK_SACK, 1);
		player.getItemInHand().setDurability((short) (rAmmo));
		player.getInventory().removeItem(PistolAmmo);
		player.updateInventory();
		player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 10, 1);
	}

	public int Shoot(Player player, int dur, int maxdur) {
		if (dur == maxdur + 1) {
			player.sendMessage("Out Of Ammo");
			player.playSound(player.getLocation(), Sound.CHEST_OPEN, 20, 1);
			return dur;
		} else {
			Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
			snowball.setShooter(player);
			snowball.setVelocity(player.getEyeLocation().getDirection().multiply(Velocity));
			player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 1);
			return (short)(dur + 3);
		}
	}
	
	public String getName() {
		return Name;
	}
	public int getDamage() {
		return Damage;
	}
}