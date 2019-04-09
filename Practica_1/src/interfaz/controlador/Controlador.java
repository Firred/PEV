package interfaz.controlador;

import common.Cromosoma;
import common.Poblacion;
import practica1.AlgoritmoGenetico;

public abstract class Controlador {
	private static ControladorImp ctrl;
	
	public static ControladorImp getInstance() {
		if (ctrl == null)
			ctrl = new ControladorImp();
		
		return ctrl;
	}
	
	
	public abstract void execute();	
	public abstract void addObservador(Observador obs);	
	public abstract void update(Poblacion pobl, Cromosoma mejorG);
	public abstract AlgoritmoGenetico getAG();
}
