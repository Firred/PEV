package common.mutacion;

import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.cruce.ParCromosoma;

public abstract class Mutacion {
	protected Random rand = new Random();
	
	public void execute(Poblacion pobl, int prob) {
		Cromosoma crom;
		
		if(prob == 0)
			return;
		
		for (int i = 0; i < pobl.getTpobl(); i++) {
			if (prob >= rand.nextDouble()) {
					crom = mutacion(pobl.getIndividuos(i));
					
					pobl.setIndividuos(crom, i);
			}
		}		
	}
	
	abstract <T extends Cromosoma> T mutacion(T crom);
}
