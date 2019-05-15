package common.cruce.practica3;

import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import practica3.CromosomaArbol;
import practica3.GenArbol;

public class CruceSubarboles extends Reproduccion {

	protected ParCromosoma cruce(ParCromosoma par) {
		CromosomaArbol p1 = (CromosomaArbol)par.getC1(), p2 = (CromosomaArbol)par.getC2();
		GenArbol subArb1, subArb2;
		CromosomaArbol h1 = new CromosomaArbol(p1), h2 = new CromosomaArbol(p2);
		
		
		
		return null;
	}

}
