package common.seleccion.torneo;

import common.Cromosoma;

public class TorneoProbabilistico extends Torneo {

	@Override
	public Cromosoma seleccion(Cromosoma[] individuos) {
		Cromosoma mejor = null;
		Cromosoma peor = null;
		
		if(individuos.length > 0) {
			mejor = individuos[0];
			peor = individuos[0];
			
			for (int i = 1; i < individuos.length; i++) {
				if(mejor.getApt() < individuos[i].getApt()) {
					mejor = individuos[i];
				}
				else if(peor.getApt() > individuos[i].getApt()) {
					peor = individuos[i];
				}
			}
		}
		
		//p deber�a ser un atributo global que se seleccione en la interfaz
		double p = 0.6;
		if(rand.nextDouble() > p) 
			return mejor;
		else
			return peor;
	}

	@Override
	public String toString() {
		return "Torneo Probabilistico";
	}

}
