package common.cruce.practica1;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.GenBi;

public class ReproduccionBinariaMultipunto extends Reproduccion {

	@Override
	protected ParCromosoma cruce(ParCromosoma par) {
		Cromosoma h1 =  new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int n = par.getC1().getGenes_long();
		int n1 = rand.nextInt(n);
		int n2 = rand.nextInt(n-n1)+n1;
		int i = 0, j;
		
		while(n1 > 0) {
			if(((GenBi)par.getC1().getGen(i)).getLong() < n1) {
				n1 -= ((GenBi)par.getC1().getGen(i)).getLong();
				n2 -= ((GenBi)par.getC1().getGen(i)).getLong();
				i++;
			}
			else {
				j = ((GenBi)par.getC1().getGen(i)).getLong() - n1;
				n2 -= (((GenBi)par.getC1().getGen(i)).getLong()-n1);
				n1 = 0;
			}
		}

		while (n2 > 0) {
			if (n2 < ((GenBi)par.getC1().getGen(i)).getLong()) {
				GenBi g1 = (GenBi)h1.getGen(i), g2 = (GenBi)h2.getGen(i);
				
				for (j = 0; j < n2; j++) {
					g1.setAlelo(((GenBi)par.getC2().getGen(i)).getAlelo(j), j);
					g2.setAlelo(((GenBi)par.getC1().getGen(i)).getAlelo(j), j);
				}					

				n2 = 0;
			}
			else {
				h1.getGenes().set(i, new GenBi((GenBi)par.getC2().getGen(i)));
				h2.getGenes().set(i, new GenBi((GenBi)par.getC1().getGen(i)));

				n2 -= ((GenBi)par.getC1().getGen(i)).getLong();
			}		
			
			i++;
		}
				
		return new ParCromosoma(h1, h2);
	}

	@Override
	public String toString() {
		return "Multipunto";
	}	
}
