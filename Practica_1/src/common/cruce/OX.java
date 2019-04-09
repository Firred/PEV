package common.cruce;

import java.util.HashMap;

import common.Cromosoma;
import common.genes.Gen;

public class OX<T extends Gen<?>> extends Reproduccion {

	@Override
	ParCromosoma cruce(ParCromosoma par) {
		int nGenes = par.getC1().getNumGenes();
		int ini = rand.nextInt(nGenes-1);
		int fin = rand.nextInt(nGenes-ini);
		int j1 = nGenes+fin+1, j2 = nGenes+fin+1;
		int j = nGenes + fin + 1;
		HashMap<T, Integer> map1 = new HashMap<>(fin - ini);
		HashMap<T, Integer> map2 = new HashMap<>(fin - ini);
		
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		
		for (int i = ini; i <= fin; i++) {
			h1.getGenes().set(i, par.getC2().getGen(i));
			h2.getGenes().set(i, par.getC1().getGen(i));
			map1.put((T) par.getC2().getGen(i), i);
			map2.put((T) par.getC1().getGen(i), i);
		}
		
		for (int i = fin+1; i < nGenes; i++) {
			while(map1.containsKey(par.getC1().getGen(j1%nGenes))) {
					j1++;
			}
			
			h1.getGenes().set(i, par.getC1().getGenes().get(j1%nGenes));
			
			while (map2.containsKey(par.getC2().getGen(j2%nGenes))) {
				j2++;
			}
			
			h2.getGenes().set(i, par.getC2().getGenes().get(j2%nGenes));
		}
		
		for (int i = 0; i < ini; i++) {
			while(map1.containsKey(par.getC1().getGen(j1%nGenes))) {
				j1++;
			}
		
			h1.getGenes().set(i, par.getC1().getGenes().get(j1%nGenes));
		
			while (map2.containsKey(par.getC2().getGen(j2%nGenes))) {
				j2++;
			}
			
			h2.getGenes().set(i, par.getC2().getGenes().get(j2%nGenes));
		}
		
		return new ParCromosoma(h1, h2);
	}

}
