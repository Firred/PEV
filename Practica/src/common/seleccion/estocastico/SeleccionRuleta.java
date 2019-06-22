package common.seleccion.estocastico;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.seleccion.Seleccion;

public class SeleccionRuleta extends Seleccion {
	private Random rand;
	
	public SeleccionRuleta() {
		rand = new Random();		
	}
	
	/**
	 * @see common.seleccion.Seleccion#execute(common.Poblacion)
	 */
	public Poblacion execute(Poblacion pobl) {
		double prob;	
		int pos_super;
		ArrayList<Cromosoma> lista = new ArrayList<>();
		
		for(int i = 0; i < pobl.getTPobl(); i++) {
			prob = rand.nextDouble();
			pos_super = 0;
			
			while(pos_super < pobl.getTPoblacion()-1 && prob > pobl.getIndividuos(pos_super).getPunt_Acum()) {
				pos_super++;
			}	
			
			lista.add(new Cromosoma<>(pobl.getIndividuos(pos_super)));
		}
		
		pobl.setIndividuos(lista);
		pobl.setGeneracion(pobl.getGeneracion()+1);
		
		return pobl;
	}

	@Override
	public String toString() {
		return "Ruleta";
	}
	
}
