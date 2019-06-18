package practicas;

import java.util.ArrayList;

import common.Cromosoma;
import common.Poblacion;
import common.bloating.Bloating;
import common.bloating.BloatingTarpeian;
import common.cruce.Reproduccion;
import common.genes.Gen;
import practicas.practica3.Tipo;

public abstract class ProblemaArbol<T> extends ProblemaNoBinario<T> {

	private int nMax;
	private Bloating bloating;
	
	public ProblemaArbol(boolean minimizar, Reproduccion rep) {
		super(minimizar, 1, rep);
		this.nMax = 3;
		this.bloating = new BloatingTarpeian();
	}

	public int getNMax() {
		return nMax;
	}

	public void setNMax(int nMax) {
		this.nMax = nMax;
	}
	
	public Bloating getBloating() {
		return bloating;
	}

	public void setBloating(Bloating bloating) {
		this.bloating = bloating;
	}

	@Override
	public ArrayList<? extends Gen<T>> crearGenes() {return null;}
	
	@Override
	public void calcularPuntuacion(Poblacion pobl) {
		double suma_aptitud = 0;

		for(Cromosoma crom : pobl.getIndividuos()) {
			crom.setApt(this.evalua(crom));
			crom.setX(crom.getApt());
		}
		
		if(this.bloating != null) {
			this.bloating.ejecutar(pobl, this);
		}
		
		revisarAdaptacion(pobl);
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			suma_aptitud += crom.getApt();	
		}
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			crom.setPunt(crom.getApt()/suma_aptitud);
			
		}
		
		pobl.calcularMejorMedia();	
		pobl.calcularPuntAcum();
		
		return;
	}
	
	public void calcularPuntuacionNormal(Poblacion pobl) {
		double suma_aptitud = 0;
		
		if(this.bloating == null)
			return;
		
		for(Cromosoma crom : pobl.getIndividuos()) {
			crom.setApt(this.evalua(crom));
			crom.setX(crom.getApt());
		}
		
		revisarAdaptacion(pobl);
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			suma_aptitud += crom.getApt();	
		}
		
		for(Cromosoma crom : pobl.getIndividuos()) {	
			crom.setPunt(crom.getApt()/suma_aptitud);
			
		}
		
		pobl.calcularMejorMedia();	
		pobl.calcularPuntAcum();
		
		return;
	}
	
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
	/**
	 * Reduce el fitness del cromosoma introducido
	 * @param crom
	 * @return
	 */
	public abstract void bajoFitness(Cromosoma crom);
	/**
	 * Devuelve el maximo de nodos que la funcion permite.
	 * Se usa para eliminar automaticamente los arboles 
	 * con mas de ese numero de nodos.
	 * @return
	 */
//	public abstract int maximoNodos();
}
