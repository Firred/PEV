package common.seleccion.estocastico;

import java.util.Random;

import common.Poblacion;
import common.seleccion.Seleccion;

public class SeleccionRuleta extends Seleccion {
	//private Poblacion newPobl;
	private Random rand;
	
	public SeleccionRuleta() {
		//newPobl = new Poblacion();
		rand = new Random();		
	}
	
	/**
	 * @see common.seleccion.Seleccion#execute(common.Poblacion)
	 */
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		double prob;	
		int pos_super;
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			prob = rand.nextDouble();
			pos_super = 0;
			
			while(pos_super < pobl.getTpobl()-1 && prob > pobl.getIndividuos(pos_super).getPunt_Acum()) {
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
		return "Ruleta";
	}
	
}
