package common.cruce.practica2;

import java.util.HashMap;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.Gen;
import common.genes.GenInt;

public class PMX extends Reproduccion {

	@SuppressWarnings("unchecked")
	@Override
	protected ParCromosoma cruce(ParCromosoma par) {
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
/*			if (map1.containsKey(par.getC1().getGen(i))) {		
				
				do {
					h1.setGen(map1.get(h1.getGen(i)), i);
				} while(map1.containsKey(h1.getGen(i)));
				
			} else {
				h1.setGen(par.getC1().getGen(i), i);
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				
				do {
					h2.setGen(map2.get(h2.getGen(i)), i);
				} while(map2.containsKey(h2.getGen(i)));

			} else {
				h2.setGen(par.getC2().getGen(i), i);
			}*/
			
			remplazarConflicto(h1, map1, i);
			remplazarConflicto(h2, map2, i);
		}
		
		for (int i = fin; i < par.getC1().getNumGenes(); i++) {
/*			if (map1.containsKey(par.getC1().getGen(i))) {
				
				do {
					h1.setGen(map1.get(h1.getGen(i)), i);
				} while(map1.containsKey(h1.getGen(i)));

			} else {
				h1.setGen(par.getC1().getGen(i), i);
			}
			
			if (map2.containsKey(par.getC2().getGen(i))) {
				
				do {
					h2.setGen(map2.get(h2.getGen(i)), i);
				} while(map2.containsKey(h2.getGen(i)));

			} else {
				h2.setGen(par.getC2().getGen(i), i);
			}*/		
			
			remplazarConflicto(h1, map1, i);
			remplazarConflicto(h2, map2, i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	private <T> void remplazarConflicto(Cromosoma<T> crom, HashMap<Gen<?>, Gen<?>> mapa, int pos) {
		if (mapa.containsKey(crom.getGen(pos))) {		
			
			do {
				crom.setGen((Gen<T>)mapa.get(crom.getGen(pos)), pos);
			} while(mapa.containsKey(crom.getGen(pos)));
			
		}
	}
	
	@Override
	public String toString() {
		return "PMX";
	}
}
