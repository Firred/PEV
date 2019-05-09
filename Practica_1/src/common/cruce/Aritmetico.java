package common.cruce;

import common.Cromosoma;
import common.genes.GenReal;

public class Aritmetico extends Reproduccion {

	private double alfa = 0.5;
	
	public double getAlfa() {
		return alfa;
	}
	
	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}
	
	@Override
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma<Double> h1 = new Cromosoma<Double>(par.getC1()), h2 = new Cromosoma<>(par.getC2());
		Cromosoma<Double> p1 = (Cromosoma<Double>)par.getC1(), p2 = (Cromosoma<Double>)par.getC2();
		GenReal genAux;
		
		for(int i = 0; i < par.getC1().getNumGenes(); i++) {
			genAux = new GenReal();
			genAux.setCarateristica(alfa*p1.getGen(i).getCaracteristica() + (1-alfa)*p2.getGen(i).getCaracteristica());
			h1.setGen(genAux, i);
			
			genAux = new GenReal();
			genAux.setCarateristica(alfa*p2.getGen(i).getCaracteristica() + (1-alfa)*p1.getGen(i).getCaracteristica());
			h2.setGen(genAux, i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Aritmetico";
	}
}