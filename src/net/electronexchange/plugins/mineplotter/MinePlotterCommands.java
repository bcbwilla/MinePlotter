package net.electronexchange.plugins.mineplotter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinePlotterCommands implements CommandExecutor {
	private MinePlotter mp;
	private PlotSettings plotSettings;
	
	public MinePlotterCommands(MinePlotter mp) {
		this.mp = mp;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("mp")){
			Player p = (Player) sender;
			
			if(mp.settings.containsKey(p)) {
				plotSettings = mp.settings.get(p);
			} else {
				plotSettings = new PlotSettings(p);
			}
			
			
			if(args.length == 2) {	
				if(args[0].equalsIgnoreCase("xmin")) {
					int xmin = Integer.parseInt(args[1]);
					plotSettings.setXMin(xmin);
					
					p.sendMessage("Xmin set to " + args[1]);
					
				} else if(args[0].equalsIgnoreCase("ymin")) {
					int ymin = Integer.parseInt(args[1]);
					plotSettings.setYMin(ymin);
				
					p.sendMessage("Ymin set to " + args[1]);

				} else if(args[0].equalsIgnoreCase("zmin")) {
					int zmin = Integer.parseInt(args[1]);
					plotSettings.setZMin(zmin);
					
					p.sendMessage("Zmin set to " + args[1]);
					
				} else if(args[0].equalsIgnoreCase("xmax")) {
					int xmax = Integer.parseInt(args[1]);
					plotSettings.setXMax(xmax);
					
					p.sendMessage("Xmax set to " + args[1]);
					
				} else if(args[0].equalsIgnoreCase("ymax")) {
					int ymax = Integer.parseInt(args[1]);
					plotSettings.setYMax(ymax);
					
					p.sendMessage("Ymax set to " + args[1]);

				} else if(args[0].equalsIgnoreCase("zmax")) {
					int zmax = Integer.parseInt(args[1]);
					plotSettings.setZMax(zmax);
					
					p.sendMessage("Zmax set to " + args[1]);
					
				} else if(args[0].equalsIgnoreCase("plotrange")) {
					int range = Integer.parseInt(args[1]);
					plotSettings.setXMax(range);
					plotSettings.setZMax(range);
					plotSettings.setYMax(range);
					
					p.sendMessage("Plot range set to " + args[1]);
					
					
				} else if(args[0].equalsIgnoreCase("expr")) {
					String expr = args[1];
					plotSettings.setExpr(expr);
					
					p.sendMessage("Expression set to " + expr);
					
				} else if(args[0].equalsIgnoreCase("plotsize")) {
					plotSettings.setPlotSize(Integer.valueOf(args[1]));
					
					p.sendMessage("Plotsize set to " + args[1]);
					
				} else if(args[0].equalsIgnoreCase("material")) {
					String materialName = args[1].toUpperCase();
					
					if(isMaterial(materialName)) {
						Material material = Material.getMaterial(materialName);
						plotSettings.setMaterial(material);
						p.sendMessage("Plot material set to " + args[1]);
					} else {
						p.sendMessage(args[1] + "is not a valid material");
					}

				} else if(args[0].equalsIgnoreCase("squashiness")) {
					plotSettings.setSquashiness(Integer.valueOf(args[1]));
					p.sendMessage("Squashiness set to " + args[1]);
				
				} else if(args[0].equalsIgnoreCase("plot")) {
					p.sendMessage("Plotting " + args[1]);
					plotSettings.setExpr(args[1]);
					
					PlotBuilder pb = new PlotBuilder(mp, plotSettings);
					pb.plot();
				}
				
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("pos1")){
					Location loc1 = p.getLocation();
					plotSettings.setLocation(loc1);
					
					p.sendMessage("Location 1 set to " + loc1.toString());
					
					
				} else if(args[0].equalsIgnoreCase("plot")) {
					p.sendMessage("Plotting!");
					
					PlotBuilder pb = new PlotBuilder(mp, plotSettings);
					pb.plot();
					
				} else if(args[0].equalsIgnoreCase("undo")) {
					p.sendMessage("Undoing last plot");
					
					PlotBuilder pb = new PlotBuilder(mp, plotSettings);
					pb.undo();
					
				} else if(args[0].equalsIgnoreCase("echo")) {
					p.sendMessage(plotSettings.toString());
					
				} else if(args[0].equalsIgnoreCase("debug")) {
					mp.debug = !mp.debug;
					p.sendMessage("Debug=" + String.valueOf(mp.debug));
				}
			}
			mp.settings.put(p, plotSettings);
			return true;
		}
		return false; 
	}
	
	private boolean isMaterial(String material) {
		
		for(Material m : Material.values()) {
			if(m.name().equals(material)) {
				return true;
			}
		}
		return false;
	}

}
