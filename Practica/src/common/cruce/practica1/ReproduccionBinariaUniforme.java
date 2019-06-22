package common.cruce.practica1;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.GenBi;

public class ReproduccionBinariaUniforme extends Reproduccion {

	@Override
	protected ParCromosoma cruce(ParCromosoma par) {
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		GenBi gen1, gen2;

		for(int i = 0; i < par.getC1().getNumGenes(); i++) {
			gen1 = new GenBi((GenBi)h1.getGen(i));
			gen2 = new GenBi((GenBi)h2.getGen(i));	
			
			for(int j = 0; j < gen1.getLong(); j++) {
				if(0.5 < rand.nextDouble()) {					
					gen1.setAlelo(((GenBi)par.getC2().getGen(i)).getAlelo(j), j);
					gen2.setAlelo(((GenBi)par.getC1().getGen(i)).getAlelo(j), j);				
				}
			}
			
			h1.setGen(gen1, i);
			h2.setGen(gen2, i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Uniforme";
	}
}
