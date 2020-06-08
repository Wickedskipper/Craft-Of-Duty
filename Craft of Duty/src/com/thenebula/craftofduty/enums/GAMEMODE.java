package com.thenebula.craftofduty.enums;

public enum GAMEMODE {
	DevMode,
	FreeForAll,
	TeamDeathMatch;

	public static GAMEMODE fromInteger(int i) {
		if (i == -1) {
			return GAMEMODE.DevMode;
		} else if (i == 0) {
			return GAMEMODE.FreeForAll;
		} else if (i == 1) {
			return GAMEMODE.TeamDeathMatch;
		} else {
			return null;
		}
	}
	
	public static String getString(GAMEMODE gm) {
		if (gm == GAMEMODE.DevMode) {
			return "Dev Mode";
		} else if (gm == GAMEMODE.FreeForAll) {
			return "Free For All";
		} else if (gm == GAMEMODE.TeamDeathMatch) {
			return "Team Death Match";
		} else {
			return "Unknown";
		}
	}
	
	public static String getShortString(GAMEMODE gm) {
		if (gm == GAMEMODE.DevMode) {
			return "DevMode";
		} else if (gm == GAMEMODE.FreeForAll) {
			return "FFA";
		} else if (gm == GAMEMODE.TeamDeathMatch) {
			return "TDM";
		} else {
			return "Unknown";
		}
	}
}
