package common.genes;

import java.util.Random;

public class GenBi extends Gen<Double> {
	private boolean[] alelo;
	private double min, max, prec;

	/** CONSTRUCTORES
	*/
	public GenBi(int pos, double min, double max, double prec){
		super();
		this.min = min;
		this.max = max;
		this.prec = prec;
		int n = calcLongituGen();
		alelo = new boolean[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			alelo[i] = rand.nextBoolean();
		}
		
		calcularCaracteristica();
	}
	
	public GenBi(GenBi g) {
		super(g);
		this.alelo = new boolean[g.alelo.length];
		System.arraycopy(g.alelo, 0, this.alelo, 0, g.alelo.length);
		this.min = g.min;
		this.max = g.max;
		this.prec = g.prec;
		
		calcularCaracteristica();
	}
	
	/*
	 /** Constructor con generacion aleatoria con rango y precision 
	 * @see common.Function_main
	 
	public GenBi(int n){
		super();
		//int n = calcLongituGen(Function_main.MIN,Function_main.MAX,Function_main.PRECISION);
		alelo = new boolean[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			alelo[i] = rand.nextBoolean();
		}
		
		calcularCaracteristica();
	}
	*/
	
	/* No construyes ningun objeto nuevo, usas un contenedor y simplemente vas asignando valores
	 * 
	 * Constructora a partir de otro gen (para el cruce)
	public GenBi(boolean[] gen, double min, double max) {
		super(min, max);
		
		alelo = gen.clone();
	}*/
		

	/** GETTERS
	 *  devuelve la longitud de un alelo
	 */
	public int getLong() {
		return alelo.length;
	}
	
	public boolean[] getAlelo() {
		return alelo;
	}

	/**
	 * @param n1
	 * @param n2
	 * @return
	 */
	public Boolean[] getalelo(int n1, int n2) {
		Boolean[] alelotemp = {};
		while(n1<n2) {
			alelotemp[n1] = this.alelo[n1];
			n1++;
		}
		return alelotemp;
	}
		
	/** 
	 * @see common.genes.Gen_Bi#getGenes_Bool(int)
	 */
	public Boolean getGenes_Bool(int n1) {
		return this.alelo[n1];
	}	

	/** SETTERS
	 *   Cambia el valor del alelo en la posición pos por b
	 * @param b
	 * @param pos
	 */
	public void setAlelo(boolean b, int pos) {
		alelo[pos] = b;
	}

	/**  FUNCIONES
	 * Cambia la característica por el valor que representa el alelo
	 * Usar tras cada modificación del alelo
	 */
	private void calcularCaracteristica() {
		setCarateristica(this.min + bin2Dec()* ((this.max-this.min)/(Math.pow(2, alelo.length)-1)));
	}
	

	/**
	 * @return  Devuelve el valor binario del alelo
	 */
	private int bin2Dec() {
		int n = 0;
		for (int i = alelo.length-1; i >= 0; i--) {
			if(alelo[i] == true) {
				n += Math.pow(2, i);
			}
			
		}
		
		return n;
	}
	
	
	/**
	 * @param tipo de gen
	 * @param minimo de rango
	 * @param maximo de rango
	 * @param precision
	 */
/*	public GenBi generarGen(int tipo) {
		int n = calcLongituGen();
		alelo = new boolean[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			alelo[i] = rand.nextBoolean();
		}	
		return this;
	}*/
	
	/**
	 * Calcula la longitud del gen para su rango en una precision
	 * @param min
	 * @param max
	 * @param precision
	 * @return
	 */
	private int calcLongituGen() {
		this.long_gen = (int)(Math.log(Math.abs(1+(this.max-this.min)/this.prec)/Math.log(2)));
		return this.long_gen;
	}

	@Override
	public String toString() {
		return "Gen [caracteristica=" + super.getCaracteristica() + ", long_gen=" + long_gen + "]";
	}
	
}
