package common.cruce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import common.Cromosoma;
import common.genes.Gen;

public class RecombinacionRutas extends Reproduccion {

	@Override 
	ParCromosoma cruce(ParCromosoma par) {
		HashMap<Gen<?>, HashSet<Gen<?>>> map = generarMapa(par);		

		ArrayList<Boolean> marcas = new ArrayList<>(par.getC1().getNumGenes());
		
		Cromosoma h1, h2;
		
		h1 = crearCromosoma(map, par.getC1());
		
		h2 = crearCromosoma(map, par.getC2());

		return new ParCromosoma(h1, h2);
	}

	private HashMap<Gen<?>, HashSet<Gen<?>>> generarMapa(ParCromosoma par) {
		HashMap<Gen<?>, HashSet<Gen<?>>> map = new HashMap<>();		
		HashSet<Gen<?>> auxList;
		
		for (int i = 1; i < par.getC1().getNumGenes()-1; i++) {
			auxList = new HashSet<>(4);
			
			auxList.add(par.getC1().getGen(i-1));
			auxList.add(par.getC1().getGen(i+1));
			
			map.put(par.getC1().getGen(i), auxList);
		}
		
		//Adyacentes al primero
		auxList = new HashSet<>(4);
		
		auxList.add(par.getC1().getGen(1));
		auxList.add(par.getC1().getGen(par.getC1().getNumGenes()-1));
		
		map.put(par.getC1().getGen(0), auxList);
		
		//Adyacentes al ultimo
		auxList = new HashSet<>(4);
		
		auxList.add(par.getC1().getGen(0));
		auxList.add(par.getC1().getGen(par.getC1().getNumGenes()-2));
		
		map.put(par.getC1().getGen(par.getC1().getNumGenes()-1), auxList);
		
		for (int i = 1; i < par.getC2().getNumGenes()-1; i++) {
			auxList = map.get(par.getC2().getGen(i));
			
			if(!auxList.contains(par.getC2().getGen(i-1)))
				auxList.add(par.getC2().getGen(i-1));
			
			if(!auxList.contains(par.getC2().getGen(i+1)))
				auxList.add(par.getC2().getGen(i+1));
		}
		
		//Adyacentes al primero
		auxList = map.get(par.getC2().getGen(0));
		
		if(!auxList.contains(par.getC2().getGen(1)))
			auxList.add(par.getC2().getGen(1));
		if(!auxList.contains(par.getC2().getGen(par.getC2().getNumGenes()-1)))
			auxList.add(par.getC2().getGen(par.getC2().getNumGenes()-1));
		
		//Adyacentes al ultimo
		auxList = map.get(par.getC2().getGen(par.getC2().getNumGenes()-1));
		
		if(!auxList.contains(par.getC2().getGen(0)))
			auxList.add(par.getC2().getGen(0));
		if(!auxList.contains(par.getC2().getGen(par.getC2().getNumGenes()-2)))
			auxList.add(par.getC2().getGen(par.getC2().getNumGenes()-2));

		
		return map;
	}
	
	private <T extends Gen<?>> Cromosoma<?> crearCromosoma(HashMap<T, HashSet<T>> map, Cromosoma padre) {
		Cromosoma hijo = new Cromosoma<>(padre);
		
		T actual = (T) padre.getGen(0), siguiente;
		ArrayList<T> aux, mejores;
		HashSet<T> set = new HashSet<>();
		
		hijo.setGen(actual, 0);
		set.add(actual);
		
		for (int i = 1; i < map.size(); i++) {
			aux = new ArrayList<>(map.get(actual));	
			mejores = new ArrayList<>(4);
			siguiente = null;
			
			for (int j = 0; j < aux.size(); j++) {
				if(!set.contains(aux.get(j))) {
					if(mejores.isEmpty()) {
						mejores.add(aux.get(j));
					}
					else if(map.get(mejores.get(0)).size() > map.get(aux.get(j)).size()) {
						mejores = new ArrayList<>();
						mejores.add(aux.get(j));
					}
					else if(map.get(mejores.get(0)).size() == map.get(aux.get(j)).size()) {
						mejores.add(aux.get(j));
					}	
				}
			}
			
			if(!mejores.isEmpty()) {
				siguiente = mejores.get(rand.nextInt(mejores.size()));
			}
			else {
				int j = 0, r = rand.nextInt(map.size()-set.size())+1;
				Iterator<T> it = map.keySet().iterator();
				T gen = null;
				
				while(j < r && it.hasNext()) {
					gen = it.next();
					
					if(!set.contains(gen))
						j++;
				}
				
				siguiente = gen;
			}			
			
			if(siguiente == null)
				return null;
			
			set.add(siguiente);
			actual = siguiente;
			hijo.setGen(siguiente, i);
		}
		
		return hijo;
	}
	
	@Override
	public String toString() {
		return "Recombinacion de rutas";
	}
}
