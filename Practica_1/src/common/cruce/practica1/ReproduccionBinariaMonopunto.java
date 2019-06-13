package common.cruce.practica1;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.GenBi;

public class ReproduccionBinariaMonopunto extends Reproduccion {
	@Override
	protected 
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma h1 =  new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int n = par.getC1().getGenes_long();
		n = rand.nextInt(Math.abs(n));
		int i = 0;
		
		while (n > 0) {
			if (n < par.getC1().getGen(i).getTam()) {
				GenBi g1 = (GenBi)h1.getGen(i), g2 = (GenBi)h2.getGen(i);
				
				for (int j= 0; j < par.getC1().getGen(i).getTam(); j++) {
					g1.setAlelo(((GenBi)par.getC2().getGen(i)).getAlelo(j), j);
					g2.setAlelo(((GenBi)par.getC1().getGen(i)).getAlelo(j), j);
				}					
					
				n = 0;
			}
			else {
				h1.getGenes().set(i, new GenBi((GenBi)par.getC2().getGen(i)));
				h2.getGenes().set(i, new GenBi((GenBi)par.getC1().getGen(i)));
				
				n -= par.getC1().getGen(i).getTam();
			}		
			
			i++;
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Reproduccion binaria";
	}
}
