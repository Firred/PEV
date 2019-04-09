package practica1;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import common.evaluacion.Function_main;

public class Main {
private static AlgoritmoGenetico ag;
public static int tipo = 0;
private static int Tpobl = 100;
private static int generaciones = 100;
private static double elite = 2;


	public static void main(String[] args) {
	// define your data
		
	double[] x = { 1, 2, 3, 4, 5, 6 };
	double[] y = { 45, 89, 6, 32, 63, 12 };
	
	// create your PlotPanel (you can use it as a JPanel)
	Plot2DPanel plot = new Plot2DPanel();
	
	// define the legend position
	plot.addLegend("SOUTH");
	
	// add a line plot to the PlotPanel
	plot.addLinePlot("my plot", x, y);
	
	// put the PlotPanel in a JFrame like a JPanel
	JFrame frame = new JFrame("a plot panel");
	frame.setSize(400, 400);
	frame.setContentPane(plot);
	frame.setVisible(true);
	}


}
