package common.mutacion.practica3;

import common.Cromosoma;
import common.InicializacionPoblacion;
import common.genes.GenArbol;
import common.mutacion.Mutacion;
import interfaz.controlador.Controlador;
import practicas.ProblemaArbol;
import practicas.practica3.Tipo;

public class MutacionInicializacion extends Mutacion {

	@Override
	protected Cromosoma mutacion(Cromosoma crom) {
		GenArbol aux = (GenArbol)crom.getGen(0);
		int aleatorio, profOriginal;			
		
		if(aux.getNumNodos() > 1) {
			aleatorio = this.rand.nextInt(aux.getHijos().size());
			aux = aux.getHijo(aleatorio);
			
			while(aux.getNumNodos() > 1 && (1.0/(aux.getProfundidad()) > rand.nextDouble())) {
				aleatorio = this.rand.nextInt(aux.getHijos().size());
				
				aux = aux.getHijo(aleatorio);
			}
			
			ProblemaArbol func = (ProblemaArbol)Controlador.getInstance().getAG().getFuncion();
			profOriginal = aux.getProfundidad();
			aux.getPadre().insertarNodo(aux, InicializacionPoblacion.inicializarArbol(rand.nextInt(profOriginal+1)+1, func));
			
			if(aux.getNumNodos() == 1)
				aux = aux.getPadre();
			
			aux.setCarateristica(Tipo.getConOperandos(aux.getCaracteristica().operandos())
					[this.rand.nextInt(Tipo.getConOperandos(aux.getCaracteristica().operandos()).length)]);
		}
		
		return crom;
	}
	
	@Override
	public String toString() {
		return "Inicializacion";
	}
}
