package common.genes.constructoras;

import common.genes.Gen;

public interface ConstrGen {
	/** Consctructor de genes
	 * @param min
	 * @param max
	 * @param precision
	 * @return
	 */
	public Gen construir(double min, double max, double precision);

	public Boolean[] getalelo(int n1, int n2);

	public boolean[] getAlelo();
}
