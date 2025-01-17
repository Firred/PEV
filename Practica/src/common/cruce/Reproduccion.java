package common.cruce;

import java.util.Random;

import common.Poblacion;

public abstract class Reproduccion {
	protected Random rand = new Random();
	
	public void ejecutar(Poblacion pobl, int probCruce) {
		int h1 = 0;
		boolean seleccionado = false;
		ParCromosoma par, hijos;
		
		if(probCruce == 0)
			return;
		
		for (int i = 0; i < pobl.getTPoblacion(); i++) {
			if (probCruce >= rand.nextInt(100)) {
				if(!seleccionado) {
					h1 = i;
					seleccionado = true;
				}
				else {
					par = new ParCromosoma(pobl.getIndividuos(h1), pobl.getIndividuos(i));
					
					hijos = cruce(par);
					
					pobl.setIndividuo(hijos.getC1(), h1);
					pobl.setIndividuo(hijos.getC2(), i);
					
					seleccionado = false;
				}
			}	
		}
	}
	
	protected abstract ParCromosoma cruce(ParCromosoma par);
}
