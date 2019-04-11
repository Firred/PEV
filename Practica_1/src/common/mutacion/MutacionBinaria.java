package common.mutacion;

import java.util.ArrayList;
import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.genes.Gen;
import common.genes.GenBi;

public class MutacionBinaria extends Mutacion {
	
/*	public void mutacionBasica(Cromosoma indiviudo, int pos) {
		for(int j=0;j<indiviudo.getGen(pos).getTam();j++) {
			if(prob > rand.nextDouble()) {
				indiviudo.setAleloBi_1g(!indiviudo.getGenes_Bool(j, pos), j , pos);
				indiviudo.getGen(pos).calcularCaracteristica();
			}
		}
	}*/

	@Override
	public void execute(Poblacion pobl, int prob) {
		boolean[] alelo;
		for (Cromosoma c : pobl.getIndividuos()) {
			for(Gen g : c.getGenes()) {
				alelo = ((GenBi)g).getAlelo();
				
				for(int i = 0; i < alelo.length; i++) {
					if(prob > rand.nextInt(100)) {
						alelo[i] = true^alelo[i];
					}
				}		
			}
		}
	}

	@Override
	Cromosoma mutacion(Cromosoma crom) {return null;}	
	
	/*public void mutacion_real_NOuniforme(Cromosoma individuo, int pos) {
		double checkpoint = 0;
		double next_mut = 0;
		if(Tmutacion > Rand.nextDouble()){
			next_mut = Rand.nextDouble() * (Rand.nextBoolean() ? 1 : -1);
			// Permite realizar una mutacion desde -1 hasta 1
			checkpoint = individuo.getGen(pos).getAlelo()+next_mut; 			
			if(checkpoint > Function_main.MAX[pos] || checkpoint < Function_main.MIN[pos] ) {
				mut_failed++;
				System.out.println("Mutacion no valida: " + mut_failed);
			} else {
				individuo.getGen(pos).setAlelo(checkpoint);
			}
		}
		
	}*/
	
}
