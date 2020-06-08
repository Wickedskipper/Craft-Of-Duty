package com.thenebula.craftofduty.XP;

public class LevelManager {
	public LevelManager() { }
	// Variables
	private int MaxLevel = 70;
	private int MaxLevelXP = 2434700;
	
	public int getRequiredXP(int level) {
		float xValue = level / (float)MaxLevel;
		float yValue = (float)Math.pow(xValue, 2);
		return (int)(yValue * MaxLevelXP);
	}
	
	public String getLevelName(int level) {
		if (level <= 70 && level >= 1) {
			return LevelNames[level - 1];
		} else {
			return "Unknown";
		}
	}
	
	// Level names
	String[] LevelNames = new String[] {
			"Private",
			"Private I",
			"Private II",
			"Private First Class",
			"Private First Class I",
			"Private First Class II",
			"Specialist",
			"Specialist I",
			"Specialist II",
			"Corporal",
			"Corporal I",
			"Corporal II",
			"Sergeant",
			"Sergeant I",
			"Sergeant II",
			"Staff Sergeant",
			"Staff Sergeant I",
			"Staff Sergeant II",
			"Sergeant First Class",
			"Sergeant First Class I",
			"Sergeant First Class II",
			"Master Sergeant",
			"Master Sergeant I",
			"Master Sergeant II",
			"First Sergeant",
			"First Sergeant I",
			"First Sergeant II",
			"Sergeant Major",
			"Sergeant Major I",
			"Sergeant Major II",
			"Command Sergeant Major",
			"Command Sergeant Major I",
			"Command Sergeant Major II",
			"2nd Lieutenant",
			"2nd Lieutenant I",
			"2nd Lieutenant II",
			"1st Lieutenant",
			"1st Lieutenant I",
			"1st Lieutenant II",
			"Captain",
			"Captain I",
			"Captain II",
			"Major",
			"Major I",
			"Major II",
			"Lieutenant Colonel",
			"Lieutenant Colonel I",
			"Lieutenant Colonel II",
			"Lieutenant Colonel III",
			"Colonel",
			"Colonel I",
			"Colonel II",
			"Colonel III",
			"Brigadier General",
			"Brigadier General I",
			"Brigadier General II",
			"Brigadier General III",
			"Major General",
			"Major General I",
			"Major General II",
			"Major General III",
			"Lieutenant General",
			"Lieutenant General I",
			"Lieutenant General II",
			"Lieutenant General III",
			"General",
			"General I",
			"General II",
			"General III",
			"Commander" //Finally
			};
}
