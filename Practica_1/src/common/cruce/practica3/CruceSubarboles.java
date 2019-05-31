package common.cruce.practica3;

import common.Cromosoma;
import common.cruce.ParCromosoma;
import common.cruce.Reproduccion;
import practica3.GenArbol;

public class CruceSubarboles extends Reproduccion {

	protected ParCromosoma cruce(ParCromosoma par) {
		GenArbol p1 = (GenArbol) par.getC1().getGen(0), p2 = (GenArbol) par.getC2().getGen(0);
		GenArbol subArb1, subArb2;
		Cromosoma h1 = new Cromosoma(par.getC1()), h2 = new Cromosoma(par.getC2());
		int ale1 = this.rand.nextInt(p1.getNumNodos()), ale2 = this.rand.nextInt(p2.getNumNodos());

		subArb1 = p1.buscarNodo(ale1);
		subArb2 = p2.buscarNodo(ale2);

		if(subArb1.getPadre() == null) {
			h1.setGen(new GenArbol(subArb2, null), 0);
		}
		else {
			subArb1.getPadre().insertarNodo(subArb1, subArb2);
		}

		if(subArb2.getPadre() == null) {
			h2.setGen(new GenArbol(subArb1, null), 0);
		}
		else {
			subArb2.getPadre().insertarNodo(subArb2, subArb1);
		}

		return new ParCromosoma(h1, h2);
	}
}
