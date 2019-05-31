package practica3;

import java.util.ArrayList;

import common.Cromosoma;
import common.genes.Gen;
import practicas.ProblemaNoBinario;

public class CromosomaArbol extends Cromosoma<Tipo>{
	private int pasos, puntos;

	public CromosomaArbol() {
		super();
	}
	
	public CromosomaArbol (ProblemaNoBinario func) {
		super(0, func, 0);
	}
	
	public CromosomaArbol (CromosomaArbol crom) {
		super(crom);
	}
	
	public Gen<Tipo> getGen() {
		return super.getGenes().get(0);
	}
	
	@Override
	public Gen<Tipo> getGen(int pos) {
		return super.getGen(0);
	}
	
	@Override
	public int getNumGenes() {
		return ((GenArbol)this.getGen()).getNumNodos();
	}
	
	@Override
	public <M extends Gen<Tipo>> void setGen(M gen, int pos) {
		GenArbol aux = ((GenArbol)this.getGen()).buscarNodo(pos);
		
		((GenArbol)this.getGen()).insertarNodo(aux, (GenArbol)gen);
		super.setGen(gen, 0);
	}

	public int getPasos() {
		return pasos;
	}

	public void setPasos(int pasos) {
		this.pasos = pasos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPunt(int puntos) {
		this.puntos = puntos;
	}
	
	
}
