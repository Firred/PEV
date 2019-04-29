package common.genes;

public class GenInt extends Gen<Integer> {

	public GenInt(int alelo) {
		this.setCarateristica(alelo);
	}
	
	public GenInt(GenInt gen) {
		super((Gen<Integer>)gen);
	}

	@Override
	public String toString() {
		return "Gen: " + this.getCaracteristica().toString() + " ";
	}	
}
