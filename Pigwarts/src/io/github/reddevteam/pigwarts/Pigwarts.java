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
	@SuppressWarnings("unused")
	private Objective hasRecivedMessage;
	@Override
    public void onEnable() {
		// TODO Insert logic to be performed when the plugin is enabled
		File f = new File(getDataFolder() + "/");
		
		if(!f.exists()) {
			
			getLogger().info("Creating Plugin Folder");
		    f.mkdir();
		    
		}
		
		File fInit = new File(getDataFolder() + "/fInit.fak");
		
	    if (!fInit.exists()) {
	    	
	    	// Plugin's first run initialization
	    	{
	    		//plugin will run off of scoreboards
	    		// time online will count time online
	    		// has recieved message will make it so players wont recieve messages twice.  1= true 0= false
	    		hasRecivedMessage = board.registerNewObjective("pigwarts_hasRecievedMessage", "dummy");
	    		timeOnline = board.registerNewObjective("pigwarts_timeOnline", "stat.playOneMinute");
	    	}
	    	
	    	// Don't mess with this - it makes it so that the first run initialization doesn't run again
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
	    this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    	 
	    	  public void run() {
	    	      
	    	  }
	    	}, 60L, 200L);
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
    		
    		if (args[0].equalsIgnoreCase("reset")) {
    			if (args[1].equalsIgnoreCase("y")) {
    				// resets initialization
        			File f = new File(getDataFolder() + "/");
        			File fInit = new File(getDataFolder() + "/fInit.fak");
        			fInit.delete();
        			f.delete();
        			timeOnline.unregister();
        			hasRecivedMessage.unregister();
        			
        			getServer().shutdown();
    			} else {
    				sender.sendMessage("Warning: Will stop the server. To Continue do /pigwarts reset y");
    			}
    			
    			
    		}
    		
    		return true;
    	}
    	return false;
    }
	
}
