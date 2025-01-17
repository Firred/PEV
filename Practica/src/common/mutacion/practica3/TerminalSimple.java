package common.mutacion.practica3;

import common.Cromosoma;
import common.genes.GenArbol;
import common.mutacion.Mutacion;
import practicas.practica3.Tipo;

public class TerminalSimple extends Mutacion {

	@Override
	protected Cromosoma mutacion(Cromosoma crom) {
		GenArbol aux = (GenArbol)crom.getGen(0);
		int aleatorio;

		while(aux.getNumNodos() > 1) {
			aleatorio = this.rand.nextInt(aux.getHijos().size());
			
			aux = aux.getHijo(aleatorio);
		}
		
		aux.setCarateristica(Tipo.getTerminales()[this.rand.nextInt(Tipo.getTerminales().length)]);
		
		return crom;
	}
	
	@Override
	public String toString() {
		return "Terminal Simple";
	}
}
