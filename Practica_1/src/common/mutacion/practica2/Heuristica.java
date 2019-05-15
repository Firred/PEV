package common.mutacion.practica2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import common.Cromosoma;
import common.evaluacion.Evaluacion;
import common.genes.Gen;
import common.mutacion.Mutacion;

public class Heuristica extends Mutacion {

	private Cromosoma mejor;
	private int nGenes = 3;
	
	@Override
	protected
	Cromosoma mutacion(Cromosoma crom) {
		return mutacion(crom, nGenes);
	}
	
	public int getNGenes() {
		return this.nGenes;
	}
	
	public void setNGenes(int nGenes) {
		this.nGenes = nGenes;
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
		vueltaAtras(crom, pos, marcas, new Stack<Gen<?>>());

		return mejor;
	}
	
	private void vueltaAtras(Cromosoma crom, List<Integer> pos, boolean[] marcas, Stack<Gen<?>> genes) {
		int i = 0;
		Cromosoma c = null;
		
		while(i < pos.size()) {
			if (!marcas[i]) {
				marcas[i] = true;
				genes.push(crom.getGen(pos.get(i)));
				
				if(genes.size() == pos.size()) {	//Todos los genes colocados
					c = new Cromosoma(crom);
					Stack<Gen<?>> aux = new Stack<Gen<?>>();
				
					//Montar cromosoma	
					for(int j = pos.size()-1; j >= 0; j--) {
						aux.push(genes.peek());
						c.getGenes().set(pos.get(j), genes.pop());
					}						
					
					if(Evaluacion.evaluar(c) > mejor.getX()) {
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
