package net.electronexchange.plugins.mineplotter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/*
 * Stores all of the settings for a plot
 */
public class PlotSettings {
	private Player p;
	private Location location;
	private int plotSize = 10;  //Square size of graph
	private int xmin = 0;
	private int xmax = 10;
	private int ymin = 0;
	private int ymax = 10;
	private int zmin = 0;
	private int zmax = 10;
	private String expr = "x+z";
	private Material material = Material.GOLD_BLOCK;
	private int squashiness = 2;
	
	public PlotSettings(Player p){
		this.p = p;
		this.location = p.getLocation();
		
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setPlotSize(int plotSize) {
		this.plotSize = plotSize;
	}
	
	public void setXMin(int xmin) {
		this.xmin = xmin;
	}
	
	public void setXMax(int xmax) {
		this.xmax = xmax;
	}
	
	public void setYMin(int ymin) {
		this.ymin = ymin;
	}
	
	public void setYMax(int ymax) {
		this.ymax = ymax;
	}
	
	public void setZMin(int zmin) {
		this.zmin = zmin;
	}
	
	public void setZMax(int zmax) {
		this.zmax = zmax;
	}
	
	public void setExpr(String expr) {
		this.expr = expr;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public void setSquashiness(int squashiness) {
		this.squashiness = squashiness;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public int getPlotSize() {
		return plotSize;
	}
	
	public int getXMin() {
		return xmin;
	}
	
	public int getXMax() {
		return xmax;
	}
	
	public int getYMin() {
		return ymin;
	}
	
	public int getYMax() {
		return ymax;
	}
	
	public int getZMin() {
		return zmin;
	}
	
	public int getZMax() {
		return zmax;
	}
	
	public String getExpr() {
		return expr;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public int getSquashiness() {
		return squashiness;
	}
	
	public String toString() {
		String msg = "Settings for " + p.getDisplayName() + ":\n"
		           + " Location: " + locationString(location) + "\n"
				   + " Plot Size: " + String.valueOf(plotSize) + "\n"
				   + " Function: " + expr + "\n"
				   + " Function x range: (" + String.valueOf(xmin) + "," + String.valueOf(xmax) +")\n"
				   + " Function y range: (" + String.valueOf(ymin) + "," + String.valueOf(ymax) +")\n"
				   + " Function z range: (" + String.valueOf(zmin) + "," + String.valueOf(zmax) +")\n"
				   + " Squashiness: " + String.valueOf(squashiness) + "\n"
				   + " Plot material: " + material.name();
		return msg;
	}
	
	private String locationString(Location l) {
		return "(x,y,z) = "+"("+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ()+")";
	}
}
