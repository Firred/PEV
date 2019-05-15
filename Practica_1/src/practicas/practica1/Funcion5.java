package practicas.practica1;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.cruce.practica1.Monopunto;
import common.genes.Gen;
import common.genes.GenReal;
import common.mutacion.practica1.NoUniforme;
import practicas.ProblemaNoBinario;

public class Funcion5 extends ProblemaNoBinario<Double> {
	
	private static int n = 7;
	private Random rand;
	
	public Funcion5() {
		super(true, n);
		this.rand = new Random();
		this.setReproduccion(new Monopunto());
		this.setMutacion(new NoUniforme());
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
}