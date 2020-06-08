package com.thenebula.craftofduty.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thenebula.craftofduty.guns.Gun;
import com.thenebula.craftofduty.guns.GunUtil;
import com.thenebula.craftofduty.guns.PistolAmmo;

public class CMD_GUNS_Give implements SubCommand {

	private boolean adminOnly = true;
	private String permission = "craftofduty.admin.give";

	public boolean onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		if (player.hasPermission(permission) || player.isOp()) {
			if (args.length == 1) {
				if (args[0].toLowerCase().equals("pistolammo")) {
					PistolAmmo gun = new PistolAmmo();
					gun.Give(player);
					player.sendMessage("Given Handgun Ammunition");
				} else {
					Gun gun = GunUtil.getGunByName(args[0]);
					if (gun != null) {
						gun.Give(player);
						player.sendMessage("Given " + gun.getName());
					} else {
						player.sendMessage("Gun '" + args[0] + "' not found.");
					}
				}
			}
		}
		return false;
	}

	public void help(Player p) {
		String help = "/guns give <Item Name> - Gives the player the Item";
		if (adminOnly == true) {
			if (p.hasPermission(permission) || p.isOp()) {
				p.sendMessage(ChatColor.AQUA + help);
			}
		} else {
			p.sendMessage(ChatColor.AQUA + help);
		}
	}
}
