package interfaz;

import common.seleccion.Ranking;
import common.seleccion.Seleccion;
import common.seleccion.Truncamiento;
import common.seleccion.estocastico.MuestreoEstocasticoUniversal;
import common.seleccion.estocastico.SeleccionRuleta;
import common.seleccion.torneo.TorneoDeterministico;
import common.seleccion.torneo.TorneoProbabilistico;

import interfaz.ConfigPanel.ChoiceOption;
import interfaz.ConfigPanel.DoubleOption;
import interfaz.ConfigPanel.IntegerOption;

import practicas.practica1.*;

public class FactoriaOpciones {
	
	final public static Funcion[] funciones = new Funcion[] { 
			new Funcion1(), 
			new Funcion2(), 
			new Funcion3(), 
			new Funcion4(),
			//new Funcion5() 
	};
	final public static Seleccion[] selecciones = new Seleccion[] {
			new SeleccionRuleta(),
			new MuestreoEstocasticoUniversal(),
			new TorneoDeterministico(),
			new TorneoProbabilistico(),
			new Ranking(),
			new Truncamiento()
	};
	
	public FactoriaOpciones() {

	}
	
	public static ConfigPanel<AlgoritmoGenetico> getConfigPanel() {
		ConfigPanel<AlgoritmoGenetico> panel = new ConfigPanel<AlgoritmoGenetico>();
		panel.addOption(new IntegerOption<AlgoritmoGenetico>(
				"Poblacion", 
				"Tamano de la poblacion",
				"poblacion",
				0, 
				Integer.MAX_VALUE))
		.addOption(new IntegerOption<>(
				"Generaciones",
				"Numero maximo de generaciones",
				"generaciones",
				0,
				Integer.MAX_VALUE))
		.addOption(new IntegerOption<>(
				"Cruces",
				"Probabilidad de cruce",
				"cruce",
				0,
				100))
		.addOption(new IntegerOption<>(
				"Mutaciones",
				"Probabilidad de mutacion",
				"PMut", 
				0, 
				100))
		.addOption(new DoubleOption<>(
				"Precision",
				"Precision del algoritmo",
				"precision",
				0,
				Double.MAX_VALUE))
		.addOption(new IntegerOption<>(
				"Elitismo",
				"Porcentaje de elite",
				"elite",
				0, 
				100))
		.addOption(new ChoiceOption<>(
				"Seleccion",
				"Metodo de seleccion",
				"seleccion",
				selecciones
				))
		.addOption(new ChoiceOption<>(
				"Funcion", 
				"Funcion a optimizar",
				"funcion",
				funciones))
		.endOptions();

		return panel;
	}

}
