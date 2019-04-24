package practicas;

import java.util.ArrayList;

import common.Cromosoma;
import common.genes.Gen;

public abstract class Problema<T> {
	
	public final boolean MINIMIZAR;
	
	public Problema(boolean minimizar) {
		this.MINIMIZAR = minimizar;
	}
	
	public abstract double evalua(Cromosoma<T> crom);
	public abstract ArrayList<? extends Gen<T>> crearGenes(double... args);
}
