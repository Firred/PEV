package common.cruce;

import common.Cromosoma;
import common.genes.GenReal;

public class SLX extends Reproduccion {

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
		double cMax = 0, cMin = 0, I;
		double iSup, iInf, valor;
		
		for(int i = 0; i < par.getC1().getNumGenes(); i++) {
			if(p1.getGen(i).getCaracteristica() > p2.getGen(i).getCaracteristica()) {
				cMax = p1.getGen(i).getCaracteristica();
				cMin = p2.getGen(i).getCaracteristica();
			}
			else {
				cMax = p2.getGen(i).getCaracteristica();
				cMin = p1.getGen(i).getCaracteristica();
			}
			
			I = cMax - cMin;
			
			iSup = cMax + I*alfa;
			iInf = cMin - I*alfa;
			
			valor = rand.nextDouble()*(iSup-iInf)+iInf;
			
			genAux = new GenReal();
			genAux.setCarateristica(valor);
			
			h1.setGen(genAux, i);
			
			valor = rand.nextDouble()*(iSup-iInf)+iInf;
			
			genAux = new GenReal();
			genAux.setCarateristica(valor);
			
			h2.setGen(genAux, i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "SLX";
	}
}