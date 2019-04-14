package common.mutacion;

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
		int[] pos = new int[n];
		boolean[] marcas = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			pos[i] = rand.nextInt(crom.getNumGenes());
		}	
		
		mejor = crom;
		
		Evaluacion.evaluar(crom);
		vueltaAtras(crom, pos, marcas, new Stack<Gen<T>>());
		
		return mejor;
	}
	
	private void vueltaAtras(Cromosoma crom, int[] pos, boolean[] marcas, Stack<Gen<T>> genes) {
		int i = 0;
		Cromosoma c = null;
		
		while(i < pos.length) {
			if (!marcas[i]) {
				marcas[i] = true;
				genes.push(crom.getGen(i));
				
				if(genes.size() == pos.length) {
					c = new Cromosoma(crom);
					
					for(int j = pos.length-1; j >= 0; j--) {
						c.getGenes().set(pos[j], genes.pop());
					}						
					
					if(Evaluacion.evaluar(c) > mejor.getApt()) {
						mejor = c;
					}				
				}
				else {
					vueltaAtras(crom, pos, marcas, genes);
				}
				
				genes.pop();
				marcas[i] = false;
			}
		}
		
		return;
	}
}
