package common;
import java.awt.List;
import java.util.ArrayList;

import common.evaluacion.Function_main;
import common.genes.Gen;
import common.genes.GenBi;
import common.genes.GenController;
import practicas.Problema;
import practicas.practica1.Funcion;


public class Cromosoma<T> implements Comparable<Cromosoma<T>>{
	
	private ArrayList<? extends Gen<T>> genes; //cadena de bits (genotipo)
	private int longitud; // Cantidad de genes del cromosma
	private double aptitud;//funci�n de evaluaci�n fitness adaptaci�n);
	private double puntuacion; //puntuaci�n relativa(aptitud/suma)
	private double punt_acum; //puntuaci�n acumulada para selecci�n
	private int tipo;
	private boolean minimizar;

	
	public Cromosoma() {};
	
	/**
	 * Constructor por defecto para los genes
	 * @param tipo de genes
	 * @param min
	 * @param max
	 */
	public Cromosoma(int tipo, Problema<T> func, double prec) {
		this.tipo = tipo;
		this.genes = new ArrayList<Gen<T>>();
		this.minimizar = func.MINIMIZAR;

		genes = func.crearGenes(prec);
		
/*		for(int i = 0; i < func.MAX.length; i++) 
			genes.add(new GenBi(i, func.MIN[i], func.MAX[i], prec));*/
		
/*		for (int i = 0; i< Function_main.MAX.length; i++) {
			genes.add(GenController.generarGen(tipo, i));
		}*/
		
		if(tipo == 0) {
			this.longitud = genes.size();
		}
	}
	
	/**
	 * Constructora de copia
	 * Para objetos lo suyo no es crear un clon directo, sino hacer un set de cada uno de sus elementos
	 * @param crom
	 */
	public Cromosoma (Cromosoma<T> crom) {
		this.genes = new ArrayList<>(crom.genes);
		
		
		
/*		for (Gen<T> g : crom.genes) {
			this.genes.add(new GenBi((GenBi)g));
			//this.genes.add(GenController.copiarGen(crom.tipo, g));
		}*/
		
		this.minimizar = crom.minimizar;
		this.longitud = crom.longitud;	
		this.aptitud = crom.aptitud;
		this.puntuacion = crom.puntuacion;
		this.punt_acum = crom.punt_acum;
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
	 * devuelve el tama�o de un gen booleano[]
	 * @return int
	 */
	public int getGenes_long() {
		int z = 0;
		for(int i = 0; i<genes.size(); i++) {
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
	 * @param n1 desde que posicion recibes del gen booleano[]
	 * @return boolean[n1-end]
	 */
	public Boolean getGenes_Bool(int n1, int pos) {
		return ((GenBi) this.genes.get(pos)).getGenes_Bool(n1);
	}
	/**
	 * @param n1 desde que posicion recibes del gen booleano[]
	 * @param n2 hasta que posicion recibes del gen booleano[]
	 * @return booleano[n1-n2]
	 */
	public Boolean[] getGenes_BoolArray(int n1, int n2) {
		return this.getGenes_BoolArray(n1, n2);
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
	
	/** SETTERS
	 * @param b
	 * @param n
	 */
	/*public void setAleloBi_1g(boolean b, int n, int pos) {
		((Gen_Bi) genes.get(pos)).setAlelo(b,n);
	}*/
	
	public void setGen(T caracteristica, int pos) {
		this.genes.get(pos).setCarateristica(caracteristica);
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
	
	/**
	 *  Compara dos aptitudes para saber si son el mismo cromosoma
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Cromosoma<T> o) {
		if(this.aptitud == o.aptitud)
			return 0;
		
		if (this.minimizar) {
			if (this.aptitud < o.aptitud)
				return 1;
			
			return -1;
		}
		else {
			if (this.aptitud < o.aptitud)
				return -1;

			return 1;
		}
	}
	
	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string =  "Cromosoma [genes= ";
				for(int i = 0; i < genes.size() ; i++) {
					string += this.genes.get(i).toString();
				}
				string += ", aptitud=" + aptitud	+ ", puntuaci�n=" + puntuacion + ", punt_acum=" + punt_acum + "]";
			return string;
	}	
}

