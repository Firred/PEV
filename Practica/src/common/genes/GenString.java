package common.genes;

public class GenString extends Gen<String> {	
	
	public GenString(String alelo) {
		this.setCarateristica(alelo);
	}
	
	public GenString(GenString gen) {
		super((Gen<String>)gen);
	}

	@Override
	public String toString() {
		return "Gen: " + this.getCaracteristica() + " ";
	}
}
