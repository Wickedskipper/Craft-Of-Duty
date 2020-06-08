package com.thenebula.craftofduty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;

public class MapManager {
	
	private static HashMap<String, ArrayList<Location>> MapSpawns = new HashMap<String, ArrayList<Location>>();
	
	public static void Init(World world) {
		// Define map spawns.
		ArrayList<Location> StandoffSpawns = new ArrayList<Location>();
		
		// Add spawns.
		Collections.addAll(StandoffSpawns, new Location(world, 390, 64, -69 ), // Test spawns by Cross...
										   new Location(world, 392, 64, -53 ),
										   new Location(world, 437, 64, -9  ),
										   new Location(world, 502, 64,  31 ),
										   new Location(world, 527, 64, -68 ),
										   new Location(world, 514, 64, -93 ),
										   new Location(world, 351, 63, -100));
		
		
		// Add map spawns to MapSpawns
		MapSpawns.put("mp_standoff", StandoffSpawns);
	}
	
	public static ArrayList<Location> getSpawns(String map) {
		if (MapSpawns.containsKey(map)) {
			return MapSpawns.get(map);
		} else {
			return null;
		}
	}
	
	public static boolean mapExists(String mapcode) {
		return MapSpawns.containsKey(mapcode);
	}
	
	public static String getMapNameByCode(String mapcode) {
		if (mapcode.equalsIgnoreCase("mp_standoff")) {
			return "Standoff";
		} else {
			return "Unknown Map";
		}
	}
	
	public static String getMapCodeByName(String mapname) {
		if (mapname.equalsIgnoreCase("standoff")) {
			return "mp_standoff";
		} else {
			return "unknown";
		}
	}
}
