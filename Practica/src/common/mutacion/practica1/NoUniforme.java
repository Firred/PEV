package common.mutacion.practica1;

import common.Cromosoma;
import common.genes.GenReal;
import common.mutacion.Mutacion;

public class NoUniforme extends Mutacion {

	private double sigma = 0.02;
	
	public double getSigma() {
		return sigma;
	}
	
	public void setSigma(double sigma) {
		this.sigma = sigma;
	}
	
	@Override
	protected
	Cromosoma mutacion(Cromosoma crom) {
		double valor = rand.nextGaussian()*sigma;
		int multi;
		
		GenReal gen;
		
		for(int i = 0; i < crom.getNumGenes(); i++) {
			gen = new GenReal();
			
			if(rand.nextBoolean())
				multi = 1;
			else
				multi = -1;
			
			gen.setCarateristica(((GenReal)crom.getGen(i)).getCaracteristica()+multi*valor);
			
			crom.setGen(gen, i);;
		}
		
		return crom;
	}

	@Override
	public String toString() {
		return "No Uniforme";
	}
}
