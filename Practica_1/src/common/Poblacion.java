package common;

import java.util.ArrayList;
import java.util.Collections;

import common.Cromosoma;
import practica3.CromosomaArbol;
import practica3.InicializacionPoblacion;
import practica3.Practica3;
import practica3.ProblemaArbol;
import practicas.Problema;

public class Poblacion {
	private ArrayList<Cromosoma> individuos;
	private int tPobl;
	private int generacion;
	private Cromosoma mejor;
	private double media;
	private double aptMedia;
	
	/**
	 * CONSTRUCTORES
	 * Constructor por defecto
	 */
	public Poblacion(int i) {
		individuos = new ArrayList();
	}
	
	/** contructor para las poblaciones que se evaluaran
	 * @param tipo de genes
	 * @param tPobl
	 * @param generacion
	 * @param mejor
	 * @param precision
	 */
	public Poblacion(int tPobl, int generacion, Problema<?> func, double prec) {
		this.individuos = new ArrayList<>();
		
		if(ProblemaArbol.class.isAssignableFrom(func.getClass())) {
			this.individuos = InicializacionPoblacion.inicializarPoblacion(tPobl, (ProblemaArbol) func);
		}
		else {
			for(int i = 0; i < tPobl; i++) {
				individuos.add(new Cromosoma(func, prec));
			}
		}
		
		this.tPobl = tPobl;
		this.generacion = generacion;
		this.mejor = this.individuos.get(0);
	}
	
	//GETTERS
	/** 
	 *  get Tamaño poblacion
	 * @return 
	 */
	public int getTPoblacion() {
		return this.individuos.size();
	}

	/** get individuo en i
	 * @param i posicion del individuo a tomar
	 * @return
	 */
	public Cromosoma getIndividuos(int i) {
		return individuos.get(i);
	}
	
	/**
	 * Devuelve el array de Cromosomas de la población
	 * @return individuos
	 */
	public ArrayList<? extends Cromosoma> getIndividuos() {
		return this.individuos;
	}
	
	/**
	 * Devuelve el tamano original de la poblacion
	 * @return
	 */
	public int getTPobl() {
		return this.tPobl;
	}
	
	/**
	 * Devuelve la generación actual de la población
	 * @return
	 */
	public int getGeneracion() {
		return this.generacion;
	}
	
	public Cromosoma getMejor() {
		return this.mejor;
	}
	
	public double getMedia() {
		return this.media;
	}
	
	public double getAptMedia() {
		return aptMedia;
	}
	
	
	// SETTERS
	/** 
	 * set individuo en i
	 * @param individuos a introducir
	 * @param i posicion del individuo a tomar
	 */
	public void setIndividuo(Cromosoma individuos, int i) {
		this.individuos.set(i, new Cromosoma(individuos));
	}
	
	public void setIndividuos(ArrayList<Cromosoma> individuos) {
		this.individuos = individuos;
	}
	
	public void setTPobl(int tPobl) {
		this.tPobl = tPobl;
	}
	
	/**
	 * @param generacion
	 */
	public void setGeneracion (int generacion) {
		this.generacion = generacion;
	}
	
	/**
	 * Establece la puntuación acumulada de cada individuo de la población.
	 * Llamar en la evaluación.
	 */
	public void calcularPuntAcum() {
		double acum = 0;
		
		for (Cromosoma c : individuos) {
			c.setPunt_Acum(acum+=c.getPunt());
		}
	}
	
	/**
	 * Añade el cromosoma crom a la lista de individuos.
	 * @param crom
	 */
	public void addIndividuo(Cromosoma crom) {
		this.individuos.add(new Cromosoma(crom));
		//this.Tpobl++;
	}
	
	
	/**
	 * Saca los tamElite mejores individuos de la población y los devuelve
	 * @param tamElite
	 * @return los tamElite mejores individuos
	 */
	public Cromosoma[] separaMejores(int tamElite) {
		Cromosoma[] elite = new Cromosoma[tamElite];
		
		Collections.sort(this.individuos, Collections.reverseOrder());
		
		for (int i = 0; i < tamElite; i++) {
			elite[i] = new Cromosoma(this.individuos.remove(0));
		}
		
		this.calcularPuntAcum();
		
		return elite;
	}
	
	/**
	 * Mete los individuos en la población
	 * @param individuos
	 */
	public void incluye(Cromosoma[] individuos) {
		for(Cromosoma c : individuos) {
			this.individuos.add(c);
		}
	}
	
	public void calcularMejorMedia() {
		double m = 0, apt = 0;
		
		if(this.mejor == null)
			this.mejor = this.individuos.get(0);
		
		for(Cromosoma c : this.individuos) {
			m += c.getX();
			apt += c.getApt();
			
			if(c.compareTo(this.mejor) >= 1)
				this.mejor = c;
		}
		
		this.media = m/getTPoblacion();
		this.aptMedia = apt/getTPoblacion();
	}
}