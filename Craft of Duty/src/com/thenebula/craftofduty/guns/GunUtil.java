package com.thenebula.craftofduty.guns;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class GunUtil {

	public static Gun getGunByName(String name) {
		if (name.toLowerCase().equals("fiveseven")) {
			return new FiveSeven();
		} else if (name.toLowerCase().equals("asp")) {
			return new ASP();
		} else if (name.toLowerCase().equals("python")) {
			return new Python();
		} else if (name.toLowerCase().equals("b23r")) {
			return new B23R();
		} else if (name.toLowerCase().equals("vector")) {
			return new Vector();
		} else if (name.toLowerCase().equals("chicom")) {
			return new Chicom();
		} else if (name.toLowerCase().equals("skorpion")) {
			return new Skorpion();
		} else if (name.toLowerCase().equals("p90")) {
			return new P90();
		} else if (name.toLowerCase().equals("fal")) {
			return new Fal();
		} else if (name.toLowerCase().equals("mtar")) {
			return new Mtar();
		} else {
			return null;
		}
	}
	
	public static Gun getGunByItemStack(ItemStack item) {
		try {
			String itemname = item.getItemMeta().getDisplayName();
			if (itemname.equals(ChatColor.WHITE + "Five-Seven")) {
				return new FiveSeven();
			} else if (itemname.equals(ChatColor.WHITE + "ASP")) {
				return new ASP();
			} else if (itemname.equals(ChatColor.WHITE + "Python")) {
				return new Python();
			} else if (itemname.equals(ChatColor.WHITE + "B23R")) {
				return new B23R();
			} else if (itemname.equals(ChatColor.WHITE + "Vector K10")) {
				return new Vector();
			} else if (itemname.equals(ChatColor.WHITE + "Chicom CQB")) {
				return new Chicom();
			} else if (itemname.equals(ChatColor.WHITE + "Skorpion EVO")) {
				return new Skorpion();
			} else if (itemname.equals(ChatColor.WHITE + "MTAR")) {
				return new Mtar();
			} else if (itemname.equals(ChatColor.WHITE + "FAL OSW")) {
				return new Fal();
			} else if (itemname.equals(ChatColor.WHITE + "P90")) {
				return new P90();
			} else {
				return null;
			}
		} catch (Exception ex) { //Probably item stack is null
			return null;
		}
	}
}
