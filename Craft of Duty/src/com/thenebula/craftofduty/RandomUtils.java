package com.thenebula.craftofduty;

public class RandomUtils {
	// Random utils by Reosh :]
	
	public static String Capitalize(String input) {
		if (input.length() >= 1) {
			return input.substring(0, 1).toUpperCase() + input.substring(1);
		} else {
			return null;
		}
	}
}
