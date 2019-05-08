package practicas.practica1;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.genes.Gen;
import common.genes.GenBi;
import common.genes.GenReal;

public class Funcion5 extends Funcion {
	
	private static double[] min = new double[] {0};
	private static double[] max = new double[] {Math.PI};
	private int n = 7;	
	private Random rand;
	
	public Funcion5() {
		super(min, max, true);
		this.rand = new Random();
	}
	
	@Override
	public double evalua(Cromosoma<Double> crom) {	
		double y = 0, paramSeno, aux;
		
		for(int i = 1; i <= this.n; i++) {
			paramSeno = ((i+1)*Math.pow(crom.getGen(i-1).getCaracteristica(),2))/Math.PI;
			aux = Math.sin(crom.getGen(i-1).getCaracteristica())*Math.pow(Math.sin(paramSeno), 20);
			y += aux;
		}
		
		return -y;
	}
	
	@Override
	public ArrayList<? extends Gen<Double>> crearGenes(double... args) {
		if(args.length != 1)
			return null;
		
		ArrayList<GenReal> genes = new ArrayList<>();
		
		for(int i = 0; i < this.n; i++) {
			genes.add(new GenReal(rand.nextDouble()*Math.PI, 0, Math.PI));
		}
		
		return genes;
	}

	@Override
	public String toString() {
		return "Funcion 5";
	}
}
