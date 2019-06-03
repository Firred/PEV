package common.seleccion;

import java.util.ArrayList;
import java.util.Collections;

import common.Cromosoma;
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
		ArrayList<Cromosoma> lista = new ArrayList<>();
		int repeticiones = (int)(1/this.trunc);
		int corte = (int)(this.trunc*pobl.getTPoblacion());

		Collections.sort(pobl.getIndividuos(), Collections.reverseOrder());
		
		for(int i = 0; i < corte; i++) {
			for(int j = 0; j < repeticiones; j++) {
				lista.add(pobl.getIndividuos(i));
			}
		}
		
		pobl.setGeneracion(pobl.getGeneracion()+1);
//		System.out.println(pobl.getTPoblacion());
		return pobl;
	}

	@Override
	public String toString() {
		return "Truncamiento";
	}

}
