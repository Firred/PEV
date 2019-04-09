package practica1;

import common.Cromosoma;
import common.evaluacion.Function_main;

public class Funcion1 extends Funcion{

	private static double[] min = new double[] {-3, 4.1};
	private static double[] max = new double[] {12.1, 5.8};		
	
	public Funcion1() {
		super(min, max, false);
		
		
		//Set_Function();
	}

	public void Set_Function() {
		Function_main.Set_Arrays(2);
		Function_main.MAX[0] = 12.1;
		Function_main.MIN[0] = -3;
		Function_main.MAX[1] = 5.8;
		Function_main.MIN[1] = 4.1;
		Function_main.MAX_MIN = false;
		for (int i = 0; i<2; i++) {
			Function_main.PRECISION[i] = 0.0001;
		}
	}
	
	@Override
	public double Evalua(Cromosoma ind_evaluar) {
		double g0 = (double) ind_evaluar.getGen(0).getCaracteristica();
		double g1 = (double) ind_evaluar.getGen(1).getCaracteristica();	
		return 21.5+g0*Math.sin(4*Math.PI*g0)+g1*Math.sin(20*Math.PI*g1);
	}

	@Override
	public String toString() {
		return "Función 1";
	}

}
