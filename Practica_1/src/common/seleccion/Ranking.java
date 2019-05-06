package common.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;

public class Ranking extends Seleccion {
	
	private final double BETA = 1.5;
	private Random rand;
	
	public Ranking() {
		rand = new Random();		
	}
	
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		double x;
//		int suma = (pobl.getTpobl()/2)*(pobl.getTpobl()+1);
		int pos_super;
		
		Collections.sort(pobl.getIndividuos());
		
/*		for(int i = 0; i < pobl.getTpobl(); i++) {
			probL[i] = (i+1)/suma;
		}*/
		
		ArrayList<Double> probL = rankingPobl(pobl);
		double seg = probL.get(probL.size()-1);
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			x = (double)(rand.nextDouble()*seg);
			pos_super = 0;
			
			if(x <= probL.get(0)) {
				newPobl.addIndividuo(pobl.getIndividuos(0));
			}
			else {				
				while(pos_super < probL.size() && x > probL.get(pos_super)) {
					pos_super++;
				}
				
				newPobl.addIndividuo(pobl.getIndividuos(pos_super));
			}
		}
		
		newPobl.setGeneracion(pobl.getGeneracion()+1);
		
		return newPobl;
	}
	
	public ArrayList<Double> rankingPobl(Poblacion pobl) {
		ArrayList<Double> lista = new ArrayList<>(pobl.getTpobl());
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			double prob = (double)i/pobl.getTpobl();
			prob = prob*2*(BETA -1);
			prob = BETA - prob;
			prob = (double)prob*((double)1/pobl.getTpobl());
			
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
