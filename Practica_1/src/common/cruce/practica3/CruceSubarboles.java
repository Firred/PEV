package common.cruce.practica3;

import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import practica3.CromosomaArbol;
import practica3.GenArbol;

public class CruceSubarboles extends Reproduccion {

	protected ParCromosoma cruce(ParCromosoma par) {
		CromosomaArbol p1 = (CromosomaArbol)par.getC1(), p2 = (CromosomaArbol)par.getC2();
		GenArbol subArb1, subArb2, aux;
		CromosomaArbol h1 = new CromosomaArbol(p1), h2 = new CromosomaArbol(p2);
		int ale1 = this.rand.nextInt(p1.getNumGenes()-1) + 1, ale2 = this.rand.nextInt(p2.getNumGenes()-1) + 1;;
		
		subArb1 = ((GenArbol)p1.getGen()).buscarNodo(ale1);
		subArb2 = ((GenArbol)p2.getGen()).buscarNodo(ale2);
		
		aux = subArb1.getPadre();
		subArb2.getPadre().insertarNodo(subArb2, subArb1);
		aux.insertarNodo(subArb1, subArb2);
		
		return new ParCromosoma(h1, h2);
	}
}
