package interfaz;

import common.AlgoritmoGenetico;
import common.cruce.CodificacionOrdinal;
import common.cruce.CruceCiclos;
import common.cruce.DiscretoUniforme;
import common.cruce.Monopunto;
import common.cruce.OX;
import common.cruce.PMX;
import common.cruce.RecombinacionRutas;
import common.cruce.Reproduccion;
import common.mutacion.Heuristica;
import common.mutacion.Insercion;
import common.mutacion.Intercambio;
import common.mutacion.Inversion;
import common.mutacion.Mutacion;
import common.mutacion.MutacionBinaria;
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
import interfaz.ConfigPanel.InnerOption;
import practicas.Problema;
import practicas.practica1.*;
import practicas.practica2.Practica2;

public class FactoriaOpciones {
	
	final public static Mutacion[] mutaciones = new Mutacion[] {
		new Heuristica(),
		new Insercion(),
		new Intercambio(),
		new Inversion()
	};
	
	final public static Reproduccion[] reproducciones = new Reproduccion[] {
		new CodificacionOrdinal(),
		new CruceCiclos(),
		new OX(),
		new PMX(),
		new RecombinacionRutas(),
		new Monopunto(),
		new DiscretoUniforme()
	};
	
	final public static Problema<?>[] funciones = new Problema<?>[] { 
		new Funcion1(), 
		new Funcion2(), 
		new Funcion3(), 
		new Funcion4(),
		new Funcion5(), 
		new Practica2()
	};
	final public static Seleccion[] selecciones = new Seleccion[] {
		new SeleccionRuleta(),
		new MuestreoEstocasticoUniversal(),
		new TorneoDeterministico(),
		new TorneoProbabilistico(),
		new Ranking(),
		new Truncamiento()
	};
	
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
				"P.Mutacion",
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
		.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
			  	"Truncamiento",							
			  	"Numero de genes a recombinar",				 
			  	"seleccion",							
			  	Truncamiento.class))						
		  		  .addInner(new DoubleOption<Truncamiento>(
		  		     "P. Cromosomas", "Porcentaje de cromosomas a seleccionar", "trunc", 0, 1))
		  		  .endInner()
		.addOption(new ChoiceOption<>(
				"Funcion", 
				"Funcion a optimizar",
				"funcion",
				funciones))
		.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
			  	"Funcion 4",							
			  	"Numero de genes a recombinar",				 
			  	"funcion",							
			  	Funcion4.class))						
		  		  .addInner(new IntegerOption<Funcion4>(
		  		     "Num. Variables", "Numero de variables de la funcion", "n", 1, Integer.MAX_VALUE))
		  		  .endInner()
		.addOption(new ChoiceOption<>(
				"Mutacion", 
				"Metodo de mutacion",
				"mutacion",
				mutaciones))
		.beginInner(new InnerOption<AlgoritmoGenetico,Mutacion>(  
			  	"Heuristica",							
			  	"Numero de genes a recombinar",				 
			  	"mutacion",							
			  	Heuristica.class))						
		  		  .addInner(new IntegerOption<Heuristica>(
		  		     "Numero Genes", "Numero de genes a recombinar", "NGenes", 0, Integer.MAX_VALUE))
		  		  .endInner()
		.addOption(new ChoiceOption<>(
				"Reproduccion", 
				"Metodo de reproduccion",
				"reproduccion",
				reproducciones))
		.beginInner(new InnerOption<AlgoritmoGenetico,Reproduccion>(  
			  	"Discreto uniforme",							
			  	"",				 
			  	"reproduccion",							
			  	DiscretoUniforme.class))						
		  		  .addInner(new DoubleOption<DiscretoUniforme>(
		  		     "p", "P", "p", 0, 1))
		  		  .endInner()
		.endOptions();

		return panel;
	}

}
