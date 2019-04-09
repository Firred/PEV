package common.seleccion;

import java.util.Collections;
import java.util.Random;

import common.Poblacion;

public class Ranking extends Seleccion {
	//private Poblacion newPobl;
	private Random rand;
	
	public Ranking() {
		//newPobl = new Poblacion();
		rand = new Random();		
	}
	
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		double prob;
		int suma = (pobl.getTpobl()/2)*(pobl.getTpobl()+1);
		int pos_super;
		double[] probL = new double[pobl.getTpobl()];
		
		//Arrays.sort(pobl.getIndividuos());
		Collections.sort(pobl.getIndividuos());
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			probL[i] = (i+1)/suma;
		}
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			prob = rand.nextDouble();
			pos_super = 0;
			while(pos_super < pobl.getTpobl()-1 && prob > probL[pos_super]) {
				pos_super++;
			}			
			
			newPobl.addIndividuo(pobl.getIndividuos(pos_super));
			//newPobl.setIndividuos(pobl.getIndividuos(pos_super), i);
		}
		
		newPobl.setGeneracion(pobl.getGeneracion()+1);
		
		return newPobl;
	}


	@Override
	public String toString() {
		return "Ranking";
	}
}
