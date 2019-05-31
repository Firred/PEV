package common.seleccion.estocastico;

import java.util.Random;

import common.Poblacion;
import common.seleccion.Seleccion;

public class MuestreoEstocasticoUniversal extends Seleccion {
	private Random rand;
	
	public MuestreoEstocasticoUniversal() {
		rand = new Random();
	}
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		double intervalo = 1.0/pobl.getTpobl();
		double r = rand.nextDouble()*(intervalo);
		int pos_super = 0;
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			while(pos_super < pobl.getTpobl()-1 && r*i > pobl.getIndividuos(pos_super).getPunt_Acum()) {
				pos_super++;
			}
			
			newPobl.addIndividuo(pobl.getIndividuos(pos_super));
		}	
		
		newPobl.setGeneracion(pobl.getGeneracion()+1);
		
		return newPobl;
	}

	@Override
	public String toString() {
		return "Muestreo Estocastico Universal";
	}	
}
