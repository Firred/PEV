package common.bloating;

import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.genes.GenArbol;
import practicas.ProblemaArbol;

public class BloatingTarpeian implements Bloating {

	private int n = 3;	
	private Random rand = new Random();
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void ejecutar(Poblacion pobl, ProblemaArbol func) {
		int tam = 0;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			tam += ((GenArbol)crom.getGen(0)).getNumNodos();
		}
		
		tam = tam/pobl.getTPoblacion();
		
		for(Cromosoma crom : pobl.getIndividuos()) {			
			if(((GenArbol)crom.getGen(0)).getNumNodos() > tam && (double)1.0/n > rand.nextDouble()) {
				func.bajoFitness(crom);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Tarpeian";
	}
}
