package practica3;

import common.Cromosoma;
import common.Poblacion;

public class BloatingPenalizacion implements Bloating {
	public void ejecutar(Poblacion pobl, ProblemaArbol func) {
		int tam = 0, nodos;
		GenArbol gen;
		double cov = 0, var = 0, c;
		int i = 0;
		
		while(i < pobl.getTPoblacion()) {
			if(((GenArbol)pobl.getIndividuos(i).getGen(0)).getNumNodos() > func.maximoNodos()) {
				pobl.getIndividuos().remove(i);	
			}
			else
				i++;
		}
				
		for(Cromosoma crom : pobl.getIndividuos()) {
			nodos = ((GenArbol)crom.getGen(0)).getNumNodos();
			tam += nodos;
			cov += nodos*crom.getApt();
		}
		
		tam = tam/pobl.getTPoblacion();
		cov -= tam*pobl.getAptMedia();
		cov = cov/pobl.getTPoblacion();
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			var += Math.pow(((GenArbol)crom.getGen(0)).getNumNodos()-tam, 2);
		}
		
		var = var/pobl.getTPoblacion();
		
		c = cov/var;
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			nodos = ((GenArbol)crom.getGen(0)).getNumNodos();
			
			crom.setApt(func.evalua(crom)- c*nodos);
			
			if(crom.getApt() < 0)
				crom.setApt(0);
		}
	}
	
	@Override
	public String toString() {
		return "Penalizacion";
	}
}
