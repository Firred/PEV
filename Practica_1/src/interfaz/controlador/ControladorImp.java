package interfaz.controlador;

import java.util.ArrayList;

import common.AlgoritmoGenetico;
import common.Cromosoma;
import common.Poblacion;

public class ControladorImp extends Controlador {
	
	private ArrayList<Observador> obs = new ArrayList<>();
	private AlgoritmoGenetico aG;
	
	public ControladorImp() {
		this.aG = new AlgoritmoGenetico();
	}
	
	public void execute() {
		System.out.println(this.aG.exe(this));
		System.gc();
	}
	
	public void addObservador(Observador obs) {
		this.obs.add(obs);
	}
	
	public void update(Poblacion pobl, Cromosoma mejorG) {
		for (Observador o : this.obs) {
			o.update(pobl, mejorG);
		}
	}
	
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
}
