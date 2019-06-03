package common.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;

public class Ranking extends Seleccion {
	
	private final double BETA = 2;
	private Random rand;
	
	public Ranking() {
		rand = new Random();		
	}
	
	
	public Poblacion execute(Poblacion pobl) {
		ArrayList<Cromosoma> lista = new ArrayList<>();
		double x;
//		int suma = (pobl.getTpobl()/2)*(pobl.getTpobl()+1);
		int pos_super;
		
		Collections.sort(pobl.getIndividuos());
		
/*		for(int i = 0; i < pobl.getTpobl(); i++) {
			probL[i] = (i+1)/suma;
		}*/
		
		ArrayList<Double> probL = rankingPobl(pobl);
		double seg = probL.get(probL.size()-1);
		
		HashMap<Integer, Integer> repeticiones = new HashMap<>();
		
		for(int i = 0; i < pobl.getTPoblacion(); i++) {
			repeticiones.put(i, 0);
		}
		
		for(int i = 0; i < pobl.getTPoblacion(); i++) {
			x = (double)(rand.nextDouble()*seg);
			pos_super = 0;
			
			if(x <= probL.get(0)) {
				lista.add(pobl.getIndividuos(0));
//				newPobl.addIndividuo(pobl.getIndividuos(0));
				
				repeticiones.put(0, repeticiones.get(0)+1);
			}
			else {				
				while(pos_super < probL.size() && x > probL.get(pos_super)) {
					pos_super++;
				}
				
				lista.add(pobl.getIndividuos(pos_super));
//				newPobl.addIndividuo(pobl.getIndividuos(pos_super));
				
				repeticiones.put(pos_super, repeticiones.get(pos_super)+1);
			}
		}

		pobl.setGeneracion(pobl.getGeneracion()+1);
		
		return pobl;
	}
	
	private ArrayList<Double> rankingPobl(Poblacion pobl) {
		ArrayList<Double> lista = new ArrayList<>(pobl.getTPoblacion());
		double prob;
		
		for(int i = 0; i < pobl.getTPoblacion(); i++) {
			prob = (double)((i-1)/(pobl.getTPoblacion()-1));
			prob = prob*2*(BETA-1);
			prob = BETA - prob;
			prob = (double)prob*((double)1/pobl.getTPoblacion());
			
			if(i != 0)
				lista.add(i, lista.get(i-1)+prob);
			else
				lista.add(i, prob);
		}

		return lista;
	}


	@Override
	public String toString() {
		return "Ranking";
	}
}
