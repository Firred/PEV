package common.mutacion.practica2;

import common.Cromosoma;
import common.mutacion.Mutacion;

public class Intercambio extends Mutacion {

	@Override
	protected
	Cromosoma mutacion (Cromosoma crom) {
		if(crom.getNumGenes() == 1)
			return crom;
		
		int p1 = rand.nextInt(crom.getNumGenes()), p2;
		Cromosoma hijo = new Cromosoma(crom);
		
		do {
			p2 = rand.nextInt(crom.getNumGenes());
		} while (p2 == p1);
		
		hijo.getGenes().set(p1, crom.getGen(p2));
		hijo.getGenes().set(p2, crom.getGen(p1));
		
		return hijo;
	}

	@Override
	public String toString() {
		return "Intercambio";
	}
}
