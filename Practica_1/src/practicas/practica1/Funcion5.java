package practicas.practica1;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.genes.Gen;
import common.genes.GenReal;

public class Funcion5 extends Funcion4 {
	
	private Random rand;
	
	public Funcion5() {
		this.rand = new Random();
	}
	
	@Override
	public ArrayList<? extends Gen<Double>> crearGenes(double... args) {
		if(args.length != 1)
			return null;
		
		ArrayList<GenReal> genes = new ArrayList<>();
		
		for(int i = 0; i < this.getN(); i++) {
			genes.add(new GenReal(rand.nextDouble()*Math.PI, 0, Math.PI));
		}
		
		return genes;
	}

	@Override
	public String toString() {
		return "Funcion 5";
	}
}