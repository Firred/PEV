package interfaz.controlador;

import common.AlgoritmoGenetico;
import common.Cromosoma;
import common.Poblacion;

public abstract class Controlador {
	private static ControladorImp ctrl;
	
	public static ControladorImp getInstance() {
		if (ctrl == null)
			ctrl = new ControladorImp();
		
		return ctrl;
	}
	
	public abstract void start(int generaciones, int variables);
	public abstract void execute();	
	public abstract void addObservador(Observador obs);	
	public abstract void update(Poblacion pobl, Cromosoma mejorG);
	public abstract void finish(Cromosoma mejor, String texto);
	public abstract AlgoritmoGenetico getAG();
}
