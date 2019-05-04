package common.cruce;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import common.Cromosoma;
import common.genes.Gen;

public class OX extends Reproduccion {

	@Override ParCromosoma cruce(ParCromosoma par) {
		int nGenes = par.getC1().getNumGenes();
		int ini = rand.nextInt(nGenes-1);
		int fin = rand.nextInt(nGenes-ini)+ini;
		int j1 = nGenes+fin+1, j2 = nGenes+fin+1;
		
		HashSet<Gen<?>> list1 = new HashSet<>(), list2 = new HashSet<>();
		
		Cromosoma h1 = new Cromosoma(par.getC2()), h2 = new Cromosoma(par.getC1());
		
		for (int i = ini; i < fin; i++) {
			list1.add(par.getC2().getGen(i));
			list2.add(par.getC1().getGen(i));
		}
		
		for (int i = fin; i < nGenes; i++, j1++, j2++) {
			while(list1.contains(par.getC1().getGen(j1%nGenes))) {
					j1++;
			}
			
			h1.getGenes().set(i, par.getC1().getGen(j1%nGenes));
			
			while(list2.contains(par.getC2().getGen(j2%nGenes))) {
				j2++;
			}
			
			h2.getGenes().set(i, par.getC2().getGen(j2%nGenes));
		}
		
		for (int i = 0; i < ini; i++, j1++, j2++) {
			while(list1.contains(par.getC1().getGen(j1%nGenes))) {
				j1++;
			}
		
			h1.getGenes().set(i, par.getC1().getGen(j1%nGenes));
		
			while(list2.contains(par.getC2().getGen(j2%nGenes))) {
				j2++;
			}
			
			h2.getGenes().set(i, par.getC2().getGen(j2%nGenes));
		}
		
		return new ParCromosoma(h1, h2);
	}

	@Override
	public String toString() {
		return "OX";
	}
}
