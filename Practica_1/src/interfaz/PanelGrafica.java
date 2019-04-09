package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.eclipse.swt.awt.SWT_AWT;

import org.math.plot.Plot2DPanel;

import common.Cromosoma;
import common.Poblacion;
import interfaz.controlador.Controlador;
import interfaz.controlador.Observador;

public class PanelGrafica extends Plot2DPanel implements Observador {

	private double[] x;
	private double[] yMejor, yMedia, yMejorG;
	private int generaciones;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGrafica(/*int generaciones*/) {
		super();
/*		this.generaciones = generaciones;
		
		x = new double[this.generaciones];
		yMejor = new double[this.generaciones];
		yMedia = new double[this.generaciones];
		yMejorG = new double[this.generaciones];
		
		for(int i = 0; i < this.generaciones; i++) {
			x[i] = i;
		}*/
		
	//	double[] x = { 1, 2, 3, 4, 5, 6 };
		//double[] y = { 45, 89, 6, 32, 63, 12 };
		
		// create your PlotPanel (you can use it as a JPanel)
		//Plot2DPanel plot = new Plot2DPanel();
		
		// define the legend position
		this.addLegend("SOUTH");
		
		// add a line plot to the PlotPanel
//		this.addLinePlot("my plot", x, y);
		
		// put the PlotPanel in a JFrame like a JPanel
		//JFrame frame = new JFrame("a plot panel");
		//frame.setSize(600, 600);
		//frame.setContentPane(plot);
		//frame.setVisible(true);
		
		Controlador.getInstance().addObservador(this);
	}

	@Override
	public void update(Poblacion pobl, Cromosoma mejorG/*Cromosoma mejorG, Cromosoma mejorP, int gen*/) {
		if(pobl.getGeneracion() < this.generaciones) {
			System.out.println(pobl.getMejor().getApt());
			yMejor[pobl.getGeneracion()] = pobl.getMejor().getApt();
			yMedia[pobl.getGeneracion()] = pobl.getMedia();
			yMejorG[pobl.getGeneracion()] = mejorG.getApt();
		}
		else {
			this.removeAllPlotables();
			this.addLinePlot("Mejor Generación", x, yMejor);
			this.addLinePlot("Media", x, yMedia);
			this.addLinePlot("Mejor Global", x, yMejorG);
		}
	}

	@Override
	public void start(int generaciones, int variables) {
		this.generaciones = generaciones;
		
		x = new double[this.generaciones];
		yMejor = new double[this.generaciones];
		yMedia = new double[this.generaciones];
		yMejorG = new double[this.generaciones];
		
		for(int i = 0; i < this.generaciones; i++) {
			x[i] = i;
		}
	}

}