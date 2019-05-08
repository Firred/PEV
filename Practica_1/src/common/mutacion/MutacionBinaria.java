package common.mutacion;

import common.Cromosoma;
import common.Poblacion;
import common.genes.Gen;
import common.genes.GenBi;

public class MutacionBinaria extends Mutacion {

	@Override
	public void execute(Poblacion pobl, int prob) {
		boolean[] alelo;
		for (Cromosoma<Double> c : pobl.getIndividuos()) {
			for(Gen<Double> g : c.getGenes()) {
				alelo = ((GenBi)g).getAlelo();
				
				for(int i = 0; i < alelo.length; i++) {
					if(prob > rand.nextInt(100)) {
						alelo[i] = true^alelo[i];
					}
				}		
			}
		}
	}

	@Override
	Cromosoma mutacion(Cromosoma crom) {return null;}	
	
	@Override
		public String toString() {
			return "Mutacion binaria";
		}
}
