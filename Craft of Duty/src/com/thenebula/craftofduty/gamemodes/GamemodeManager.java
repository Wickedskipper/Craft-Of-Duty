package com.thenebula.craftofduty.gamemodes;

import java.util.HashMap;
import java.util.Map;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.enums.GAMEMODE;

public class GamemodeManager {
	private CraftofDuty plugin;
	
	private GAMEMODE Gamemode;
	
	private Map<GAMEMODE, Integer> NeededPlayers = new HashMap<GAMEMODE, Integer>();
	private Map<GAMEMODE, Integer> NeededPlayersForVote = new HashMap<GAMEMODE, Integer>();
	
	public GamemodeManager(CraftofDuty p) {
		plugin = p;
		Gamemode = GAMEMODE.fromInteger(plugin.getConfig().getInt("gamemode"));
		
		NeededPlayers.put(GAMEMODE.DevMode, -1);
		NeededPlayers.put(GAMEMODE.FreeForAll, 2);
		NeededPlayers.put(GAMEMODE.TeamDeathMatch, 2);
		
		NeededPlayersForVote.put(GAMEMODE.DevMode, -1);
		NeededPlayersForVote.put(GAMEMODE.FreeForAll, 2);
		NeededPlayersForVote.put(GAMEMODE.TeamDeathMatch, 2);
	}
	
	public GAMEMODE getGameMode() {
		return Gamemode;
	}
	
	public void setGameMode(GAMEMODE gm) {
		Gamemode = gm;
	}
	
	public int getNeededPlayers(GAMEMODE gm) {
		try {
			return NeededPlayers.get(gm);
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int getNeededPlayersForVote(GAMEMODE gm) {
		try {
			return NeededPlayersForVote.get(gm);
		} catch (Exception ex) {
			return -1;
		}
	}
}
