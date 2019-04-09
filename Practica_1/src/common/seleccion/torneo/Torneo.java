package common.seleccion.torneo;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.seleccion.Seleccion;

public abstract class Torneo extends Seleccion {
	private Poblacion newPobl = new Poblacion();
	Random rand;
	
	public Torneo() {
		//newPobl = new Poblacion();
		rand = new Random();
	}
	
	public Poblacion execute(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		//Cambiar el tam por un parametro o atributo externo (en Function_main?)
		int tam = 2;
		Cromosoma[] individuos = new Cromosoma[tam];
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			for(int j = 0; j < tam; j++) {
				individuos[j] = pobl.getIndividuos(rand.nextInt(pobl.getTpobl()));
			}
			
			newPobl.addIndividuo(seleccion(individuos));
			//newPobl.setIndividuos(seleccion(individuos), i);			
		}
		
		newPobl.setGeneracion(pobl.getGeneracion()+1);
		
		return newPobl;
	}
	
	abstract Cromosoma seleccion(Cromosoma[] individuos);
}
