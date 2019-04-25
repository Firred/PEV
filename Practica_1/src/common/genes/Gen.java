package common.genes;

public abstract class Gen<T> {

	private T caracteristica; //Caracteristica que representa el alelo del gen
	protected int long_gen;
	
	/** CONSTRUCTORES
	 * 
	 */		
	protected Gen() {}
	
	protected Gen(int n) {
		this.long_gen = n;
	}
	
	public Gen (Gen<T> g) {
		this.caracteristica = g.caracteristica;
		this.long_gen = g.long_gen;
	}
		
	
	/** GETTERS
	 * @return caracteristica
	 */
	public T getCaracteristica() {
		return caracteristica;
	}

	/**
	 * @return longitud del gen
	 */
	public int getTam() {
		return long_gen;
	}
	
	/** SETTERS
	 * @param c valor caracteristica
	 */
	public void setCarateristica(T c) {
		caracteristica = c;
	}
/*	public void setAlelo(double valor) {
		controller.setAlelo(valor);
	}*/
	
	/*public double getAlelo() {
		return controller.getAlelo();
	}*/
	
	@Override
	public String toString() {
		return "Gen [caracteristica=" + caracteristica + ", long_gen=" + long_gen + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o.getClass() == this.getClass()) {
			if (this.getCaracteristica() == ((Gen<T>)o).getCaracteristica()) {
				return true;
			}
		}
		
		return false;
	}
	
//	public abstract void calcularCaracteristica();

}
