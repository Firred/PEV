package common.mutacion;

import common.Cromosoma;

public class Insercion extends Mutacion {
	
	@Override
	Cromosoma mutacion(Cromosoma crom) {
		int posGen = rand.nextInt(crom.getNumGenes());
		int posInsert = rand.nextInt(crom.getNumGenes());
		Cromosoma hijo = new Cromosoma(crom);
		
		hijo.getGenes().remove(posGen);
		hijo.getGenes().add(posInsert, crom.getGen(posGen));
		
		return hijo;
	}

}
