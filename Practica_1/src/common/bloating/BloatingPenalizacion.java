package common.bloating;

import common.Cromosoma;
import common.Poblacion;
import common.genes.GenArbol;
import practicas.ProblemaArbol;

public class BloatingPenalizacion implements Bloating {
	public void ejecutar(Poblacion pobl, ProblemaArbol func) {
		int tam = 0, nodos;
		double cov = 0, var = 0, c;
	
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
