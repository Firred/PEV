package common.cruce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.math.plot.utils.Array;

import common.Cromosoma;
import common.genes.Gen;

public class RecombinacionRutas<T> extends Reproduccion {

	@Override ParCromosoma cruce(ParCromosoma par) {
		HashMap<Gen<T>, ArrayList<Gen<T>>> map = generarMapa(par);		
	
		ArrayList<Boolean> marcas = new ArrayList<>(par.getC1().getNumGenes());
		
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int intentos = 0;
		
		while(!crearCromosoma(map, par.getC1().getGen(0), h1)) {
			intentos++;
			if(intentos == 100)
				return null;
		}
		
		intentos = 0;
		
		while(!crearCromosoma(map, par.getC2().getGen(0), h2)) {
			if(intentos == 100)
				return null;
		}
		
		return new ParCromosoma(h1, h2);
	}

	private HashMap<Gen<T>, ArrayList<Gen<T>>> generarMapa(ParCromosoma par) {
		HashMap<Gen<T>, ArrayList<Gen<T>>> map = new HashMap<>();		
		ArrayList<Gen<T>> auxList;
		
		for (int i = 1; i < par.getC1().getNumGenes()-1; i++) {
			auxList = new ArrayList<>();
			
			auxList.add(par.getC1().getGen(i-1));
			auxList.add(par.getC1().getGen(i+1));
			
			map.put(par.getC1().getGen(i), auxList);
		}
		
		//Adyacentes al primero
		auxList = new ArrayList<>();
		
		auxList.add(par.getC1().getGen(1));
		auxList.add(par.getC1().getGen(par.getC1().getNumGenes()-1));
		
		map.put(par.getC1().getGen(0), auxList);
		
		//Adyacentes al ultimo
		auxList = new ArrayList<>();
		
		auxList.add(par.getC1().getGen(0));
		auxList.add(par.getC1().getGen(par.getC1().getNumGenes()-2));
		
		map.put(par.getC1().getGen(par.getC1().getNumGenes()-1), auxList);
		
		
		for (int i = 1; i < par.getC2().getNumGenes()-1; i++) {
			auxList = map.get(par.getC2().getGen(i));
			
			//Gen<T> Comparable?
			if(auxList.contains(par.getC1().getGen(i-1)))
				auxList.add(par.getC1().getGen(i-1));
			
			if(auxList.contains(par.getC1().getGen(i+1)))
				auxList.add(par.getC1().getGen(i+1));
			
			//map.put(par.getC1().getGen(i), auxList);
		}
		
		//Adyacentes al primero
		auxList = map.get(par.getC2().getGen(0));
		
		if(auxList.contains(par.getC1().getGen(1)))
			auxList.add(par.getC2().getGen(1));
		if(auxList.contains(par.getC1().getGen(par.getC2().getNumGenes()-1)))
			auxList.add(par.getC2().getGen(par.getC2().getNumGenes()-1));
		
		//map.put(par.getC2().getGen(0), auxList);
		
		//Adyacentes al ultimo
		auxList = map.get(par.getC2().getGen(par.getC2().getNumGenes()-1));
		
		if(auxList.contains(par.getC1().getGen(0)))
			auxList.add(par.getC2().getGen(0));
		if(auxList.contains(par.getC1().getGen(par.getC2().getNumGenes()-2)))
			auxList.add(par.getC2().getGen(par.getC2().getNumGenes()-2));
		
		//map.put(par.getC1().getGen(par.getC1().getNumGenes()-1), auxList);
		
		return map;
	}
	
	private boolean crearCromosoma(HashMap<Gen<T>, ArrayList<Gen<T>>> map, Gen<T> ini, Cromosoma crom) {
		Gen<T> actual = ini, mejor;
		ArrayList<Gen<T>> aux;
		HashSet<Gen<T>> set = new HashSet<>();
			
		for (int i = 0; i < map.size(); i++) {
			aux = map.get(actual);	
			mejor = null;
			
			for (int j = 1; j < aux.size(); j++) {
				if(!set.contains(aux.get(j))) {
					if(mejor == null) {
						mejor = aux.get(j);
					}
					else if(map.get(mejor).size() > map.get(aux.get(j)).size()) {
						mejor = aux.get(j);
					}
					else if (map.get(mejor).size() == map.get(aux.get(j)).size()) {
						if (rand.nextBoolean())
							mejor = aux.get(j);
					}
				}
			}
			
			if(mejor == null)
				return false;
			
			set.add(mejor);
			actual = mejor;
			crom.getGenes().set(i, mejor);
		}
		
		return true;
	}
}
