package common.cruce.practica2;

import java.util.ArrayList;
import java.util.List;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.Gen;

public class CodificacionOrdinal extends Reproduccion {
	
	private List<? extends Gen<?>> lista;

	@Override
	protected
	ParCromosoma cruce(ParCromosoma par) {
		int pos = 0, aux;
		
		ArrayList<? extends Gen<?>> listaAux = new ArrayList<>(lista);
		ArrayList<Integer> orden1 = new ArrayList<>(lista.size()), orden2 = new ArrayList<>(lista.size());
		
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		
		//Obtener lista de orden
		for (int i = 0; i < lista.size(); i++) {
			pos = listaAux.indexOf(par.getC1().getGen(i));
			orden1.add(pos);
			listaAux.remove(pos);
		}
		
		listaAux = new ArrayList<>(lista);
		
		for (int i = 0; i < lista.size(); i++) {
			pos = listaAux.indexOf(par.getC2().getGen(i));
			orden2.add(pos);
			listaAux.remove(pos);
		}
	
		
		//Calcular corte en orden
		pos = rand.nextInt(lista.size());
		
		//Combinar ordenes
		for(int i = 0; i < pos; i++) {
			aux = orden1.get(i);
			orden1.set(i, orden2.get(i));
			orden2.set(i, aux);
		}
		
		//Insertar genes combinados en cromosomas
		listaAux = new ArrayList<>(lista);
		
		for(int i = 0; i < lista.size(); i++) {	
			h1.setGen(listaAux.remove((int)orden1.get(i)), i);
		}
		
		listaAux = new ArrayList<>(lista);
		
		for(int i = 0; i < lista.size(); i++) {
			h2.setGen(listaAux.remove((int)orden2.get(i)), i);
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	public void setLista(List<? extends Gen<?>> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "Codificacion Ordinal";
	}
}
