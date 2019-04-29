package common.evaluacion;

import common.Cromosoma;
import practicas.Problema;

public class Evaluacion {
	private static Problema<?> func = null;
	
	public static void setFuncion(Problema<?> funcion) {
		func = funcion;
	}
	
	public static double evaluar(Cromosoma crom) {		
		if(func == null) 
			return 0;
					
		crom.setApt(func.evalua(crom));

		return crom.getApt();
	}
}
