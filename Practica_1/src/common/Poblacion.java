package common;

import java.util.ArrayList;
import java.util.Collections;

import common.Cromosoma;
import practicas.Problema;

public class Poblacion {
	private ArrayList<Cromosoma> individuos = new ArrayList();
	//private int Tpobl;
	private int generacion;
	private Cromosoma mejor;
	private double media;
	
	/**
	 * CONSTRUCTORES
	 * Constructor por defecto
	 */
	public Poblacion() {
		individuos = new ArrayList();
	}
	
	/** contructor para las poblaciones que se evaluaran
	 * @param tipo de genes
	 * @param tPobl
	 * @param generacion
	 * @param mejor
	 * @param precision
	 */
	public Poblacion(int tipo, int tPobl, int generacion, Problema<?> func, double prec) {
		for(int i = 0; i < tPobl; i++) {
			individuos.add(new Cromosoma(tipo, func, prec));
		}
		//Tpobl = tpobl;
		this.generacion = generacion;
		this.mejor = this.individuos.get(0);
	}
	
	/** GETTERS
	 *  get Tamaño poblacion
	 * @return 
	 */
	public int getTpobl() {
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
	public ArrayList<Cromosoma> getIndividuos() {
		return this.individuos;
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
	
	
	/** SETTERS
	 * set individuo en i
	 * @param individuos a introducir
	 * @param i posicion del individuo a tomar
	 */
	public void setIndividuos(Cromosoma individuos, int i) {
		this.individuos.set(i, new Cromosoma(individuos));
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
			elite[i] = this.individuos.get(0);
			this.individuos.remove(0);
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
		double m = 0;
		
		if(this.mejor == null)
			this.mejor = this.individuos.get(0);
		
		for(Cromosoma c : this.individuos) {
			m += c.getApt();
			
			if(c.compareTo(this.mejor) >= 1)
				this.mejor = c;
		}
		
		this.media = m/getTpobl();
	}
}