package common.seleccion.torneo;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.seleccion.Seleccion;

public abstract class Torneo extends Seleccion {
	Random rand;
	
	public Torneo() {
		//newPobl = new Poblacion();
		rand = new Random();
	}
	
	public Poblacion execute(Poblacion pobl) {
		ArrayList<Cromosoma> lista = new ArrayList<>();
		
		//Cambiar el tam por un parametro o atributo externo (en Function_main?)
		int tam = 2;
		Cromosoma[] individuos = new Cromosoma[tam];
		
		for(int i = 0; i < pobl.getTPobl(); i++) {
			for(int j = 0; j < tam; j++) {
				individuos[j] = pobl.getIndividuos(rand.nextInt(pobl.getTPoblacion()));
			}
			
			lista.add(new Cromosoma<>(seleccion(individuos)));		
		}
		
		pobl.setGeneracion(pobl.getGeneracion()+1);
		
		return pobl;
	}
	
	abstract Cromosoma seleccion(Cromosoma[] individuos);
}
