package common;
import java.util.ArrayList;

import common.genes.Gen;
import common.genes.GenArbol;
import common.genes.GenBi;
import practicas.Problema;
import practicas.practica3.Practica3;


public class Cromosoma<T> implements Comparable<Cromosoma<T>>{
	
	private ArrayList<Gen<T>> genes; //cadena de bits (genotipo)
	private int longitud; // Cantidad de genes del cromosma
	private double aptitud;//funcion de evaluacion fitness (adaptacion);
	private double puntuacion; //puntuacion relativa(aptitud/suma)
	private double punt_acum; //puntuacion acumulada para selecci�n
	private double x; //Valor de adaptacion real
	private boolean minimizar;

	
	public Cromosoma() {
		this.genes = new ArrayList<>();
	};
	
	/**
	 * Constructor por defecto para los genes
	 * @param min
	 * @param max
	 */
	public Cromosoma(Problema<T> func, double prec) {
		this.genes = new ArrayList<Gen<T>>();
		this.minimizar = func.MINIMIZAR;
		
		genes = (ArrayList<Gen<T>>) func.crearGenes(prec);
	}
	
	/**
	 * Constructora de copia
	 * Para objetos lo suyo no es crear un clon directo, sino hacer un set de cada uno de sus elementos
	 * @param crom
	 */
	public Cromosoma (Cromosoma<T> crom) {
		this.genes = new ArrayList<>(crom.genes);
		
		this.x = crom.x;
		this.minimizar = crom.minimizar;
		this.longitud = crom.longitud;	
		this.aptitud = crom.aptitud;
		this.puntuacion = crom.puntuacion;
		this.punt_acum = crom.punt_acum;

		if(crom.getNumGenes() > 0 && GenArbol.class.isAssignableFrom(crom.getGen(0).getClass())) {
			this.genes.set(0, (Gen<T>) new GenArbol((GenArbol)crom.getGen(0), null));
		}
	}
	
	/**
	 * @param pos
	 * Devuelve el gen de la pos
	 * @return gen[pos]
	 */
	public Gen<T> getGen(int pos) {
		return genes.get(pos);
	}
	
	
	/** GETTERS
	 * devuelve el tama�o de la suma de los genes booleanos
	 * @return int
	 */
	public int getGenes_long() {
		int z = 0;
		for(int i = 0; i < genes.size(); i++) {
			z += genes.get(i).getTam();
		}
		return z;
	}
	
	/**
	 * Devuelve el numero de genes del cromosoma
	 * @return genes.size()
	 */
	public int getNumGenes() {
		return this.genes.size();
	}
	
	
	public ArrayList<? extends Gen<T>> getGenes() {
		return this.genes;
	}
	
	/**
	 * Devuelve la aptitud del cromosoma
	 * @return aptitud
	 */
	public double getApt() {
		return this.aptitud;
	}
	
	/**
	 * Devuelve la puntuaci�n acumulada del cromosoma
	 * @return punt_Acum
	 */
	public double getPunt_Acum() {
		return this.punt_acum;
	}

	/**
	 * Devuelve la puntuaci�n del cromosoma
	 * @return puntuaci�n
	 */
	public double getPunt() {
		return this.puntuacion;
	}
	
	public double getX() {
		return x;
	}
	
	public <M extends Gen<T>> void setGen(M gen, int pos) {
		this.genes.set(pos, gen);
	}
	
	
	/**
	 * @param apt Aptitud
	 */
	public void setApt(double apt) {
		this.aptitud = apt;
	}
	
	/**
	 * @param punt Puntuaci�n 
	 */
	public void setPunt(double punt) {
		this.puntuacion = punt;
	}
	
	/**
	 * @param punt Puntuaci�n acumulada
	 */
	public void setPunt_Acum(double punt) {
		this.punt_acum = punt;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public int compararX(Cromosoma<T> o) {		
		if(this.minimizar) 
			return this.x < o.x ? 1 : (this.x == o.x ? 0 : -1);	
		else
			return this.x > o.x ? 1 : (this.x == o.x ? 0 : -1);
	}
	
	/**
	 *  Compara dos aptitudes para saber cual es mayor
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Cromosoma<T> o) {
		if(this.aptitud == o.aptitud)
			return 0;
		
		if (this.aptitud < o.aptitud)
			return -1;

		return 1;
	}
	
	@Override
	public String toString() {
		String string =  "Cromosoma: Aptitud: " + aptitud + " NGenes: " + this.getNumGenes() + " [genes= ";
		
		for(int i = 0; i < genes.size() ; i++) {
			string += this.genes.get(i).toString();
		}
		
		string += "], puntuacion=" + puntuacion + ", punt_acum=" + punt_acum;
		
		return string;
	}	
}

