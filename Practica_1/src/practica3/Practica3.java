package practica3;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.cruce.practica3.CruceSubarboles;
import common.genes.Gen;
import common.mutacion.practica3.TerminalSimple;
import practicas.ProblemaNoBinario;

public class Practica3 extends ProblemaNoBinario<Tipo> {

	private final static char[][] mapa = {
			{ '@', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0', '0', '0' },
			{ '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0' },
			{ '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0' },
			{ '0', '0', '0', '#', '#', '#', '#', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '#', '#', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '#', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' }
	};
	
	private int pasos, punt;
	private Posicion pos;
	private Direccion dir;
	private Random rand;
	private int nMin, nMax;
	
	private class Posicion {
		public int x = 0;
		public int y = 0;
	
		public void suma(Direccion dir) {
			if((this.x + dir.x) < mapa.length && (this.x + dir.x) >= 0) {
				if((this.y + dir.y) < mapa.length && (this.y + dir.y) >= 0) {
					this.x += dir.x;
					this.y += dir.y;
				}
			}		
		}
	}
	
	private class Direccion {
		public int x;
		public int y;
		
		public Direccion() {
			this.x = 1;
			this.y = 0;
		}
		
		public void giroDer() {
			if(this.x == 0) {
				if(this.y == 1)
					this.x = -1;
				else
					this.x = 1;
				
				this.y = 0;
			}
			else {
				if(this.x == 1)
					this.y = 1;
				else
					this.y = -1;
				
				this.x = 0;
			}
		}
		
		public void giroIzq() {
			if(this.x == 0) {
				if(this.y == 1)
					this.x = 1;
				else
					this.x = -1;
				
				this.y = 0;
			}
			else {
				if(this.x == 1)
					this.y = -1;
				else
					this.y = 1;
				
				this.x = 0;
			}
		}
	}
	
	public Practica3() {
		super(false, 1);
		super.setMutacion(new TerminalSimple());
		super.setReproduccion(new CruceSubarboles());
		this.rand = new Random();
		this.nMin = 2;
		this.nMax = 10;
	}

	public int getNMin() {
		return nMin;
	}

	public void setNMin(int nMin) {
		this.nMin = nMin;
	}

	public int getNMax() {
		return nMax;
	}

	public void setNMax(int nMax) {
		this.nMax = nMax;
	}

	@Override
	public double evalua(Cromosoma<Tipo> crom) {
		this.pasos = 0;
		this.punt = 0;
		this.pos = new Posicion();
		this.dir = new Direccion();
		char[][] tablero = new char[mapa.length][mapa[0].length];
		
		for(int i = 0; i < mapa.length; i++) {
			for(int j = 0; j < mapa[i].length; j++) {
				tablero[i][j] = mapa[i][j];
			}
		}
		
		while(this.pasos < 400 && this.punt < 90)
			ejecutarArbol((GenArbol)crom.getGen(0), tablero);
		
		int bonTiempo = 400-this.pasos;
		
//		System.out.println(this.punt + ", " + this.pasos);
		
		return this.punt + bonTiempo;
	}
	
	@Override
	public ArrayList<? extends Gen<Tipo>> crearGenes(double... args) {
//		if(args.length != 2)
//			return null;
//		double[] arg = {1,1};

		ArrayList<Gen<Tipo>> lista = new ArrayList<>();
		
		int pMin = (int)args[0], pMax = (int)args[1];
		GenArbol gen = new GenArbol();
		
		if(pMin > 0) {
			gen.setCarateristica(Tipo.getFunciones()[rand.nextInt(Tipo.getFunciones().length)]);
			
			gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
			gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
			
			if(gen.getCaracteristica() == Tipo.PROGN3) {
				gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
			}
		}
		else {
			if(pMax == 0) {
				gen = new GenArbol(Tipo.getTerminales()[rand.nextInt(Tipo.getTerminales().length)]);
			}
			else {
				if(rand.nextBoolean()) {	//Elige funcion
					gen.setCarateristica(Tipo.getFunciones()[rand.nextInt(Tipo.getFunciones().length)]);
					
					gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
					gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
					
					if(gen.getCaracteristica() == Tipo.PROGN3) {
						gen.addHijo(crearGenes(pMin-1, pMax-1).get(0));
					}
				}
				else { 		//Elige terminal
					gen = new GenArbol(Tipo.getTerminales()[rand.nextInt(Tipo.getTerminales().length)]);
				}
			}
		}
		
		lista.add(gen);
		
		return lista;
	}	
	
	private void ejecutarArbol(GenArbol gen, char[][] tablero) {				
		if(this.pasos < 400 && this.punt < 90) {
			this.pasos++;
			
			if(tablero[pos.y][pos.x] == '#') {
				this.punt++;
				tablero[pos.y][pos.x] = '.';
			}
			
			switch (gen.getCaracteristica()) {
			case PROGN3:
				ejecutarArbol(gen.getHijo(0), tablero);
				ejecutarArbol(gen.getHijo(1), tablero);
				ejecutarArbol(gen.getHijo(2), tablero);
				
				break;

			case PROGN2:
				ejecutarArbol(gen.getHijo(0), tablero);
				ejecutarArbol(gen.getHijo(1), tablero);
				
				break;
				
			case SIC:
				if(!fueraLimites(pos.x + dir.x, pos.y + dir.y)) {
					if(tablero[pos.y + dir.y][pos.x + dir.x] == '#') 	
						ejecutarArbol(gen.getHijo(0), tablero);			
				}
				else
					ejecutarArbol(gen.getHijo(1), tablero);
				
				break;
				
			case AVANZA:
				this.pos.suma(dir);
				
				break;
				
			case GIRA_DERECHA:
				this.dir.giroDer();
				
				break;
				
			case GIRA_IZQUIERDA:
				this.dir.giroIzq();
				
				break;
				
			default:
				System.out.println("Error al ejecutar arbol. Comando " + gen.getCaracteristica().toString() + " no reconocido.");
				break;
			}
		}
	}
	
	public char[][] pintarTablero(Cromosoma crom) {		
		char[][] tablero = new char[mapa.length][mapa[0].length];
		
		for(int i = 0; i < mapa.length; i++) {
			for(int j = 0; j < mapa[i].length; j++) {
				tablero[i][j] = mapa[i][j];
			}
		}
		
		this.pasos = 0;
		this.punt = 0;
		this.pos = new Posicion();
		this.dir = new Direccion();	
		
		while(this.pasos < 400 && this.punt < 90)
			rellenarTablero((GenArbol)crom.getGen(0), tablero);
		
/*		s = "";
		for(int i = 0; i < mapa.length; i++) {
			for(int j = 0; j < mapa[i].length; j++) {
				s += tablero[i][j] + " ";
			}
			s += System.lineSeparator();
		}
		
		System.out.println(s);*/
		
		return tablero;
	}
	
	private void rellenarTablero(GenArbol gen, char[][] tablero) {
		if(this.pasos < 400 && this.punt < 90) {
			this.pasos++;
			
			if(tablero[pos.y][pos.x] == '#') {
				this.punt++;
				tablero[pos.y][pos.x] = '2';
			}
			else if(tablero[pos.y][pos.x] == '2'){
				tablero[pos.y][pos.x] = '2';
			}
			else {
				tablero[pos.y][pos.x] = '1';
			}
			
			switch (gen.getCaracteristica()) {
			case PROGN3:
				rellenarTablero(gen.getHijo(0), tablero);
				rellenarTablero(gen.getHijo(1), tablero);
				rellenarTablero(gen.getHijo(2), tablero);
				
				break;

			case PROGN2:
				rellenarTablero(gen.getHijo(0), tablero);
				rellenarTablero(gen.getHijo(1), tablero);
				
				break;
				
			case SIC:
				if(!fueraLimites(pos.x + dir.x, pos.y + dir.y)) {
					if(tablero[pos.y + dir.y][pos.x + dir.x] == '#')
						rellenarTablero(gen.getHijo(0), tablero);			
				}	
				else
					rellenarTablero(gen.getHijo(1), tablero);
				
				break;
				
			case AVANZA:
				this.pos.suma(dir);
				
				break;
				
			case GIRA_DERECHA:
				this.dir.giroDer();
				
				break;
				
			case GIRA_IZQUIERDA:
				this.dir.giroIzq();
				
				break;
				
			default:
				System.out.println("Error al ejecutar arbol en rellenar tablero. Comando " + gen.getCaracteristica().toString() + " no reconocido.");
				break;
			}
		}
	}
	
	private boolean fueraLimites(int x, int y) {
		return (x < 0 || x >= mapa.length || y < 0 || y >= mapa[0].length) ? true : false;
	}
	
	@Override
	public String toString() {
		return "Practica 3";
	}
	
	public String cromToString(Cromosoma crom) {
		evalua(crom);
		String s = "El mejor recorrido obtiene " + this.punt + "uds. de comida en " + this.pasos + " pasos.";
		
		return s;
	}
}
