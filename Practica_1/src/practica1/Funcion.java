package practica1;

import common.Cromosoma;

public abstract class  Funcion {
	
	public final double[] MIN;
	public final double[] MAX;
	public final boolean MINIMIZAR;

	public Funcion(double[] min, double[] max, boolean minimizar) {
		this.MAX = min;
		this.MIN = max;
		this.MINIMIZAR = minimizar;
	}
	
	public abstract double Evalua(Cromosoma ind_evaluar);
//	public abstract void Set_Function();
	public abstract String toString();
}
