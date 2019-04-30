package practicas.practica1;

import java.util.ArrayList;

import common.Cromosoma;
import common.evaluacion.Function_main;
import common.genes.Gen;

public class Funcion2 extends Funcion{
	
	private static double[] min = new double[] {-512, -512};
	private static double[] max = new double[] {512, 512};		
	
	public Funcion2() {
		super(min, max, true);
	}
	
	@Override
	public double evalua(Cromosoma<Double> ind_evaluar) {
		double g0 = (double) ind_evaluar.getGen(0).getCaracteristica();
		double g1 = (double) ind_evaluar.getGen(1).getCaracteristica();
		return -(g1+47)*Math.sin(Math.sqrt(Math.abs(g1+ g0/2 +47)))-g0*Math.sin(Math.sqrt(Math.abs(g0-g1+47)));
	}

	@Override
	public String toString() {
		return "Funcion 2";
	}
}