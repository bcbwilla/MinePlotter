package net.electronexchange.plugins.mineplotter;


import org.bukkit.entity.Player;

import expr.expr.Expr;
import expr.expr.Parser;
import expr.expr.SyntaxException;
import expr.expr.Variable;

/*
 * Builds a 3D grid by evaluating inputted function
 */
public class Grid {
	private MinePlotter mp;
	private PlotSettings ps;
	private Player p;
	private int plotSize;
	private double dx;
	private double dz;
	private double verticalScaling;
	
	private double grid[][];

	public Grid(MinePlotter mp, PlotSettings ps) {
		this.mp = mp;
		this.ps = ps;
		this.p = ps.getPlayer();
		
		plotSize = ps.getPlotSize();
		
		grid = new double[plotSize][plotSize];
		
		dx = (ps.getXMax() - ps.getXMin()) / (double) plotSize;
		dz = (ps.getZMax() - ps.getZMin()) / (double) plotSize;
		
		buildGrid();
		
	}
	
	public int valueAt(int i, int j) {
		return (int) Math.round(grid[i][j]*verticalScaling);
	}
	
	public int getLength() {
		return plotSize;
	}
	
	
	private boolean buildGrid() {
		Expr expr;
		try {
		    expr = Parser.parse(ps.getExpr()); 
		} catch (SyntaxException e) {
			p.sendMessage(String.valueOf(e.explain()));
			return false;
		}
		
		final Variable xvar = Variable.make("x"); 
		final Variable zvar = Variable.make("z");
		
		if(mp.debug) {
			mp.getLogger().info("Building Grid.");
		}
		
		for(int i=0; i < grid.length; i++) {
			for(int j=0; j < grid[i].length; j++) {
				double x = ps.getXMin() + i*dx;
				double z = ps.getZMin() + j*dz;
				
				xvar.setValue(x);
				zvar.setValue(z);
				
				if(mp.debug) {	
				mp.getLogger().info("i="+String.valueOf(i)+" j="+String.valueOf(j)+" y="+String.valueOf(expr.value()));		
				}
				
				grid[i][j] = expr.value();
			}		
		}
		
		double max = max(grid);
		this.verticalScaling = ps.getPlotSize() / (ps.getSquashiness()*max);  //Used so that graph fills allowed area
		return true;
	}
	
	/*
	 * Find maximum value on grid, for scaling.
	 */
    private double max(double[][] grid) {
        double max = grid[0][0];
        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[col].length; row++) {
                if (max < grid[col][row]) {
                    max = grid[col][row];
                }
            }
        }
        return max;
    }
}
