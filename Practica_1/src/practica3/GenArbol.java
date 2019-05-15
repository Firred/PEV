package practica3;

import java.util.ArrayList;

import common.genes.Gen;

public class GenArbol extends Gen<Tipo> {

	private String dato;
	private int numNodos;
	private int profundidad;
	private ArrayList<GenArbol> hijos;
	
	public GenArbol(GenArbol gen) {
		super((GenArbol)gen);
	}
	
	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public int getNumNodos() {
		return numNodos;
	}

	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public ArrayList<GenArbol> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<GenArbol> hijos) {
		this.hijos = hijos;
	}
	
	public void setHijo(GenArbol hijo, int pos) {
		this.hijos.set(pos, hijo);
	}
	
	@Override
	public String toString() {
		return "String GenArbol: Nada";
	}
	
	/**
	 * Devuelve el GenArbol correspondiente al numero de nodo introducido.
	 * La busqueda del nodo se hace considerando que el arbol se encuentra balanceado 
	 * (el valor a la izquierda de un nodo siempre sera menor que este y a la derecha sera mayor)
	 * Dado que puede haber nodos con 3 hijos, el hijo "central" se considerara mayor que el padre 
	 * y menor que el hijo derecho.
	 * @param nodo
	 * @return
	 */
	public GenArbol buscarNodo(int nodo) {
		if(nodo > 1 && this.numNodos > 1) {
			if(nodo >= hijos.get(0).getNumNodos()) {
				if(hijos.size() == 3) {
					if(nodo > hijos.get(0).getNumNodos()+hijos.get(1).getNumNodos()) {
						return hijos.get(2).buscarNodo(nodo-hijos.get(0).numNodos-hijos.get(1).numNodos);
					}
					else {
						return hijos.get(1).buscarNodo(nodo-hijos.get(0).numNodos);
					}
				}
				else {
					return hijos.get(1).buscarNodo(nodo-hijos.get(0).numNodos);
				}		
			}
			else {
				return hijos.get(0).buscarNodo(nodo);
			}
		}

		return this;
	}
}

