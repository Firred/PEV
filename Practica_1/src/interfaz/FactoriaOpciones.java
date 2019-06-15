package interfaz;

import common.AlgoritmoGenetico;
import common.bloating.Bloating;
import common.bloating.BloatingPenalizacion;
import common.bloating.BloatingTarpeian;
import common.cruce.*;
import common.cruce.practica1.Aritmetico;
import common.cruce.practica1.DiscretoUniforme;
import common.cruce.practica1.Monopunto;
import common.cruce.practica1.ReproduccionBinariaMonopunto;
import common.cruce.practica1.ReproduccionBinariaMultipunto;
import common.cruce.practica1.ReproduccionBinariaUniforme;
import common.cruce.practica1.SBX;
import common.cruce.practica1.BLX;
import common.cruce.practica2.CodificacionOrdinal;
import common.cruce.practica2.CruceCiclos;
import common.cruce.practica2.OX;
import common.cruce.practica2.PMX;
import common.cruce.practica2.RecombinacionRutas;
import common.mutacion.*;
import common.mutacion.practica1.NoUniforme;
import common.mutacion.practica2.Heuristica;
import common.mutacion.practica2.Insercion;
import common.mutacion.practica2.Intercambio;
import common.mutacion.practica2.Inversion;
import common.mutacion.practica3.FuncionalSimple;
import common.mutacion.practica3.MutacionInicializacion;
import common.mutacion.practica3.TerminalSimple;
import common.seleccion.*;
import common.seleccion.estocastico.*;
import common.seleccion.torneo.*;
import interfaz.ConfigPanel.*;
import practicas.Problema;
import practicas.ProblemaArbol;
import practicas.practica1.*;
import practicas.practica2.Practica2;
import practicas.practica3.Practica3;

public class FactoriaOpciones {
	
	final public static Mutacion[] mutaciones = new Mutacion[] {
		new Heuristica(),
		new Insercion(),
		new Intercambio(),
		new Inversion()
	};
	
	final public static Mutacion[] mut3 = new Mutacion[] {
			new TerminalSimple(),
			new FuncionalSimple(),
			new MutacionInicializacion()
		};
	
	final public static Reproduccion[] repBinaria = new Reproduccion[] {
			new ReproduccionBinariaMonopunto(),
			new ReproduccionBinariaUniforme(),
			new ReproduccionBinariaMultipunto()
		};
	
	final public static Reproduccion[] repP1 = new Reproduccion[] {
		new Monopunto(),
		new DiscretoUniforme(),
		new Aritmetico(),
		new SBX(),
		new BLX()
	};
	
	final public static Reproduccion[] repP2 = new Reproduccion[] {
		new CodificacionOrdinal(),
		new CruceCiclos(),
		new OX(),
		new PMX(),
		new RecombinacionRutas()
	};
	
	final public static Boolean[] booleano = new Boolean[] {
		true,
		false
	};
	
	final public static Problema<?>[] funciones = new Problema<?>[] { 
		new Funcion1(), 
		new Funcion2(), 
		new Funcion3(), 
		new Funcion4(),
		new Funcion5(), 
		new Practica2(),
		new Practica3()
	};
	
	final public static Seleccion[] selecciones = new Seleccion[] {
		new SeleccionRuleta(),
		new MuestreoEstocasticoUniversal(),
		new TorneoDeterministico(),
		new TorneoProbabilistico(),
		new Ranking(),
		new Truncamiento()
	};
	
