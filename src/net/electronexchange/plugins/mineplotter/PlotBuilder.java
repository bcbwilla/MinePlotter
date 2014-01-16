package net.electronexchange.plugins.mineplotter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlotBuilder {
	private MinePlotter mp;
	private PlotSettings ps;
	private Grid grid;
	
	private int xLower;
	private int xUpper;
	private int yLower;
	private int zLower;
	private int zUpper;
	
	
	public PlotBuilder(MinePlotter mp, PlotSettings ps) {
		this.mp = mp;
		this.ps = ps;
		this.grid = new Grid(mp, ps);
		
		Location location = ps.getLocation();
		int plotSize = ps.getPlotSize();
		
		yLower = location.getBlockY();
		
		xLower = location.getBlockX();    // One corner of plot as placed in the world
		xUpper = xLower + plotSize;  // Opposite corner of plot along X
		
		zLower = location.getBlockZ();    // One corner of plot as placed in the world
		zUpper = zLower + plotSize;  // Opposite corner of plot along Z
		
	}
	
	public void plot() {
		changeBlocks(ps.getPlayer().getWorld(), xLower, yLower, zLower, grid.getLength(), ps.getMaterial());
	}
	
	public void undo() {
		changeBlocks(ps.getPlayer().getWorld(), xLower, yLower, zLower, grid.getLength(), Material.AIR);
	}
	
	/*
	 * This method loops over blocks in the world and sets them to material
	 */
	public void changeBlocks(World world, int xLower, int yLower, int zLower, int range, Material material) {
		
		
		if(mp.debug) {
			mp.getLogger().info("Changing world");
			mp.getLogger().info("xl="+String.valueOf(xLower)+" xu="+String.valueOf(xUpper)+" zl="+String.valueOf(zLower)+" zu="+String.valueOf(zUpper));
		}
		
		
		int xpos = xLower;  // X position of the first block to change
		for(int i=0; i < grid.getLength(); i++) {
			int zpos = zLower;  // Z position of the first block to change
			
			for(int j=0; j < grid.getLength(); j++) {
				int yBlockMax = grid.valueAt(i, j) + yLower;  // The "value" of the function at i,j.
				int ypos = yLower; // Lowest Y position where the graph will sit
				
				for(int k = 0; k <= yBlockMax; k++) {
					
					if(mp.debug) {
						mp.getLogger().info("x=" + String.valueOf(xpos) + " z=" + String.valueOf(zpos) + " y=" + String.valueOf(ypos));
					}
					
					world.getBlockAt(xpos, ypos, zpos).setType(material);
					
					ypos++;
				}
				zpos++;
				
			}
			xpos++;
		}		
	}
	
}
