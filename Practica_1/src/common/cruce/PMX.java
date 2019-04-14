package common.cruce;

import java.util.HashMap;

import common.Cromosoma;
import common.genes.Gen;

public class PMX<T extends Gen<?>> extends Reproduccion {

	@Override ParCromosoma cruce(ParCromosoma par) {
		int ini = rand.nextInt(par.getC1().getNumGenes()-1);
		int fin = rand.nextInt(par.getC1().getNumGenes()-ini)+ini;
		HashMap<T, Integer> map1 = new HashMap<>(fin - ini);
		HashMap<T, Integer> map2 = new HashMap<>(fin - ini);
		
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		
		for (int i = ini; i <= fin; i++) {
			h1.getGenes().set(i, par.getC2().getGen(i));
			h2.getGenes().set(i, par.getC1().getGen(i));
			map1.put((T) par.getC2().getGen(i), i);
			map2.put((T) par.getC1().getGen(i), i);
		}
		
		for (int i = 0; i < ini; i++) {
			if (map1.containsKey(par.getC1().getGen(i))) {
				h1.getGenes().set(i, par.getC2().getGenes().get(map1.get(par.getC1().getGen(i))));
			} else {
				h1.getGenes().set(i, par.getC1().getGenes().get(i));
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				h2.getGenes().set(i, par.getC1().getGenes().get(map2.get(par.getC2().getGen(i))));
			} else {
				h2.getGenes().set(i, par.getC2().getGenes().get(i));
			}
		}
		
		for (int i = fin+1; i < par.getC1().getNumGenes(); i++) {
			if (map1.containsKey(par.getC1().getGen(i))) {
				h1.getGenes().set(i, par.getC2().getGenes().get(map1.get(par.getC1().getGen(i))));
			} else {
				h1.getGenes().set(i, par.getC1().getGenes().get(i));
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				h2.getGenes().set(i, par.getC1().getGenes().get(map2.get(par.getC2().getGen(i))));
			} else {
				h2.getGenes().set(i, par.getC2().getGenes().get(i));
			}
		}
		
		return new ParCromosoma(h1, h2);
	}
}
