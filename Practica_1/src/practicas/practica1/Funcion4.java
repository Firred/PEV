package practicas.practica1;

import java.util.ArrayList;

import common.Cromosoma;
import common.genes.Gen;
import common.genes.GenBi;

public class Funcion4 extends Funcion{
	
	private static double[] min = new double[] {0};
	private static double[] max = new double[] {Math.PI};
	private int n = 7;
	
	public Funcion4() {
		super(min, max, true);
	}
	
	public int getN() {
		return this.n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	@Override
	public int getNumGenes() {
		return this.n;
	}
	
	@Override
	public double evalua(Cromosoma<Double> crom) {	
		double y = 0, paramSeno, aux;

//		System.out.println(prueba());
		
		for(int i = 1; i <= this.n; i++) {
			paramSeno = ((i+1)*Math.pow(crom.getGen(i-1).getCaracteristica(),2))/Math.PI;
			aux = Math.sin(crom.getGen(i-1).getCaracteristica())*Math.pow(Math.sin(paramSeno), 20);
			y += aux;
		}
		
		return -y;
	}

	@Override
	public String toString() {
		return "Funcion 4";
	}
	
	@Override
	public ArrayList<? extends Gen<Double>> crearGenes(double... args) {
		if(args.length != 1)
			return null;
		
		ArrayList<GenBi> genes = new ArrayList<>();
		
		for(int i = 0; i < this.n; i++) 
			genes.add(new GenBi(i, MIN[0], MAX[0], args[0]));
		
		return genes;
	}
}
