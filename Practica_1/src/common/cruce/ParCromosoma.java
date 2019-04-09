package common.cruce;

import common.Cromosoma;

public class ParCromosoma {
	private Cromosoma c1;
	private Cromosoma c2;
	
	public ParCromosoma(Cromosoma c1, Cromosoma c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	/** GETTERS
	 * @return cromosoma 1
	 */
	public Cromosoma getC1() {
		return c1;
	}
	/**
	 * @return cromosoma 2
	 */
	public Cromosoma getC2() {
		return c2;
	}
	
	/** SETTERS
	 * @param c1 cromosoma 1
	 */
	public void setC1(Cromosoma c1) {
		this.c1 = c1;
	}
	
	/**
	 * @param c2 cromosoma 2
	 */
	public void setC2(Cromosoma c2) {
		this.c2 = c2;
	}
}

