package common.cruce.practica2;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;

public class CruceCiclos extends Reproduccion {
	
	@Override
	protected ParCromosoma cruce(ParCromosoma par) {
		int pos = 0;

		Cromosoma h1 = new Cromosoma(par.getC2()), h2 = new Cromosoma(par.getC1());

		while (par.getC1().getGen(0) != par.getC2().getGen(pos)) {
			h2.getGenes().set(pos, par.getC1().getGen(pos));
			
			pos = par.getC1().getGenes().indexOf(par.getC2().getGen(pos));
		}
		
		pos = 0;
		
		while (par.getC2().getGen(0) != par.getC1().getGen(pos)) {
			h1.getGenes().set(pos, par.getC2().getGen(pos));
			
			pos = par.getC2().getGenes().indexOf(par.getC1().getGen(pos));
		}

		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Cruce por ciclos";
	}
}
