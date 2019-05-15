package common.cruce.practica1;

import java.util.Random;

import common.Cromosoma;
import common.Poblacion;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import common.genes.Gen;
import common.genes.GenBi;

public class ReproduccionBinaria extends Reproduccion {
	//private Par_Cromosomas_C P_1_2 = new Par_Cromosomas_C();

	/** Dandole una poblacion los empareja con la % de cruce y los junta en un punto
	 * Reemplazando padres por hijos
	 * @param pobl
	 * @return
	 */
	/*public Poblacion Seleccion_Cruce_Padres_1p(Poblacion pobl) {
		Poblacion newPobl = new Poblacion();
		Par_Cromosomas_C P_1_2 = new Par_Cromosomas_C();
		//int np1 = 0;
		//int np2 = 0;
		this.flag = false;
		Par_Cromosomas_C H_1_2 = new Par_Cromosomas_C();
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			if(prob_cruce >= rand.nextDouble()) {
				if(this.flag == false) {
					P_1_2.setC1(new Cromosoma(pobl.getIndividuos(i)));
					//np1 = i;
					this.flag = true;
					H_1_2.setC1(new Cromosoma(P_1_2.getC1()));
				}else{
					P_1_2.setC2(new Cromosoma(pobl.getIndividuos(i)));
					//np2 = i;
					this.flag = false;
					H_1_2.setC2(new Cromosoma(P_1_2.getC2()));
					for(int j = 0; j < pobl.getIndividuos(i).getNumGenes(); j++) {
						H_1_2 = Cruce_I.cruce.cruce_1p(P_1_2, H_1_2, j);
					}

					newPobl.addIndividuo(H_1_2.getC1());
					newPobl.addIndividuo(H_1_2.getC2());
				}				
			}
			else {
				newPobl.addIndividuo(pobl.getIndividuos(i));
			}
		}
		return 	pobl;
	}*/

/*	@Override
	public void ejecutar(Poblacion pobl, int probCruce) {
		/*Poblacion newPobl = new Poblacion();
		Par_Cromosomas_C P_1_2 = new Par_Cromosomas_C();
		//int np1 = 0;
		//int np2 = 0;
		boolean seleccionado = false;
		Par_Cromosomas_C H_1_2 = new Par_Cromosomas_C();
		
		for(int i = 0; i < pobl.getTpobl(); i++) {
			if(probCruce >= rand.nextDouble()) {
				if(seleccionado == false) {
					P_1_2.setC1(new Cromosoma(pobl.getIndividuos(i)));
					//np1 = i;
					seleccionado = true;
					H_1_2.setC1(new Cromosoma(P_1_2.getC1()));
				}else{
					P_1_2.setC2(new Cromosoma(pobl.getIndividuos(i)));
					//np2 = i;
					seleccionado = false;
					H_1_2.setC2(new Cromosoma(P_1_2.getC2()));
					for(int j = 0; j < pobl.getIndividuos(i).getNumGenes(); j++) {
						H_1_2 = Cruce_I.cruce.cruce_1p(P_1_2, H_1_2, j);
					}

					newPobl.addIndividuo(H_1_2.getC1());
					newPobl.addIndividuo(H_1_2.getC2());
				}				
			}
			else {
				newPobl.addIndividuo(pobl.getIndividuos(i));
			}
		}*/
		
		//return newPobl;
		
/*		int h1 = 0;
		boolean seleccionado = false;
		ParCromosoma par, hijos;
		
		if (probCruce == 0)
			return;
		
		for (int i = 0; i < pobl.getTpobl(); i++) {
			if (probCruce >= rand.nextDouble()) {
				if(!seleccionado) {
					h1 = i;
					seleccionado = true;
				}
				else {
					par = new ParCromosoma(pobl.getIndividuos(h1), pobl.getIndividuos(i));
					
					hijos = cruce(par);
					
					pobl.setIndividuos(hijos.getC1(), h1);
					pobl.setIndividuos(hijos.getC2(), i);
				}
			}
			
		}
	}*/

	@Override
	protected 
	ParCromosoma cruce(ParCromosoma par) {
		Cromosoma h1 =  new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int n = par.getC1().getGenes_long();
		n = rand.nextInt(Math.abs(n));
		int i = 0;
		
		while (n > 0) {
			if (n > par.getC1().getGen(i).getTam()) {
				GenBi g1 = (GenBi)h1.getGen(i), g2 = (GenBi)h2.getGen(i);
				
				for (int j= 0; j < par.getC1().getGen(i).getTam(); j++) {
					g1.setAlelo(((GenBi)par.getC2().getGen(i)).getGenes_Bool(j), j);
					g2.setAlelo(((GenBi)par.getC1().getGen(i)).getGenes_Bool(j), j);
				}					
					
				n = 0;
			}
			else {
				h1.getGenes().set(i, new GenBi((GenBi)par.getC2().getGen(i)));
				h2.getGenes().set(i, new GenBi((GenBi)par.getC1().getGen(i)));
				
				n -= par.getC1().getGen(i).getTam();
			}		
			
			i++;
		}
		
		return new ParCromosoma(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Reproduccion binaria";
	}
}
