package common.cruce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import common.Cromosoma;
import common.genes.Gen;

public class CodificacionOrdinal extends Reproduccion {
	
	private List<? extends Gen<?>> lista;

	@Override
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
		pos = rand.nextInt(par.getC1().getNumGenes());
		
		//Combinar ordenes
		ListIterator<Integer> it1 = orden1.listIterator(), it2 = orden2.listIterator();
		
		while(it1.hasNext() && it1.nextIndex() <= pos) {
			aux = it1.next();
			it1.set(it2.next());
			it2.set(aux);
		}

		//Insertar genes combinados en cromosomas
		listaAux = new ArrayList<>(lista);
		
		ListIterator<Gen> itG1 = h1.getGenes().listIterator(), itG2 = h2.getGenes().listIterator();
		it1 = orden1.listIterator();
		
		while(itG1.hasNext()) {
			itG1.next(); 
			
			itG1.set(listaAux.get(it1.next()));	
			listaAux.remove(it1.previousIndex());
		}
		
		listaAux = new ArrayList<>(lista);
		it2 = orden2.listIterator();
		
		while(itG2.hasNext()) {
			itG2.next(); 
			
			itG2.set(listaAux.get(it2.next()));	
			listaAux.remove(it2.previousIndex());
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
