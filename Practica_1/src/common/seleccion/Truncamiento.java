package common.seleccion;

import java.util.Collections;

import common.Poblacion;

public class Truncamiento extends Seleccion {
	
	private double trunc = 0.2;
	
	public double getTrunc() {
		return this.trunc;
	}
	
	public void setTrunc(double trunc) {
		this.trunc = trunc;
	}
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		int repeticiones = (int)(1/this.trunc);
		int corte = (int)(this.trunc*pobl.getTpobl());

		Collections.sort(pobl.getIndividuos(), Collections.reverseOrder());
		
		for(int i = 0; i < corte; i++) {
			for(int j = 0; j < repeticiones; j++) {
				newPobl.addIndividuo(pobl.getIndividuos(i));
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
