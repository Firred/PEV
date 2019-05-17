package common.condicion;

import common.Poblacion;

public interface CondicionTerminacion {

	public boolean execute(Poblacion poblAct, Poblacion poblAnt);
}