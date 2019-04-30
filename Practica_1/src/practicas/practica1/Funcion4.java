package practicas.practica1;

import common.Cromosoma;

public class Funcion4 extends Funcion{
	
	private static double[] min = new double[] {1};
	private static double[] max = new double[] {7};		
	
	public Funcion4() {
		super(min, max, true);
	}
	
	@Override
	public double evalua(Cromosoma<Double> ind_evaluar) {
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
		return "Funcion 4";
	}	
}
