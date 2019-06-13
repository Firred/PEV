package practicas.practica1;

import java.util.ArrayList;

import common.cruce.Reproduccion;
import common.genes.Gen;
import common.genes.GenBi;
import practicas.Problema;

public abstract class Funcion extends Problema<Double> {
	
	public final double[] MIN;
	public final double[] MAX;

	public Funcion(double[] min, double[] max, boolean minimizar, Reproduccion rep) {
		super(minimizar, max.length, rep);
		this.MAX = min;
		this.MIN = max;
	}
	
	public Funcion(double[] min, double[] max, boolean minimizar, int length, Reproduccion rep) {
		super(minimizar, length, rep);
		this.MAX = min;
		this.MIN = max;
	}
	
	@Override
	public ArrayList<? extends Gen<Double>> crearGenes(double... args) {
		if(args.length != 1)
			return null;
		
		ArrayList<GenBi> genes = new ArrayList<>();
		
		for(int i = 0; i < MAX.length; i++) 
			genes.add(new GenBi(i, MIN[i], MAX[i], args[0]));
		
		return genes;
	}
	
	public abstract String toString();
}
