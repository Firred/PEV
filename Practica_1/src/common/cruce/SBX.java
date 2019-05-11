package common.cruce;

import common.Cromosoma;
import common.genes.GenReal;

public class SBX extends Reproduccion {

	private double beta = 0.5;
	
	public double getBeta() {
		return beta;
	}
	
	public void setBeta(double beta) {
		this.beta = beta;
	}
	
	@Override
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma<Double> h1 = new Cromosoma<Double>(par.getC1()), h2 = new Cromosoma<>(par.getC2());
		Cromosoma<Double> p1 = (Cromosoma<Double>)par.getC1(), p2 = (Cromosoma<Double>)par.getC2();
		
		double x, valor;
		GenReal gen;
		
		for(int i = 0; i < p1.getNumGenes(); i++) {
			x = (p1.getGen(i).getCaracteristica() + p2.getGen(i).getCaracteristica())/2;
			
			if(h1.getGen(i).getCaracteristica() > p2.getGen(i).getCaracteristica()) {
				valor = beta*(p1.getGen(i).getCaracteristica()-p2.getGen(i).getCaracteristica())/2;
			}
			else {
				valor = beta*(p2.getGen(i).getCaracteristica()-p1.getGen(i).getCaracteristica())/2;
			}
			
			gen = new GenReal();
			gen.setCarateristica(x-valor);
			h1.setGen(gen, i);
			
			gen = new GenReal();
			gen.setCarateristica(x+valor);
			h2.setGen(gen, i);
		}
		
		return new ParCromosoma(h1,h2);
	}
	
	@Override
	public String toString() {
		return "SBX";
	}
}
