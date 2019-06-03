package practica3;

import java.util.ArrayList;
import common.genes.Gen;
import practicas.ProblemaNoBinario;

public abstract class ProblemaArbol<T> extends ProblemaNoBinario<T> {

	private int nMax;
	
	public ProblemaArbol(boolean minimizar) {
		super(minimizar, 1);
		this.nMax = 5;
	}

	public int getNMax() {
		return nMax;
	}

	public void setNMax(int nMax) {
		this.nMax = nMax;
	}

	@Override
	public ArrayList<? extends Gen<T>> crearGenes(double... args) {return null;}
	
	/**
	 * Devuelve un enum Tipo aleatorio de terminal
	 * @return Tipo de terminal
	 */
	public abstract Tipo obtenerTerminal();
	/**
	 * Devuelve un enum Tipo aleatorio de funcion
	 * @return Tipo de funcion
	 */
	public abstract Tipo obtenerFuncion();
	/**
	 * Devuelve un enum Tipo aleatorio de terminal o de funcion
	 * @return Tipo de terminal o funcion
	 */
	public abstract Tipo obtenerTodos();
	/**
	 * Devuelve el numero de ramificaciones en el arbol que corresponden
	 * al Tipo introducido.
	 * Nota: Los Tipo de terminal tambien deben de tenerse en cuenta
	 * y devolver 0.
	 * @param tipo
	 * @return Numero de ramificaciones del Tipo
	 */
	public abstract int ramificacionesFuncion(Tipo tipo);
}
