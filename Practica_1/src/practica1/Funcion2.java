package practica1;

import common.Cromosoma;
import common.evaluacion.Function_main;

public class Funcion2 extends Funcion{
	
	private static double[] min = new double[] {-512, -512};
	private static double[] max = new double[] {512, 512};		
	
	public Funcion2() {
		super(min, max, true);
		
		//Set_Function();
	}

	public void Set_Function() {
		Function_main.Set_Arrays(2);
		for (int i = 0; i<2; i++) {
			Function_main.MAX[i] = 512;
			Function_main.MIN[i] = -512;
			Function_main.MAX_MIN = true;
			Function_main.PRECISION[i] = 0.0001;
		}
	}
	
	@Override
	public double Evalua(Cromosoma ind_evaluar) {
		double g0 = (double) ind_evaluar.getGen(0).getCaracteristica();
		double g1 = (double) ind_evaluar.getGen(1).getCaracteristica();
		return -(g1+47)*Math.sin(Math.sqrt(Math.abs(g1+ g0/2 +47)))-g0*Math.sin(Math.sqrt(Math.abs(g0-g1+47)));
	}

	@Override
	public String toString() {
		return "Función 2";
	}

}
