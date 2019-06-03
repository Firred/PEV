package practica3;

import java.util.Random;

import common.Cromosoma;
import common.Poblacion;

public class BloatingTarpeian {

	private int n = 2;	
	private Random rand = new Random();
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void ejecutar(Poblacion pobl) {
		int tam = 0;
		GenArbol gen;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			tam += ((GenArbol)crom.getGen(0)).getNumNodos();
		}
		
		tam = tam/pobl.getTPoblacion();
		
		int i = 0;
		while(i < pobl.getTPoblacion()) {
			gen = (GenArbol)pobl.getIndividuos(i).getGen(0);
			
			if(gen.getNumNodos() > tam && (double)1.0/n > rand.nextDouble()) {
				pobl.getIndividuos().remove(i);
			}
			else {
				i++;
			}
		}
	}
}
