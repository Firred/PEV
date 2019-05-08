package practicas.practica1;

import common.Cromosoma;

public class Funcion3 extends Funcion{
	
	private static double[] min = new double[] {-10, -10};
	private static double[] max = new double[] {10, 10};		
	
	public Funcion3() {
		super(min, max, true);
	}
	
	@Override
	public double evalua(Cromosoma<Double> crom) {
		double g0 = (double) crom.getGen(0).getCaracteristica();
		double g1 = (double) crom.getGen(1).getCaracteristica();

		double sum1 = 0, sum2 = 0;
		
		for (int i = 1; i <= 5; i++) {
			sum1 += i*Math.cos((i+1)*g0+i);
			sum2 += i*Math.cos((i+1)*g1+i);
		}
		
		return sum1*sum2;
	}

	@Override
	public String toString() {
		return "Funcion 3";
	}
}