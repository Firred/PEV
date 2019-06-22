package common.condicion;

import common.Cromosoma;
import common.Poblacion;

public class Contenido implements CondicionTerminacion {

	private double tol = 0.02;
	private int it;
	
	public double getTol() {
		return tol;
	}
	
	public void setTol(double tol) {
		this.tol = tol;
	}
	
	@Override
	public boolean execute(Poblacion poblAct, Poblacion poblAnt) {
		if(poblAnt == null) {
			it = 0;
			return false;
		}
		
		if(poblAct.getMejor().compareTo(poblAnt.getMejor()) <= 0) {
			if(poblAct.getAptMedia() <= poblAnt.getAptMedia()) {
				if(Math.abs(poblAct.getAptMedia()-poblAnt.getAptMedia()) > 0.02) {
					if(it > 100) {
						return true;
					}
					else {
						it++;
						return false;
					}
				}
			}
		}
		
		return false;
	}
}
