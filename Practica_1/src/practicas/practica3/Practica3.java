package practicas.practica3;

import java.util.Random;

import common.Cromosoma;
import common.cruce.practica3.CruceSubarboles;
import common.genes.GenArbol;
import common.mutacion.practica3.TerminalSimple;
import practicas.ProblemaArbol;

public class Practica3 extends ProblemaArbol<Tipo> {

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
	
	private class Posicion {
		public int x = 0;
		public int y = 0;
	
		public void suma(Direccion dir) {
			if(this.x + dir.x >= mapa.length) {
				this.x = 0;
			}
			else if (this.y + dir.y >= mapa.length) {
				this.y = 0;
			}
			else if (this.x + dir.x < 0) {
				this.x = mapa[0].length-1;
			}
			else if (this.y + dir.y < 0) {
				this.y = mapa.length-1;
			}
			else {
				this.x += dir.x;
				this.y += dir.y;
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
		super(false);
		super.setMutacion(new TerminalSimple());
		super.setReproduccion(new CruceSubarboles());
		this.rand = new Random();
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

		return this.punt + bonTiempo;
	}
	
	private void ejecutarArbol(GenArbol gen, char[][] tablero) {
		if(this.pasos < 400 && this.punt < 89) {
			
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
				if(!fueraLimites(pos.x + dir.x, pos.y + dir.y) && tablero[pos.y + dir.y][pos.x + dir.x] == '#') 
						ejecutarArbol(gen.getHijo(0), tablero);		
				else 
					ejecutarArbol(gen.getHijo(1), tablero);
				
				break;
				
			case AVANZA:
				this.pos.suma(dir);
				this.pasos++;
				break;
				
			case GIRA_DERECHA:
				this.dir.giroDer();
				this.pasos++;
				break;
				
			case GIRA_IZQUIERDA:
				this.dir.giroIzq();
				this.pasos++;
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
		
		return tablero;
	}
	
	private void rellenarTablero(GenArbol gen, char[][] tablero) {
		if(this.pasos < 400 && this.punt < 90) {
			
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
				if(!fueraLimites(pos.x + dir.x, pos.y + dir.y) && tablero[pos.y + dir.y][pos.x + dir.x] == '#') 
						rellenarTablero(gen.getHijo(0), tablero);			
				else
					rellenarTablero(gen.getHijo(1), tablero);
				
				break;
				
			case AVANZA:
				this.pos.suma(dir);
				this.pasos++;
				break;
				
			case GIRA_DERECHA:
				this.dir.giroDer();
				this.pasos++;
				break;
				
			case GIRA_IZQUIERDA:
				this.dir.giroIzq();
				this.pasos++;
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
		String s = "El mejor recorrido obtiene " + this.punt + "uds. de comida en " + this.pasos + " pasos." + System.lineSeparator();
		
		s += ((GenArbol)crom.getGen(0)).caracteristicaString(0);
		
		return s;
	}

	@Override
	public Tipo obtenerTerminal() {
		return Tipo.getTerminales()[rand.nextInt(Tipo.getTerminales().length)];
	}

	@Override
	public Tipo obtenerFuncion() {
		return Tipo.getFunciones()[rand.nextInt(Tipo.getFunciones().length)];
	}

	@Override
	public Tipo obtenerTodos() {
		return Tipo.values()[rand.nextInt(Tipo.values().length)];
	}

	@Override
	public int ramificacionesFuncion(Tipo tipo) {
		switch (tipo) {
		case PROGN2:
			return 2;

		case PROGN3:
			return 3;
			
		case SIC:
			return 2;
			
		case AVANZA:
			return 0;
			
		case GIRA_DERECHA:
			return 0;
			
		case GIRA_IZQUIERDA:
			return 0;
			
		default:
			return 0;
		}
	}

	@Override
	public void bajoFitness(Cromosoma crom) {
		if(crom.getApt() > 31)
			crom.setApt(crom.getApt()-30);
		else
			crom.setApt(0.0125);
	}
	
	@Override
	public int maximoNodos() {
		return 100;
	}
}
