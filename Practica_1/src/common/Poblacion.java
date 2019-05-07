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
	 *  get Tama�o poblacion
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
	 * Devuelve el array de Cromosomas de la poblaci�n
	 * @return individuos
	 */
	public ArrayList<Cromosoma> getIndividuos() {
		return this.individuos;
	}
	
	
	/**
	 * Devuelve la generaci�n actual de la poblaci�n
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
	 * Establece la puntuaci�n acumulada de cada individuo de la poblaci�n.
	 * Llamar en la evaluaci�n.
	 */
	public void calcularPuntAcum() {
		double acum = 0;
		
		for (Cromosoma c : individuos) {
			c.setPunt_Acum(acum+=c.getPunt());
		}
	}
	
	/**
	 * A�ade el cromosoma crom a la lista de individuos.
	 * @param crom
	 */
	public void addIndividuo(Cromosoma crom) {
		this.individuos.add(new Cromosoma(crom));
		//this.Tpobl++;
	}
	
	
	/**
	 * Saca los tamElite mejores individuos de la poblaci�n y los devuelve
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
	 * Mete los individuos en la poblaci�n
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