package com.thenebula.craftofduty;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.mcsg.double0negative.tabapi.TabAPI;

import com.thenebula.craftofduty.enums.GAMEMODE;

public class VoteManager {
	public static CraftofDuty plugin;
	public static boolean voting = false;
	public static boolean countdownRunning = false;
	public static Score[] MapScore = new Score[5];
	public static Score Time;
	public static int countdown = 60;
	public static int threadsync = 0;
	public static int winningmap = 0;

	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	public static Scoreboard voteBoard = manager.getNewScoreboard();
	private static Scoreboard blankBoard = manager.getNewScoreboard();

	public static ArrayList<Player> Players = new ArrayList<Player>();

	public static void startVote(GAMEMODE gm) {
		if (gm == GAMEMODE.FreeForAll) {
			voteFFA();
		} else if (gm == GAMEMODE.TeamDeathMatch) {
			voteTDM();
		}
	}

	private static void voteFFA() {
		voting = true;
		Objective objective = voteBoard.registerNewObjective(
				"To Vote /vote ID", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		for (int i = 0; i < 5; i++) {
			MapScore[i] = objective.getScore(Bukkit
					.getOfflinePlayer(ChatColor.GREEN
							+ RandomUtils.Capitalize(plugin.getConfig()
									.getString("map.map" + i))));
		}

		Time = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA
				+ "Time Left"));

		MapScore[0].setScore(0);
		MapScore[1].setScore(0);
		MapScore[2].setScore(0);
		MapScore[3].setScore(0);
		MapScore[4].setScore(0);
		for (final Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(voteBoard);

			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
					new Runnable() {
						public void run() {
							TabAPI.setPriority(plugin, p, 2);
						}
					}, 40L);
		}

		updateScores();

		countdownFFA(countdown);
	}

	private static void voteTDM() { // TODO: Add... things. Do we need separate
									// methods for gamemodes?

	}

	private static void updateScores() {
		Time.setScore(countdown);
	}

	private static void countdownFFA(int time) {
		threadsync++;
		countdownRunning = true;
		countdown = time;
		updateScores();
		if (time > 0) {
			new CountdownThreadFFA(time).start();
		} else if (time <= 0) {
			countdownRunning = false;
			Bukkit.broadcastMessage(ChatColor.AQUA + "[COD] " + ChatColor.WHITE
					+ "The winning map is "
					+ RandomUtils.Capitalize(getMapNameByID(getWinner()))
					+ ". The game will start soon");
			winningmap = getWinner();

			startGameFFA();
		} else
			return;
	}

	public static void startGameFFA() {
		voting = false;
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(blankBoard);
			
			try {
				p.teleport(getRandomSpawn(MapManager.getMapCodeByName(getMapNameByID(winningmap))));
			} catch (Exception ex) {
				System.out.println("[Call of Duty] ERROR! Unknown map \"" + getMapNameByID(winningmap) + "\". Restarting server.");
				for (Player player : Bukkit.getOnlinePlayers()) { player.kickPlayer("Unknown map \"" + getMapNameByID(winningmap) + "\". Restarting server."); }
				Bukkit.getServer().reload();
				break;
			}
		}
		
		// TODO: Give players weapons somehow?
	}

	static class CountdownThreadFFA extends Thread {

		int time;
		int trun = threadsync;

		public CountdownThreadFFA(int t) {
			this.time = t;
		}

		public void run() {
			time--;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			if (trun == threadsync)
				countdownFFA(time);

		}
	}

	public static void Init(CraftofDuty p) {
		plugin = p;
	}

	public static String getMapNameByID(int ID) {
		try {
			return plugin.getConfig().getString("map.map" + ID);
		} catch (Exception ex) {
			return null;
		}
	}

	public static int getMapIDByName(String name) {
		name = name.toLowerCase();
		if (name.equals(plugin.getConfig().getString("map.map0"))) {
			return 0;
		} else if (name.equals(plugin.getConfig().getString("map.map1"))) {
			return 1;
		} else if (name.equals(plugin.getConfig().getString("map.map2"))) {
			return 2;
		} else if (name.equals(plugin.getConfig().getString("map.map3"))) {
			return 3;
		} else if (name.equals(plugin.getConfig().getString("map.map4"))) {
			return 4;
		} else {
			return -1;
		}
	}

	public static int getWinner() {
		int largest = -1;
		int winner = -1;
		for (int x = 0; x < MapScore.length; x++) {
			if (MapScore[x].getScore() > largest) {
				largest = MapScore[x].getScore();
				winner = x;
			}
		}
		return winner;
	}
	
	public static Location getRandomSpawn(String map) {
		if (MapManager.mapExists(map)) {
			ArrayList<Location> spawns = MapManager.getSpawns(map);
			
			int index = new Random().nextInt(spawns.size());
			return spawns.get(index);
		} else {
			return null;
		}
	}

	public static void updateTab(Player p) { // MAX CHARS IN TAB IS 16 //We
												// Think
		TabAPI.setTabString(plugin, p, 0, 0, ChatColor.BOLD + "Nebula Network");
		TabAPI.setTabString(plugin, p, 0, 1, ChatColor.BOLD + "Craft Of Duty");
		TabAPI.setTabString(plugin, p, 0, 2, ChatColor.BOLD + "Free For All");

		TabAPI.setTabString(plugin, p, 1, 0, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());
		TabAPI.setTabString(plugin, p, 1, 1, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());
		TabAPI.setTabString(plugin, p, 1, 2, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());

		TabAPI.setTabString(plugin, p, 2, 0, ChatColor.BOLD + "Player Name");
		TabAPI.setTabString(plugin, p, 2, 1, ChatColor.BOLD + "Kills");
		TabAPI.setTabString(plugin, p, 2, 2, ChatColor.BOLD + "Deaths");

		TabAPI.setTabString(plugin, p, 3, 0, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());
		TabAPI.setTabString(plugin, p, 3, 1, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());
		TabAPI.setTabString(plugin, p, 3, 2, ChatColor.RED + "   ---------"
				+ TabAPI.nextNull());

		// Show players
		Player[] players = Players.toArray(new Player[Players.size()]);

		for (int i = 0; i < 14; i++) {
			if (players.length > i && players[i] != null) {
				String name = players[i].getName();
				if (name.length() > 13) {
					name = name.substring(0, 10) + "...";
				}
				if (players[i].isOp()) {
					name = ChatColor.DARK_RED + name;
				}

				TabAPI.setTabString(plugin, p, 4 + i, 0, name);
				TabAPI.setTabString(plugin, p, 4 + i, 1, ChatColor.BOLD + "" + PlayerManager.getKills(players[i]) + TabAPI.nextNull()); // Kills
				TabAPI.setTabString(plugin, p, 4 + i, 2, ChatColor.BOLD + "" + PlayerManager.getDeaths(players[i]) + TabAPI.nextNull()); // Deaths
			}
		}

		TabAPI.updatePlayer(p);
	}
}
