package net.electronexchange.plugins.mineplotter;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class MinePlotter extends JavaPlugin {
	public HashMap<Player, PlotSettings> settings = new HashMap<Player, PlotSettings>();
	
	public boolean debug = false;
	
	public Location loc1;
	public Location loc2;
	public int plotSize = 10;  //Square size of graph
	public int xmin = 0;
	public int xmax = 5;
	public int ymin = 0;
	public int ymax = 5;
	public int zmin = 0;
	public int zmax = 5;
	public String expr = "x+z";
	
	@Override
	public void onEnable(){
		getCommand("mp").setExecutor(new MinePlotterCommands(this));
	}

	@Override
	public void onDisable(){

	}
}
