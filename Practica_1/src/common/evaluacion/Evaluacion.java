package common.evaluacion;

import common.Cromosoma;
import practica1.Funcion;

public class Evaluacion {
	private static Funcion func = null;
	
	public static void setFuncion(Funcion funcion) {
		func = funcion;
	}
	
	public static double evaluar(Cromosoma crom) {		
		if(func == null) 
			return 0;
					
		crom.setApt(func.Evalua(crom));

		return crom.getApt();
	}
}
