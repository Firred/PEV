package common.evaluacion;

import common.Cromosoma;
import practicas.practica1.Funcion;

public class Evaluacion {
	private static Funcion func = null;
	
	public static void setFuncion(Funcion funcion) {
		func = funcion;
	}
	
	public static double evaluar(Cromosoma crom) {		
		if(func == null) 
			return 0;
					
		crom.setApt(func.evalua(crom));

		return crom.getApt();
	}
}
