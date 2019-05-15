package practica3;

import common.Cromosoma;
import common.genes.Gen;

public class CromosomaArbol extends Cromosoma<GenArbol>{

	public CromosomaArbol (CromosomaArbol crom) {
		super(crom);
	}
	
	public GenArbol getGen() {
		return super.getGenes().get(0).getCaracteristica();
	}
	
	@Override
	public Gen<GenArbol> getGen(int pos) {
		return super.getGen(0);
	}
	
	@Override
	public int getNumGenes() {
		return this.getGen().getNumNodos();
	}
	
	@Override
	public <M extends Gen<GenArbol>> void setGen(M gen, int pos) {
		super.setGen(gen, 0);
	}
}
