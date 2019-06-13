package practicas;

import common.cruce.Reproduccion;
import common.mutacion.Mutacion;

public abstract class ProblemaNoBinario<T> extends Problema<T> {

	private Mutacion mutacion;
	
	public ProblemaNoBinario(boolean minimizar, int genes, Reproduccion rep) {
		super(minimizar, genes, rep);
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
	
	public void setMutacion(Mutacion mutacion) {
		this.mutacion = mutacion;
	}
}