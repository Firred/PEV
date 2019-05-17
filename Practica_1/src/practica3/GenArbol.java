package practica3;

import java.util.ArrayList;

import common.genes.Gen;

public class GenArbol extends Gen<Tipo> {

	private String dato;
	private int numNodos;
	private int profundidad;
	private ArrayList<GenArbol> hijos;
	private GenArbol padre;
	
	public GenArbol() {}
	
	public GenArbol(Tipo tipo) {
		super.setCarateristica(tipo);
		this.numNodos = 1;
		this.profundidad = 1;
	}
	
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
		if(padre != null) {
			padre.setNumNodos(padre.getNumNodos()-this.numNodos+numNodos);
		}
		
		this.numNodos = numNodos;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		if(padre != null) {
			padre.setProfundidad(padre.getProfundidad()-this.profundidad+profundidad);
		}
		
		this.profundidad = profundidad;
	}

	public ArrayList<GenArbol> getHijos() {
		return hijos;
	}
	
	public GenArbol getHijo(int pos) {
		return this.hijos.get(pos);
	}

	public void setHijos(ArrayList<GenArbol> hijos) {
		this.hijos = hijos;
	}
	
	public <T extends Gen<Tipo>> void setHijo(T hijo, int pos) {
		this.hijos.set(pos, (GenArbol)hijo);
	}
	
	public <T extends Gen<Tipo>> void addHijo(T hijo) {
		GenArbol gArb = (GenArbol)hijo;
		this.hijos.add(gArb);
		this.numNodos += gArb.getNumNodos();
		this.profundidad += gArb.getProfundidad();
		gArb.setPadre(this);
	}

	public GenArbol getPadre() {
		return padre;
	}

	public void setPadre(GenArbol padre) {
		this.padre = padre;
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
	
	public void insertarNodo(GenArbol nodoAntiguo, GenArbol nodoNuevo) {		
		for(int i = 0; i < this.hijos.size(); i++) {
			if(this.hijos.get(i).equals(nodoAntiguo)) {
				this.hijos.set(i, nodoNuevo);
				this.setNumNodos(this.numNodos-nodoAntiguo.getNumNodos()+nodoNuevo.getNumNodos());
				this.setProfundidad(this.profundidad-nodoAntiguo.getProfundidad()+nodoNuevo.getProfundidad());
				
				nodoAntiguo.setPadre(null);
				nodoNuevo.setPadre(this);
			}
		}
	}
}

