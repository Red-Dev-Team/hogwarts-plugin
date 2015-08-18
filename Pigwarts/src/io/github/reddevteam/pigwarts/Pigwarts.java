package io.github.reddevteam.pigwarts;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Pigwarts extends JavaPlugin {
	private ScoreboardManager sbm = Bukkit.getScoreboardManager();
	public Scoreboard board = sbm.getNewScoreboard();
	@SuppressWarnings("unused")
	private Objective timeOnline;
	@Override
    public void onEnable() {
		JavaPlugin plugin = new JavaPlugin() {
		};
		// TODO Insert logic to be performed when the plugin is enabled
		File f = new File(plugin.getDataFolder() + "/");
		if(!f.exists()) {
			getLogger().info("Creating Plugin Folder");
		    f.mkdir();
		    
		}
		File fInit = new File(plugin.getDataFolder() + "/fInit.fak");
	    if (!fInit.exists()) {
	    	// Plugin's first run initialization
	    	{
	    		
	    		timeOnline = board.registerNewObjective("pigwarts_timeOnline", "stat.playOneMinute");
	    	}
	    	
	    	// Don't mess with this - it makes it so that the first run initialization doesnt run again
	    	{
		    	getLogger().info("Creating fInit Check");
		    	try {
					fInit.createNewFile();
				} catch (IOException e) {
					getLogger().severe("IOException (File error)");
					e.printStackTrace();
				}
	    	}
	    }
		getLogger().info("Pigwarts has initialized!");
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("Pigwarts: goodbye!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("pigwarts")) {
    		sender.sendMessage("Running Pigwarts");
    		
    		return true;
    	}
    	return false;
    }
	
}
