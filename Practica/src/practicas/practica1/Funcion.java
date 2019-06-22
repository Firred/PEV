package practicas.practica1;

import java.util.ArrayList;

import common.cruce.Reproduccion;
import common.genes.Gen;
import common.genes.GenBi;
import practicas.Problema;

public abstract class Funcion extends Problema<Double> {
	
	public final double[] MIN;
	public final double[] MAX;
	private double prec;

	public Funcion(double[] min, double[] max, boolean minimizar, Reproduccion rep) {
		super(minimizar, max.length, rep);
		this.MAX = min;
		this.MIN = max;
		prec = 0.001;
	}
	
	public Funcion(double[] min, double[] max, boolean minimizar, int length, Reproduccion rep) {
		super(minimizar, length, rep);
		this.MAX = min;
		this.MIN = max;
	}
	
	public double getPrec() {
		return prec;
	}

	public void setPrec(double prec) {
		this.prec = prec;
	}

	@Override
	public ArrayList<? extends Gen<Double>> crearGenes() {
		
		ArrayList<GenBi> genes = new ArrayList<>();
		
		for(int i = 0; i < MAX.length; i++) 
			genes.add(new GenBi(i, MIN[i], MAX[i], this.prec));
		
		return genes;
	}
	
	public abstract String toString();
}
