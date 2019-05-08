package practicas.practica1;

import common.Cromosoma;

public class Funcion1 extends Funcion{

	private static double[] min = new double[] {-3, 4.1};
	private static double[] max = new double[] {12.1, 5.8};		
	
	public Funcion1() {
		super(min, max, false);
	}
	
	@Override
	public double evalua(Cromosoma<Double> crom) {
		double g0 = (double) crom.getGen(0).getCaracteristica();
		double g1 = (double) crom.getGen(1).getCaracteristica();	
		return 21.5+g0*Math.sin(4*Math.PI*g0)+g1*Math.sin(20*Math.PI*g1);
	}

	@Override
	public String toString() {
		return "Funcion 1";
	}
}
