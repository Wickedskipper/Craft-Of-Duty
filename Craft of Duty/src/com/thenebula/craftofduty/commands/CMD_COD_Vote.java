package com.thenebula.craftofduty.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.VoteManager;

public class CMD_COD_Vote implements SubCommand {

	private boolean adminOnly = true;
	private String permission = "craftofduty.player.vote";
	public static CraftofDuty plugin;
	public static ArrayList<Player> voted = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		if (player.hasPermission(permission) || player.isOp()) {
			if (args.length == 1) {
				if (VoteManager.voting) {
					int MapID = VoteManager.getMapIDByName(args[0]);
					if (MapID != -1) {
						if (voted.contains(player)) {
							player.sendMessage(ChatColor.AQUA + "[COD] "
									+ ChatColor.WHITE
									+ "You can only vote once.");
						} else {
							voted.add(player);
							VoteManager.MapScore[MapID]
									.setScore(VoteManager.MapScore[MapID]
											.getScore() + 1);
							player.sendMessage(ChatColor.AQUA + "[COD] "
									+ ChatColor.WHITE + "Thank you for voting");
						}
					} else {
						player.sendMessage(ChatColor.AQUA + "[COD] "
								+ ChatColor.WHITE
								+ "Map \"" + args[0] + "\" not found!");
					}
				} else {
					player.sendMessage(ChatColor.AQUA + "[COD] "
							+ ChatColor.WHITE
							+ "You can't vote while there is no vote");
				}
			}
		}
		return false;
	}

	public void help(Player p) {
		String help = "/cod vote <Map> - Votes for that map";
		if (adminOnly == true) {
			if (p.hasPermission(permission) || p.isOp()) {
				p.sendMessage(ChatColor.AQUA + help);
			}
		} else {
			p.sendMessage(ChatColor.AQUA + help);
		}
	}

	public static void Init(CraftofDuty p) {
		plugin = p;
	}
}
