package common.cruce;

import common.Cromosoma;

public class DiscretoUniforme extends Reproduccion {

	private double p = 0.4;
	
	public double getP() {
		return this.p;
	}
	
	public void setP(double p) {
		this.p = p;
	}
	
	@Override
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma<?> h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
	
		for (int i = 0; i < par.getC1().getNumGenes(); i++) {
			if(p < rand.nextDouble()) {
				h1.setGen(par.getC2().getGen(i), i);
				h2.setGen(par.getC1().getGen(i), i);
			}
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Discreto Uniforme";
	}
}
