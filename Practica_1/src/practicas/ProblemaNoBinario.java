package practicas;

import common.cruce.Reproduccion;
import common.mutacion.Mutacion;

public abstract class ProblemaNoBinario<T> extends Problema<T> {

	private Reproduccion reproduccion;
	private Mutacion mutacion;
	
	public ProblemaNoBinario(boolean minimizar, int genes) {
		super(minimizar, genes);
	}

	public Reproduccion getReproduccion() {
		return reproduccion;
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
	
	public void setReproduccion(Reproduccion reproduccion) {
		this.reproduccion = reproduccion;
	}
	
	public void setMutacion(Mutacion mutacion) {
		this.mutacion = mutacion;
	}
}