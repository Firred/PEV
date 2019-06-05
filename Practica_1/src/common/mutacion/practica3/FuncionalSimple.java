package common.mutacion.practica3;

import common.Cromosoma;
import common.mutacion.Mutacion;
import practica3.GenArbol;
import practica3.Tipo;

public class FuncionalSimple extends Mutacion {

	@Override
	protected Cromosoma mutacion(Cromosoma crom) {
		GenArbol aux = (GenArbol)crom.getGen(0);
		int aleatorio;			
		
		while((aux.getCaracteristica().operandos() > 0 && Tipo.getConOperandos(aux.getCaracteristica().operandos()).length == 1) || 
				(1.0/(aux.getProfundidad()-1) < rand.nextDouble())) {
			aleatorio = this.rand.nextInt(aux.getHijos().size());
			
			aux = aux.getHijo(aleatorio);
		}
		
		if(aux.getNumNodos() == 1)
			aux = aux.getPadre();
		
		aux.setCarateristica(Tipo.getConOperandos(aux.getCaracteristica().operandos())
				[this.rand.nextInt(Tipo.getConOperandos(aux.getCaracteristica().operandos()).length)]);
		
		return crom;
	}

	@Override
	public String toString() {
		return "Funcional Simple";
	}
}
