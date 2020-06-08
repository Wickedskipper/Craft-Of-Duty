package com.thenebula.craftofduty.guns;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PistolAmmo {
	
	public void Give(Player player) {
		ItemStack Gun = new ItemStack(Material.INK_SACK);
		ItemMeta meta = Gun.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		//Set Item Description
		lore.add(ChatColor.GRAY + "Ammunition for all ");
		lore.add(ChatColor.GRAY + "one-handed weaponry. ");
		meta.setDisplayName(ChatColor.WHITE + "Handgun Ammunition");
		meta.setLore(lore);
		Gun.setItemMeta(meta);
		player.getInventory().addItem(Gun);
	}
}
