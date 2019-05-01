package common.cruce;

import java.util.HashMap;

import common.Cromosoma;
import common.genes.Gen;
import common.genes.GenInt;
import practicas.practica2.Practica2;

public class PMX extends Reproduccion {

	@Override ParCromosoma cruce(ParCromosoma par) {
		int ini = rand.nextInt(par.getC1().getNumGenes()-1);
		int fin = rand.nextInt(par.getC1().getNumGenes()-ini)+ini;
		
		if(ini == fin)
			return par;
		
		HashMap<Gen<?>, Gen<?>> map1 = new HashMap<>(fin - ini);
		HashMap<Gen<?>, Gen<?>> map2 = new HashMap<>(fin - ini);
		
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		
		for (int i = ini; i < fin; i++) {
			h1.setGen(par.getC2().getGen(i), i);
			h2.setGen(par.getC1().getGen(i), i);
			map1.put(par.getC2().getGen(i), (GenInt)par.getC1().getGen(i));
			map2.put(par.getC1().getGen(i), (GenInt) par.getC2().getGen(i));
		}
		
		for (int i = 0; i < ini; i++) {
			if (map1.containsKey(par.getC1().getGen(i))) {				
				do {
					h1.setGen(map1.get(h1.getGen(i)), i);
				} while(map1.containsKey(h1.getGen(i)));
				
				
//				h1.setGen(map1.get(par.getC1().getGen(i)), i);
			} else {
				h1.setGen(par.getC1().getGen(i), i);
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				do {
					h2.setGen(map2.get(h2.getGen(i)), i);
				} while(map2.containsKey(h2.getGen(i)));
				
//				h2.setGen(map2.get(par.getC2().getGen(i)), i);
			} else {
				h2.setGen(par.getC2().getGen(i), i);
			}
		}
		
		for (int i = fin; i < par.getC1().getNumGenes(); i++) {
			if (map1.containsKey(par.getC1().getGen(i))) {
				do {
					h1.setGen(map1.get(h1.getGen(i)), i);
				} while(map1.containsKey(h1.getGen(i)));
//				h1.setGen(map1.get(par.getC1().getGen(i)), i);
			} else {
				h1.setGen(par.getC1().getGen(i), i);
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				do {
					h2.setGen(map2.get(h2.getGen(i)), i);
				} while(map2.containsKey(h2.getGen(i)));
//				h2.setGen(map2.get(par.getC2().getGen(i)), i);
			} else {
				h2.setGen(par.getC2().getGen(i), i);
			}
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "PMX";
	}
}
