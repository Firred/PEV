package interfaz;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import org.eclipse.swt.awt.SWT_AWT;
import org.math.plot.Plot2DPanel;

import common.Cromosoma;
import common.Poblacion;
import interfaz.controlador.Controlador;
import interfaz.controlador.Observador;

public class PanelGrafica extends Plot2DPanel implements Observador {

	private double[] x;
	private double[] yMejor, yMedia, yMejorG;
	private ArrayList<Double> yMej, yMed, yMejG, xA;
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
		
		// define the legend position
		this.addLegend("SOUTH");
				
		Controlador.getInstance().addObservador(this);
	}

	@Override
	public void update(Poblacion pobl, Cromosoma mejorG/*Cromosoma mejorG, Cromosoma mejorP, int gen*/) {
		if(pobl.getGeneracion() < this.generaciones) {
			yMej.add(pobl.getMejor().getX());
			yMed.add(pobl.getMedia());
			yMejG.add(mejorG.getX());
		}
	}

	@Override
	public void start(int generaciones, int variables) {
		this.generaciones = generaciones;
		
		yMej = new ArrayList<>();
		yMed = new ArrayList<>();
		yMejG = new ArrayList<>();
		
		x = new double[this.generaciones];
		yMejor = new double[this.generaciones];
		yMedia = new double[this.generaciones];
		yMejorG = new double[this.generaciones];
	}

	@Override
	public void finish(Cromosoma mejor, String texto) {
		yMejor = yMej.stream().mapToDouble(Double::doubleValue).toArray();
		yMedia = yMed.stream().mapToDouble(Double::doubleValue).toArray();
		yMejorG = yMejG.stream().mapToDouble(Double::doubleValue).toArray();
		
		x = new double[yMej.size()];
		
		for(int i = 0; i < yMej.size(); i++) {
			x[i] = i;
		}
		
		this.removeAllPlotables();
		this.addLinePlot("Mejor Generacion", x, yMejor);
		this.addLinePlot("Media", x, yMedia);
		this.addLinePlot("Mejor Global", x, yMejorG);	
	}
}