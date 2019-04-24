package interfaz.controlador;

import java.util.ArrayList;

import common.Cromosoma;
import common.Poblacion;
import common.evaluacion.Function_Controller;
import practicas.practica1.AlgoritmoGenetico;

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
	public AlgoritmoGenetico getAG() {
		return this.aG;
	}
}
