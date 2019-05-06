package common.seleccion;

import java.util.Collections;

import common.Poblacion;

public class Truncamiento extends Seleccion {
	
	public Truncamiento() {
	}
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		//trunc debería ser una variable global introducida en la interfaz
		double trunc = 0.2;
		int repeticiones = (int)(1/trunc);
		int corte = (int)(trunc*pobl.getTpobl());

		//Arrays.sort(pobl.getIndividuos(), Collections.reverseOrder());
		Collections.sort(pobl.getIndividuos(), Collections.reverseOrder());
		for(int i = 0; i < corte; i++) {
			for(int j = 0; j < repeticiones; j++) {
				newPobl.addIndividuo(pobl.getIndividuos(i));
				//newPobl.setIndividuos(pobl.getIndividuos(i), j);
			}
		}
		
		newPobl.setGeneracion(pobl.getGeneracion()+1);
		
		return newPobl;
	}

	@Override
	public String toString() {
		return "Truncamiento";
	}

}
