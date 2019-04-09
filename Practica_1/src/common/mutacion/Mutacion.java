package common.mutacion;

import common.Poblacion;

public interface Mutacion {
	public void execute(Poblacion pobl, int prob);
}
