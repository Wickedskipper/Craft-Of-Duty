package com.thenebula.craftofduty;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thenebula.craftofduty.DB.Database;
import com.thenebula.craftofduty.DB.MySQLAPI;
import com.thenebula.craftofduty.XP.ExperienceManager;
import com.thenebula.craftofduty.XP.LevelManager;
import com.thenebula.craftofduty.commands.CMD_COD_Vote;
import com.thenebula.craftofduty.commands.COD_CommandHandler;
import com.thenebula.craftofduty.commands.GUNS_CommandHandler;
import com.thenebula.craftofduty.events.DamageEvent;
import com.thenebula.craftofduty.events.DeathEvent;
import com.thenebula.craftofduty.events.PlayerInteract;
import com.thenebula.craftofduty.events.PlayerJoin;
import com.thenebula.craftofduty.events.PlayerLeave;
import com.thenebula.craftofduty.gamemodes.GamemodeManager;
public class CraftofDuty extends JavaPlugin {

	public static GamemodeManager gmm;
	public static ExperienceManager xpm = new ExperienceManager();
	public static LevelManager lvm = new LevelManager();
	public final Logger logger = Logger.getLogger("Minecraft");
	CraftofDuty p = this;

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		loadConfiguration();
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new DamageEvent(), this);
		pm.registerEvents(new DeathEvent(), this);
		pm.registerEvents(new PlayerJoin(), this);
		setCommands();
		
		// Initialize few classes
		PlayerJoin.Init(this);
		VoteManager.Init(this);
		CMD_COD_Vote.Init(this);
		
		gmm = new GamemodeManager(this);
		
		logger.info("[MySQL] Connecting to MySQL");
		Database.MySQL = new MySQLAPI(p);
		Database.MySQL.Connect(this.getConfig().getString("database.host"), this.getConfig().getString("database.port"), this.getConfig().getString("database.db"), this.getConfig().getString("database.user"), this.getConfig().getString("database.pass"));
	}

	public void setCommands() {
		getCommand("guns").setExecutor(new GUNS_CommandHandler());
		getCommand("cod").setExecutor(new COD_CommandHandler());
	}

	public static void sendMsg(Player pl, String msg) {
		pl.sendMessage(ChatColor.AQUA + "[COD] " + ChatColor.WHITE + msg);
	}
	
	public void loadConfiguration(){
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
	}
}
