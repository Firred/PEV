package interfaz.controlador;

import java.util.ArrayList;

import common.AlgoritmoGenetico;
import common.Cromosoma;
import common.Poblacion;
import interfaz.PanelTablero;
import practica3.Practica3;

public class ControladorImp extends Controlador {
	
	private ArrayList<Observador> obs = new ArrayList<>();
	private AlgoritmoGenetico aG;
	
	public ControladorImp() {
		this.aG = new AlgoritmoGenetico();
	}
	
	@Override
	public void execute() {
		System.out.println(this.aG.exe(this));
		System.gc();
	}
	
	@Override
	public void addObservador(Observador obs) {
		this.obs.add(obs);
	}
	
	@Override
	public void update(Poblacion pobl, Cromosoma mejorG) {
		for (Observador o : this.obs) {
			o.update(pobl, mejorG);
		}
	}
	
	@Override
	public void start(int generaciones, int variables) {
		for (Observador o : this.obs) {
			o.start(generaciones, variables);
		}
	}
	
	@Override
	public void finish(Cromosoma mejor, String texto) {
		for (Observador o : this.obs) {
			o.finish(mejor, texto);
		}
	}

	@Override
	public AlgoritmoGenetico getAG() {
		return this.aG;
	}

	@Override
	public void actualizarTablero(PanelTablero tablero) {
		if(Practica3.class.isAssignableFrom(this.aG.getFuncion().getClass())) {
			tablero.setTablero(((Practica3)this.aG.getFuncion()).pintarTablero(this.aG.getMejor()));
			tablero.setVisible(true);
		}
	}	
}