	final public static Bloating[] bloatings = new Bloating[] {
			new BloatingTarpeian(),
			new BloatingPenalizacion()
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
  				"Contractividad",
				"Metodo de reproduccion",
				"contractividad",
				booleano
				))
		.addOption(new ChoiceOption<>(
  				"Terminacion",
				"Usar criterio de terminacion",
				"critTerminacion",
				booleano
				))
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
					  	"Funcion Binaria",							
					  	"",				 
					  	"funcion",							
					  	Funcion.class))						
				  		  .addInner(new ChoiceOption<Funcion>(
					  				"Reproduccion",
									"Metodo de reproduccion",
									"reproduccion",
									repBinaria
									))
				  		  .endInner()
				
				.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
					  	"Funcion 4",							
					  	"",				 
					  	"funcion",							
					  	Funcion4.class))						
				  		  .addInner(new IntegerOption<Funcion4>(
				  		     "Num. Variables", "Numero de variables de la funcion", "n", 1, Integer.MAX_VALUE))
				  		  .endInner()
				  		  
		  		.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
					  	"Funcion 5",							
					  	"",				 
					  	"funcion",							
					  	Funcion5.class))
				  		.addInner(new ChoiceOption<Funcion5>(
				  				"Reproduccion",
								"Metodo de reproduccion",
								"reproduccion",
								repP1
								))
				  		
				  		.beginInner(new InnerOption<AlgoritmoGenetico,Reproduccion>(  
							  	"Discreto uniforme",							
							  	"",				 
							  	"reproduccion",							
							  	DiscretoUniforme.class))						
						  		  .addInner(new DoubleOption<DiscretoUniforme>(
						  		     "p", "P", "p", 0, 1))
						  		  .endInner()
				  		.beginInner(new InnerOption<AlgoritmoGenetico,Reproduccion>(  
							  	"Aritmetico",							
							  	"",				 
							  	"reproduccion",							
							  	Aritmetico.class))						
						  		  .addInner(new DoubleOption<Aritmetico>(
						  		     "Alfa", "Alfa", "alfa", 0, 1))
						  		  .endInner()
				  		.beginInner(new InnerOption<AlgoritmoGenetico,Reproduccion>(  
							  	"SLX",							
							  	"",				 
							  	"reproduccion",							
							  	BLX.class))						
						  		  .addInner(new DoubleOption<BLX>(
						  		     "Alfa", "Alfa", "alfa", 0, 1))
						  		  .endInner()
				  		.beginInner(new InnerOption<AlgoritmoGenetico,Reproduccion>(  
							  	"SBX",							
							  	"",				 
							  	"reproduccion",							
							  	SBX.class))						
						  		  .addInner(new DoubleOption<SBX>(
						  		     "Beta", "Beta", "beta", 0, Double.MAX_VALUE))
						  		  .endInner()
				  		
				  		  
				  		  .addInner(new IntegerOption<Funcion5>(
				  		     "Num. Variables", "Numero de variables de la funcion", "n", 1, Integer.MAX_VALUE))
				  		  .endInner()
				  		  
		  		.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
					  	"Practica 2",							
					  	"",				 
					  	"funcion",							
					  	Practica2.class))
		  						  		
				  		.addInner(new ChoiceOption<Practica2>(
				  				"Reproduccion",
								"Metodo de reproduccion",
								"reproduccion",
								repP2
								))
				  		
				  		.addInner(new ChoiceOption<Practica2>(
				  				"Mutacion",
								"Metodo de mutacion",
								"mutacion",
								mutaciones
								))
				  		
						  		.beginInner(new InnerOption<AlgoritmoGenetico,Mutacion>(  
									  	"No Uniforme",
									  	"",				 
									  	"mutacion",					
									  	NoUniforme.class))						
								  		  .addInner(new DoubleOption<NoUniforme>(
								  		     "Sigma", "Desviacion estandar", "sigma", Double.MIN_VALUE, Double.MAX_VALUE))
								  		  .endInner()
								.beginInner(new InnerOption<AlgoritmoGenetico,Mutacion>(  
									  	"Heuristica",							
									  	"Numero de genes a recombinar",				 
									  	"mutacion",							
									  	Heuristica.class))						
								  		  .addInner(new IntegerOption<Heuristica>(
								  		     "Numero Genes", "Numero de genes a recombinar", "NGenes", 0, Integer.MAX_VALUE))
								  		  .endInner()		
				.endInner()
				
		  		.beginInner(new InnerOption<AlgoritmoGenetico,Seleccion>(  
					  	"Practica 3",							
					  	"",				 
					  	"funcion",							
					  	ProblemaArbol.class))
		  		
				  		.addInner(new ChoiceOption<Practica3>(
				  				"Mutacion",
								"Metodo de mutacion",
								"mutacion",
								mut3
								))
				  		.addInner(new ChoiceOption<Practica3>(
				  				"Bloating",
								"Metodo de controlar el bloating",
								"bloating",
								bloatings
								))
		  		
	  					.addInner(new IntegerOption<ProblemaArbol>(
	  							"Maximo", "Profundidad maxima de la solucion", "NMax", 1, 60))
				  		.endInner()
		  		
		.endOptions();

		return panel;
	}	
}