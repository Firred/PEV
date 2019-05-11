package practicas;

import java.util.ArrayList;

import common.Cromosoma;
import common.Poblacion;
import common.evaluacion.Evaluacion;
import common.genes.Gen;

public abstract class Problema<T> {
	
	public final boolean MINIMIZAR;
	public final int genes;
	
	public Problema(boolean minimizar, int genes) {
		this.MINIMIZAR = minimizar;
		this.genes = genes;
	}
	
	public int getNumGenes() {
		return this.genes;
	}
	
	public void calcularPuntuacion(Poblacion pobl) {
		double suma_aptitud = 0;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			crom.setApt(this.evalua(crom));
			crom.setX(crom.getApt());
		}
		
		revisarAdaptacion(pobl);
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			suma_aptitud += crom.getApt();
			crom.setPunt(crom.getApt()/suma_aptitud);
		}
		
		
		pobl.calcularMejorMedia();	
		pobl.calcularPuntAcum();
		
		return;
	}
	
	public void revisarAdaptacion(Poblacion pobl) {
		if(MINIMIZAR)
			revisarAdaptacionMin(pobl);
		else
			revisarAdaptacionMax(pobl);
	}
	
	public void revisarAdaptacionMin(Poblacion pobl) {
		double fMax = Double.MIN_VALUE;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			if(crom.getApt() > fMax) {
				fMax = crom.getApt();
			}
		}
		
		fMax = fMax * 1.05;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			crom.setApt(fMax-crom.getApt());
		}
	}
	
	public void revisarAdaptacionMax(Poblacion pobl) {
		double fMin = Double.MAX_VALUE;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			if(crom.getApt() < fMin) {
				fMin = crom.getApt();
			}
		}
		
		fMin = fMin * 1.05;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			crom.setApt(fMin+crom.getApt());
		}
	}
	
	public abstract double evalua(Cromosoma<T> crom);
	public abstract ArrayList<? extends Gen<T>> crearGenes(double... args);
}
