package interfaz.controlador;

import common.Cromosoma;
import common.Poblacion;

public interface Observador {
	public void start(int generaciones, int variables);
	public void update(Poblacion pobl, Cromosoma mejorG);
	public void finish(Cromosoma mejor, String texto);
}
