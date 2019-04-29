package practicas;

import java.util.ArrayList;

import common.Cromosoma;
import common.genes.Gen;

public abstract class Problema<T> {
	
	public final boolean MINIMIZAR;
	public final int genes;
	
	public Problema(boolean minimizar, int genes) {
		this.MINIMIZAR = minimizar;
		this.genes = genes;
	}
	
	public abstract double evalua(Cromosoma<T> crom);
	public abstract ArrayList<? extends Gen<T>> crearGenes(double... args);
}
