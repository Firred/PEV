package common.mutacion;

import common.Cromosoma;

public class Inversion extends Mutacion {
	
	Cromosoma mutacion (Cromosoma crom) {
		int nGenes = crom.getNumGenes();
		int ini = rand.nextInt(nGenes-1);
		int fin = rand.nextInt(nGenes-ini)+ini;
		
		Cromosoma hijo = new Cromosoma(crom);
		
		for (int i = 0; i < (fin-ini)/2; i++) {
			hijo.getGenes().set(ini+i, crom.getGen(fin-i));
			hijo.getGenes().set(fin-i, crom.getGen(ini+i));
		}
		
		return hijo;
	}
	
	@Override
	public String toString() {
		return "Inversion";
	}
}
