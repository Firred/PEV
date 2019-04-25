package common.genes;

import common.evaluacion.Function_main;

public class GenReal extends Gen{


	private double alelo;
	private int pos;
	
	public GenReal(int i) {
		this.pos = i;
		this.alelo = Math.random()*(Function_main.MAX[this.pos]-Function_main.MIN[this.pos]+1)+Function_main.MIN[this.pos];
	}

	public void setAlelo(double alelo) {
		this.alelo = alelo;
	}
	
	public double getAlelo() {
		return alelo;
	}
	@Override
	public String toString() {
		return "GenReal [alelo=" + alelo + ", pos=" + pos + "]";
	}	
}
