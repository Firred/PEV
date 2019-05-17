package practica3;

import common.Cromosoma;
import common.genes.Gen;

public class CromosomaArbol extends Cromosoma<Tipo>{

	public CromosomaArbol() {
		super();
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
	
	
}
