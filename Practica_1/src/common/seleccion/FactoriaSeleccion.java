package common.seleccion;

import common.seleccion.estocastico.*;
import common.seleccion.torneo.*;

public class FactoriaSeleccion {	
	public static Seleccion getAlgoritmoDeSeleccion(String algoritmo) {
		switch(algoritmo) {
			case "Ruleta":
				return new SeleccionRuleta();
			case "Torneo_Deterministico":
				return new TorneoDeterministico();
			case "Torneo_Probabilistico":
				return new TorneoProbabilistico();
			case "Truncamiento":
				return new Truncamiento();
			case "Estocástico":
				return new MuestreoEstocasticoUniversal();		
			case "Ranking":
				return new Ranking();
			default:
				return new SeleccionRuleta();
		}
	}	
}
