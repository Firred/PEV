package common.mutacion.practica2;

import common.Cromosoma;
import common.mutacion.Mutacion;

public class Insercion extends Mutacion {
	
	@Override
	protected
	Cromosoma mutacion(Cromosoma crom) {
		int posGen = rand.nextInt(crom.getNumGenes());
		int posInsert = rand.nextInt(crom.getNumGenes());
		Cromosoma hijo = new Cromosoma(crom);
		
		hijo.getGenes().remove(posGen);
		hijo.getGenes().add(posInsert, crom.getGen(posGen));
		
		return hijo;
	}

	@Override
	public String toString() {
		return "Insercion";
	}
}
