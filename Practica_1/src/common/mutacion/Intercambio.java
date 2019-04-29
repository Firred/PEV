package common.mutacion;

import common.Cromosoma;

public class Intercambio extends Mutacion {

	@Override
	Cromosoma mutacion (Cromosoma crom) {
		if(crom.getNumGenes() == 1)
			return crom;
		
		int p1 = rand.nextInt(crom.getNumGenes()), p2;
		Cromosoma hijo = new Cromosoma(crom);
		
		while ((p2 = rand.nextInt(crom.getNumGenes())) == p1)
		
		hijo.getGenes().set(p1, crom.getGen(p2));
		hijo.getGenes().set(p2, crom.getGen(p1));
		
		return hijo;
	}

	@Override
	public String toString() {
		return "Intercambio";
	}
}
