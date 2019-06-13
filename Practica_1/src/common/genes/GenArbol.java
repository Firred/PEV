package common.genes;

import java.util.ArrayList;

import practicas.practica3.Tipo;

public class GenArbol extends Gen<Tipo> {

	private int numNodos;
	private int profundidad;
	private ArrayList<GenArbol> hijos;
	private GenArbol padre;
	
	public GenArbol() {
		this.hijos = new ArrayList<>();
	}
	
	public GenArbol(Tipo tipo) {
		super.setCarateristica(tipo);
		this.numNodos = 1;
		this.profundidad = 1;
		this.hijos = new ArrayList<>();
	}
	
	public GenArbol(GenArbol gen, GenArbol padre) {
		super((GenArbol)gen);
		this.setCarateristica(Tipo.valueOf(gen.getCaracteristica().toString()));
		this.numNodos = gen.getNumNodos();
		this.profundidad = gen.getProfundidad();

		this.hijos = new ArrayList<>();
		
		for(GenArbol g : gen.getHijos()) {
			this.hijos.add(new GenArbol(g, this));
		}
		
		this.actualizarNodo();
		
		this.padre = padre;
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
	
	public GenArbol getHijo(int pos) {
		return this.hijos.get(pos);
	}

	public void setHijos(ArrayList<GenArbol> hijos) {
		for(GenArbol g : hijos) {
			addHijo(g);
		}
	}
	
	public <T extends Gen<Tipo>> void setHijo(T hijo, int pos) {
		this.hijos.set(pos, new GenArbol((GenArbol)hijo, this));
		this.actualizarNodo();
	}
	
	public <T extends Gen<Tipo>> void addHijo(T hijo) {
		GenArbol gArb = new GenArbol((GenArbol) hijo, this);

		this.hijos.add(gArb);
		this.actualizarNodo();
	}

	public GenArbol getPadre() {
		return padre;
	}

	public void setPadre(GenArbol padre) {
		this.padre = padre;
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
			if(this.hijos.get(i) == nodoAntiguo) {
				GenArbol nodo = new GenArbol(nodoNuevo, this);
				this.hijos.set(i, nodo);
				
				this.actualizarNodo();
			}
		}
	}
	
	private void actualizarNodo() {
		int sumNod = 0, maxP = 0;
		
		for(GenArbol g : this.hijos) {
			sumNod += g.getNumNodos();
			
			if(g.getProfundidad() > maxP)
				maxP = g.getProfundidad();
		}
		
		this.numNodos = sumNod+1;
		this.profundidad = maxP+1;
		
		if(this.padre != null) {
			padre.actualizarNodo();
		}
	}
	
	@Override
	public String toString() {
		String s = this.getCaracteristica().toString() + "Nodos: " + this.numNodos + ", Profundidad: " + this.profundidad + " ";
		
		if(hijos.size() == 3) 
			s += " => ";
		else if (hijos.size() == 2) {
			s += " -> ";
		}
		
		for(GenArbol h : hijos) {
			s += System.lineSeparator() + "Hijo__: " + h.toString();
		}
		
		return s;
	}
	
	public String caracteristicaString(int prof) {
/*		String s = this.getCaracteristica().toString();
		
		for(GenArbol g : this.hijos)
			s += ", " + g.caracteristicaString();*/
		String espacio = generarEspacio(prof);
		
/*		for (int i = 0; i < prof; i++) {
			espacio += "    ";
		}*/
		
		String s = "";
//		String.format("%1$"+prof, "");
		switch (this.getCaracteristica()) {
		case PROGN2:
			s += espacio + "Prog2:" + System.lineSeparator();
			for(GenArbol g : this.hijos)
				s += espacio + g.caracteristicaString(prof+1);
			break;
		case PROGN3:
			s += espacio + "Prog3:" + System.lineSeparator();
			for(GenArbol g : this.hijos)
				s += espacio + g.caracteristicaString(prof+1);
			break;
		case SIC:
			s += espacio + "SIC: {" + System.lineSeparator();	
			s += espacio + this.getHijo(0).caracteristicaString(prof+1) + generarEspacio(prof-1);
			s += espacio + '}' + System.lineSeparator() + generarEspacio(prof-1);
			s += espacio + "ELSE: {" + System.lineSeparator();
			s += espacio + this.getHijo(1).caracteristicaString(prof+1) + generarEspacio(prof-1);
			s += espacio + '}' + System.lineSeparator();
			break;
		default:
			s += espacio + this.getCaracteristica().toString() + System.lineSeparator();
			break;
		}
		
		return s;
	}
	
	private String generarEspacio(int espacio) {
		String s = "";
		
		for (int i = 0; i < espacio; i++) {
			s += "    ";
		}
		
		return s;
	}
}

