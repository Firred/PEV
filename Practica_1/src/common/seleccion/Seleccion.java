package common.seleccion;

import common.Poblacion;

public abstract class Seleccion {
	public abstract Poblacion execute(Poblacion pobl);
	public abstract String toString();
}
