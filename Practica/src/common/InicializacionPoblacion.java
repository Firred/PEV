package common;

import java.util.ArrayList;
import java.util.Random;

import common.genes.GenArbol;
import practicas.ProblemaArbol;

public class InicializacionPoblacion {
	
	private static Random rand = new Random();
	
	public static ArrayList<Cromosoma> inicializarPoblacion(int tamPobl, ProblemaArbol func) {
		ArrayList<Cromosoma> lista = new ArrayList<>();
		Cromosoma crom; 
		int tamGrupos = tamPobl/(func.getNMax()-1), resto = tamPobl%(func.getNMax()-1);
		int i;
		
		for(int prof = 2; prof <= func.getNMax(); prof++) {
			i = 0;
			while(i < tamGrupos) {
				crom = new Cromosoma();
				crom.getGenes().add(inicializacionCreciente(prof, func, true));
				lista.add(crom);
				
				i++;
				
				if(i < tamGrupos) {
					crom = new Cromosoma();
					crom.getGenes().add(inicializacionCompleta(prof, func));
					lista.add(crom);
					
					i++;
				}
			}
		}
		
		
		i = 0;
		while(resto > i) {
			crom = new Cromosoma();
			
			//Coloca los individuos alternando entre el grupo i en caso de par
			//y el maximo de profundidad - i en caso de ser impar.
			//Se busca asi repartir los cromosomas restantes de la mejor forma posible
			//sin favorecer a ningun grupo
			//En el caso de ser tamGrupos impar, se crean cromosomas con inicializacion
			//completa buscando igualar el numero de inicializaciones en ambos metodos
			if(tamGrupos%2 != 0) {
				if(i%2 != 0)
					crom.getGenes().add(inicializacionCompleta(2+i, func));
				else
					crom.getGenes().add(inicializacionCompleta(func.getNMax()-i, func));
			}	
			else {
				if(i%2 != 0) {
					if(rand.nextBoolean())
						crom.getGenes().add(inicializacionCompleta(2+i, func));
					else
						crom.getGenes().add(inicializacionCreciente(2+i, func, true));
				}
				else {
					if(rand.nextBoolean())
						crom.getGenes().add(inicializacionCompleta(func.getNMax()-i, func));
					else
						crom.getGenes().add(inicializacionCreciente(func.getNMax()-i, func, true));
				}
			}
			
			lista.add(crom);
			
			i++;
		}
		
		return lista;
	}
	
	public static GenArbol inicializarArbol(int profundidad, ProblemaArbol func) {
		if(rand.nextBoolean()) {
			return inicializacionCreciente(profundidad, func, false);
		}
		else {
			return inicializacionCompleta(profundidad, func);
		}
	}
	
	private static GenArbol inicializacionCreciente(int profundidad, ProblemaArbol func, boolean primero) {
		GenArbol gen;
		
		if(primero) { //Con esto se intenta evitar que haya arboles con solo 1 nodo terminal
			gen = new GenArbol(func.obtenerFuncion());
			
			for(int i = 0; i < func.ramificacionesFuncion(gen.getCaracteristica()); i++) {
				gen.addHijo(inicializacionCreciente(profundidad-1, func, false));
			}
		}
		else if(profundidad > 1) {
			gen = new GenArbol(func.obtenerTodos());
			
			for(int i = 0; i < func.ramificacionesFuncion(gen.getCaracteristica()); i++) {
				gen.addHijo(inicializacionCreciente(profundidad-1, func, false));
			}
		}
		else {
			gen = new GenArbol(func.obtenerTerminal());
		}
		
		return gen;
	}
	
	private static GenArbol inicializacionCompleta(int profundidad, ProblemaArbol func) {
		GenArbol gen;
		
		if(profundidad > 1) {
			gen = new GenArbol(func.obtenerFuncion());
			
			for(int i = 0; i < func.ramificacionesFuncion(gen.getCaracteristica()); i++) {
				gen.addHijo(inicializacionCompleta(profundidad-1, func));
			}
		}
		else {
			gen = new GenArbol(func.obtenerTerminal());
		}
		
		return gen;
	}
}
