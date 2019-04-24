package common.evaluacion;

import common.Cromosoma;
import practicas.practica1.Funcion;
import practicas.practica1.Funcion1;
import practicas.practica1.Funcion2;
import practicas.practica1.Funcion3;
import practicas.practica1.Funcion4;

public class Function_Controller {
	private static final Funcion[] TIPOS = { new Funcion1(), new Funcion2(), new Funcion3(), new Funcion4() };
	private static Funcion  f_actual;

	/**
	 * @param pobl_evaluar
	 * @return 
	 * @return 
	 */
	public void Evalua(Cromosoma ind_evaluar, int tipo) {	
		ind_evaluar.setApt(TIPOS[tipo].evalua(ind_evaluar));
	}

	public static Funcion getF_actual() {
		return f_actual;
	}

	public static void setF_actual(int N_funcion) {
		Function_Controller.f_actual = TIPOS[N_funcion];
	}
	
	
}
