package common.seleccion.estocastico;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.seleccion.Seleccion;

public class MuestreoEstocasticoUniversal extends Seleccion {
	private Random rand;
	
	public MuestreoEstocasticoUniversal() {
		rand = new Random();
	}
	
	public Poblacion execute(Poblacion pobl) {
		double intervalo = 1.0/pobl.getTPoblacion();
		double r = rand.nextDouble()*(intervalo);
		int pos_super = 0;
		ArrayList<Cromosoma> lista = new ArrayList<>();
		
		for(int i = 0; i < pobl.getTPobl(); i++) {
			while(pos_super < pobl.getTPoblacion()-1 && r*i > pobl.getIndividuos(pos_super).getPunt_Acum()) {
				pos_super++;
			}
			
			lista.add(pobl.getIndividuos(pos_super));
		}	
		
		pobl.setIndividuos(lista);
		pobl.setGeneracion(pobl.getGeneracion()+1);
		
		return pobl;
	}

	@Override
	public String toString() {
		return "Muestreo Estocastico Universal";
	}	
}
