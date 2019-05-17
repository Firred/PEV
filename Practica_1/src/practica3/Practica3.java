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
	
	private class Posicion {
		public int x;
		public int y;
	
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
		super(true, 1);
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
		
		while(this.pasos < 400 && this.punt < 90)
			ejecutarArbol((GenArbol)crom.getGen(0));
		
		System.out.println("Practica3.evalua: " + this.punt);
		return this.punt;
	}

	public static void main(String[] args) {
		CromosomaArbol crom = new CromosomaArbol();
		GenArbol gen = new GenArbol(Tipo.AVANZA);

		((ArrayList<GenArbol>)crom.getGenes()).add(gen);
		
		Practica3 p3 = new Practica3();		
		
		p3.evalua(crom);
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
	
	
	
	private void ejecutarArbol(GenArbol gen) {
		if(this.pasos < 400 && this.punt < 90) {
			this.pasos++;
			
			if(mapa[pos.y][pos.x] == '#') {
				this.punt++;
			}
			
			switch (gen.getCaracteristica()) {
			case PROGN3:
				ejecutarArbol(gen.getHijo(0));
				ejecutarArbol(gen.getHijo(1));
				ejecutarArbol(gen.getHijo(2));
				
				break;

			case PROGN2:
				ejecutarArbol(gen.getHijo(0));
				ejecutarArbol(gen.getHijo(1));
				
				break;
				
			case SIC:
				if(mapa[pos.y + dir.y][pos.x + dir.x] == '#') 
					ejecutarArbol(gen.getHijo(0));			
				else
					ejecutarArbol(gen.getHijo(1));
				
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
	
	@Override
	public String toString() {
		return "Practica 3";
	}
}
