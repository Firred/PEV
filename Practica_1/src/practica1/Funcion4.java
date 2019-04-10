package practica1;

import common.Cromosoma;
import common.evaluacion.Function_main;

public class Funcion4 extends Funcion{
	
	private static double[] min = new double[] {1};
	private static double[] max = new double[] {7};		
	
	public Funcion4() {
		super(min, max, true);
		
		//Set_Function();
	}

	public void Set_Function() {
		Function_main.Set_Arrays(7);
		for (int i = 0; i<7; i++) {
			Function_main.MAX[i] = 0;
			Function_main.MIN[i] = Math.PI;
			Function_main.PRECISION[i] = 0.0001;
		}
	}
	
	@Override
	public double Evalua(Cromosoma ind_evaluar) {
		int int1 = 0;
		double[] g = new double[ind_evaluar.getNumGenes()];
		for(int j = 0 ; j< ind_evaluar.getNumGenes() ; j++) {
			g[j] = (double) ind_evaluar.getGen(j).getCaracteristica();
			for(int i =0 ; i<7; i++) {
				int1 += Math.sin(g[j])*Math.pow(20, Math.sin((i+1)*Math.pow(2, g[i])/Math.PI));
			}
		}
		
		return -int1;
	}

	@Override
	public String toString() {
		return "Función 4";
	}
	
	
}
