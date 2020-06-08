package com.thenebula.craftofduty.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thenebula.craftofduty.CraftofDuty;

public class CMD_COD_Stats implements SubCommand {

	private boolean adminOnly = false;
	private String permission = "craftofduty.player.stats";

	public boolean onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		int Level = CraftofDuty.xpm.getLevel(player);
		int XP = CraftofDuty.xpm.getXP(player);
		int RequiredXP = CraftofDuty.lvm.getRequiredXP(Level);
		String LevelName = CraftofDuty.lvm.getLevelName(Level);
		
		String MESSAGE;
		MESSAGE = ChatColor.WHITE + "Level " + ChatColor.YELLOW + "{LEVEL} " + ChatColor.WHITE + "({LEVEL_NAME})";
		MESSAGE = MESSAGE.replace("{LEVEL}", Level+"").replace("{LEVEL_NAME}", LevelName);
		player.sendMessage(MESSAGE);
		MESSAGE = ChatColor.YELLOW + "{XP}" + ChatColor.WHITE + "/" + ChatColor.YELLOW + "{REQUIREDXP} " + ChatColor.WHITE + "XP";
		MESSAGE = MESSAGE.replace("{XP}", XP+"").replace("{REQUIREDXP}", RequiredXP+"");
		player.sendMessage(MESSAGE);
		return false;
	}

	public void help(Player p) {
		String help = "/cod stats - Get your level and XP";
		if (adminOnly == true) {
			if (p.hasPermission(permission) || p.isOp()) {
				p.sendMessage(ChatColor.AQUA + help);
			}
		} else {
			p.sendMessage(ChatColor.AQUA + help);
		}
	}
}
