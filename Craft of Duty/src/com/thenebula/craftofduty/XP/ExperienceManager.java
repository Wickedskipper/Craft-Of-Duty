package com.thenebula.craftofduty.XP;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.thenebula.craftofduty.CraftofDuty;
import com.thenebula.craftofduty.DB.Database;

public class ExperienceManager {

	public ExperienceManager() { }
	
	public boolean userExists(String user){
		try{
			ResultSet rs = Database.MySQL.executeQuery("SELECT 1 FROM users WHERE username='" + user + "'");
	        return rs.next();
		}catch(Exception e){e.printStackTrace(); return false;}
	}
	
	public void registerPlayer(Player pl){
		Database.MySQL.insertEntry("users", new String[]{"username","xp","level","prestige","kills","deaths"}, new String[]{"'" + pl.getName() + "'", "0", "1", "0", "0", "0"});
	}
	
	public int getXP(Player player) {
		try {
			if(userExists(player.getName())) {
				ResultSet rs = Database.MySQL.executeQuery("SELECT * FROM users WHERE username='" + player.getName() + "'");
				if(rs.next()) {
					return rs.getInt("xp");
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void giveXP(Player player, int xp) {
		if (userExists(player.getName())){
			Database.MySQL.executeUpdate("UPDATE users SET xp=xp+" + xp + " WHERE username='" + player.getName() + "'");
		
			int XP = getXP(player);
			int Level = getLevel(player);
			if (CraftofDuty.lvm.getRequiredXP(Level) >= XP) {
				Database.MySQL.executeUpdate("UPDATE users SET level=level+1 WHERE username='" + player.getName() + "'");
			}
		}
	}
	
	public void setXP(Player player, int xp) {
		if (userExists(player.getName())){
			Database.MySQL.executeUpdate("UPDATE users SET xp=" + xp + " WHERE username='" + player.getName() + "'");
		}
	}
	
	public int getLevel(Player player) {
		try {
			if(userExists(player.getName())) {
				ResultSet rs = Database.MySQL.executeQuery("SELECT * FROM users WHERE username='" + player.getName() + "'");
				if(rs.next()) {
					return rs.getInt("level");
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void setLevel(Player player, int level) {
		if (userExists(player.getName())){
			Database.MySQL.executeUpdate("UPDATE users SET level=" + level + " WHERE username='" + player.getName() + "'");
		}
	}
}
