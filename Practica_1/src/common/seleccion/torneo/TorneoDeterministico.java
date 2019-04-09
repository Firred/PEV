package common.seleccion.torneo;

import common.Cromosoma;

public class TorneoDeterministico extends Torneo {

	@Override
	public Cromosoma seleccion(Cromosoma[] individuos) {
		Cromosoma mejor = null;
		
		if(individuos.length > 0) {
			mejor = individuos[0];
			for (int i = 1; i < individuos.length; i++) {
				if(mejor.getApt() < individuos[i].getApt()) {
					mejor = individuos[i];
				}
			}
		}
		
		return mejor;
	}

	@Override
	public String toString() {
		return "Torneo Determinístico";
	}

}
