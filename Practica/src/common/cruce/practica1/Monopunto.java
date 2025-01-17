package common.cruce.practica1;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;

public class Monopunto extends Reproduccion {

	@Override
	protected
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma<?> h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int n = rand.nextInt(par.getC1().getNumGenes());

		for(int i = 0; i < n; i++) {
			h1.setGen(par.getC2().getGen(i), i);
			h2.setGen(par.getC1().getGen(i), i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Monopunto";
	}
}