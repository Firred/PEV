package practica1;

import common.Cromosoma;
import common.evaluacion.Function_main;

public class Funcion3 extends Funcion{
	
	private static double[] min = new double[] {-10, -10};
	private static double[] max = new double[] {10, 10};		
	
	public Funcion3() {
		super(min, max, true);
		
		//Set_Function();
	}

	public void Set_Function() {
		Function_main.Set_Arrays(2);
		for (int i = 0; i<2; i++) {
			Function_main.MAX[i] = 10;
			Function_main.MIN[i] = -10;
			Function_main.PRECISION[i] = 0.0001;
		}
	}
	
	@Override
	public double Evalua(Cromosoma ind_evaluar) {
		double g0 = (double) ind_evaluar.getGen(0).getCaracteristica();
		double g1 = (double) ind_evaluar.getGen(1).getCaracteristica();

		double sum1 = 0, sum2 = 0;
		
		for (int i = 1; i <= 5; i++) {
			sum1 += i*Math.cos((i+1)*g0+i);
			sum2 += i*Math.cos((i+1)*g1+i);
		}
		
		return sum1*sum2;
	}

	@Override
	public String toString() {
		return "Función 3";
	}
}
