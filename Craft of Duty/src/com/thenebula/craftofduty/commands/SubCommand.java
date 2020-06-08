package com.thenebula.craftofduty.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SubCommand {
	
	public boolean adminOnly = false;

    public boolean onCommand(CommandSender sender, String[] args);

    public void help(Player p);
    
}
