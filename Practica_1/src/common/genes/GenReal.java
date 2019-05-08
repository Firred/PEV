package common.genes;

public class GenReal extends Gen<Double> {

	public GenReal(double alelo, double min, double max) {
		this.setCarateristica(alelo);
	}
	
	public GenReal(GenReal gen) {
		super((Gen<Double>)gen);
	}
	
	@Override
	public String toString() {
		return "Gen: " + this.getCaracteristica() + " ";
	}
}
