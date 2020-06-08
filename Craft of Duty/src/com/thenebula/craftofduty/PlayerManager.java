package com.thenebula.craftofduty;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {
	
	private static HashMap<Player, Integer> kills = new HashMap<Player, Integer>();
	private static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
	
	public static int getKills(Player player) {
		if (kills.containsKey(player)) {
			return kills.get(player);
		} else {
			return 0; // Return 0 if player has no kills.
		}
	}
	
	public static int getDeaths(Player player) {
		if (deaths.containsKey(player)) {
			return deaths.get(player);
		} else {
			return 0; // Return 0 if player has no deaths.
		}
	}
	
	public static void addKill(Player player) {
		kills.put(player, getKills(player) + 1);
		updateScores();
	}
	
	public static void addDeath(Player player, boolean killedByPlayer) {
		deaths.put(player, getDeaths(player) + 1);
		if (!killedByPlayer) {
			updateScores();
		}
	}
	
	public static void clearMap() {
		kills.clear();
		deaths.clear();
	}
	
	private static void updateScores() {
		for (Player target : Bukkit.getOnlinePlayers())
        {
			VoteManager.updateTab(target);
        }
	}
}
