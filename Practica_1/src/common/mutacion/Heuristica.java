package common.mutacion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import common.Cromosoma;
import common.evaluacion.Evaluacion;
import common.genes.Gen;

public class Heuristica<T> extends Mutacion {

	private Cromosoma mejor;
	
	/**
	 * Realiza la mutación heurística por defecto (n = 3)
	 * 
	 */
	@Override
	Cromosoma mutacion(Cromosoma crom) {
		return mutacion(crom, 3);
	}
	
	Cromosoma mutacion(Cromosoma crom, int n) {
		ArrayList<Integer> pos = new ArrayList<>();
		int aux;
		boolean[] marcas = new boolean[n];

		for(int i = 0; i < n; i++) {		
			do {
				aux = rand.nextInt(crom.getNumGenes());
			}
			while(pos.contains(aux));
			
			pos.add(aux);
		}	

		mejor = crom;
		
		Evaluacion.evaluar(crom);
		vueltaAtras(crom, pos, marcas, new Stack<Gen<T>>());
		
		return mejor;
	}
	
	private void vueltaAtras(Cromosoma crom, List<Integer> pos, boolean[] marcas, Stack<Gen<T>> genes) {
		int i = 0;
		Cromosoma c = null;
		
		while(i < pos.size()) {
			
			if (!marcas[i]) {
				marcas[i] = true;
				genes.push(crom.getGen(pos.get(i)));
				if(genes.size() == pos.size()) {
					c = new Cromosoma(crom);
					Stack<Gen<T>> aux = new Stack<Gen<T>>();
				
					//Montar cromosoma	
					for(int j = pos.size()-1; j >= 0; j--) {
						aux.push(genes.peek());
						c.getGenes().set(pos.get(j), genes.pop());
					}						
					
					if(Evaluacion.evaluar(c) > mejor.getApt()) {
						mejor = c;
					}
					
					for(int j = 0; j < pos.size(); j++) {
						genes.push(aux.pop());
					}
				}
				else {
					vueltaAtras(crom, pos, marcas, genes);
				}
				
				genes.pop();
				marcas[i] = false;
			}

			i++;
		}
		
		return;
	}
	
	@Override
	public String toString() {
		return "Heuristica";
	}
}
