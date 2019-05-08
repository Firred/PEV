package common;

import common.cruce.CodificacionOrdinal;
import common.cruce.Reproduccion;
import common.cruce.ReproduccionBinaria;
import common.evaluacion.Evaluacion;
import common.mutacion.Mutacion;
import common.mutacion.MutacionBinaria;
import common.seleccion.FactoriaSeleccion;
import common.seleccion.Seleccion;
import common.seleccion.estocastico.SeleccionRuleta;
import interfaz.controlador.Controlador;
import practicas.Problema;
import practicas.practica1.Funcion;
import practicas.practica1.Funcion1;
import practicas.practica2.Practica2;

public class AlgoritmoGenetico {
	/**Tamaño de la población*/
	private int poblacion;
	private Poblacion poblPrincipal;
	/**Función a optimizar*/
	private Problema<?> funcion;	
	/**Mejor individuo*/
	private Cromosoma mejor;
	/**Método de selección*/
	private Seleccion seleccion;	
	/**Método de mutación*/
	private Mutacion mutacion;		
	/**Número de generaciones*/
	private int generaciones;
	/**Tamaño de la élite*/
	private int elite;	
	/**Prob. de mutación*/
	private int pMut;
	/**Precisión*/
	private double precision;
	/**Prob. cruce*/
	private int pCruce;
	/**Método de reproducion*/
	private Reproduccion reproduccion;
	
	/**
	 * Variable para pruebas.
	 * Poner a true para que se muestre en consola la lista de cromosomas en cada paso de la generación
	 */
	private boolean flag_print = false;
	
	public AlgoritmoGenetico() {
		this.poblacion = 100;
		this.generaciones = 100;
		this.elite = 2;
		this.pMut = 5;
		this.pCruce = 60;
		this.precision = 0.001;
		this.seleccion = new SeleccionRuleta();
		this.funcion = new Funcion1();
		this.mutacion = new MutacionBinaria();
		this.reproduccion = new ReproduccionBinaria();
	}
	
	public AlgoritmoGenetico(int tipo, int tpobl, int generaciones, int elite, String selec, String mut, int pMut) {
//		Function_Controller.setF_actual(0); // permite seleccionar la funcion pasandole un indice
//		this.funcion.Set_Function();
		this.poblPrincipal = new Poblacion(tipo, tpobl, 0, this.funcion, this.precision);
		this.generaciones = generaciones;
		this.mejor = poblPrincipal.getIndividuos(0);
		this.elite = (int)(tpobl*elite/100);
		this.seleccion = FactoriaSeleccion.getAlgoritmoDeSeleccion(selec);
		this.pMut = pMut;
		Object[] args = new Object[1];
		args[0] = pMut;
		//this.mutacion = FactoriaMutacion.getMutacion(mut, args);
		this.mutacion = new MutacionBinaria();
	}
	
	public void setSeleccion(Seleccion sel) {
		this.seleccion = sel;
	}
	
	public void setMutacion(Mutacion mut) {
		this.mutacion = mut;
	}
	
	public void setGeneraciones(int gen) {
		this.generaciones = gen;
	}
	
	public void setElite(int elite) {
		this.elite = (int)(poblacion*elite/100);
	}
	
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}
	
	public void setFuncion(Problema<?> funcion) {
		this.funcion = funcion;
		Evaluacion.setFuncion(funcion);
	}
	
	public void setPMut(int pMut) {
		this.pMut = pMut;
	}
	
	public void setPrecision(double prec) {
		this.precision = prec;
	}
	
	public void setCruce(int cruce) {
		this.pCruce = cruce;
	}
	
	public void setReproduccion(Reproduccion reproduccion) {
		this.reproduccion = reproduccion;
	}
	
	public Seleccion getSeleccion() {
		return this.seleccion;
	}
	
	public Mutacion getMutacion() {
		return this.mutacion;
	}
	
	public int getGeneraciones() {
		return this.generaciones;
	}
	
	public int getElite() {
		if (this.poblacion == 0)
			return 0;
		
		return (int)(100*elite/poblacion);
	}
	
	public int getPoblacion() {
		return this.poblacion;
	}
	
	public Problema<?> getFuncion() {
		return this.funcion;
	}
	
	public int getPMut() {
		return this.pMut;
	}
	
	public double getPrecision() {
		return this.precision;
	}
	
	public int getCruce() {
		return this.pCruce;
	}
	
	public Reproduccion getReproduccion() {
		return this.reproduccion;
	}

	@Override
	public String toString() {
		return "Ag";
	}
	
	public String mostrarPoblacion() {
		String rtn = "";
		
		for (Cromosoma crom : this.poblPrincipal.getIndividuos()) 
			rtn += (crom.toString() + "\n");
	
		return rtn;
	}
	
	public void evalua() {
		double suma_aptitud = 0;
		
		for(Cromosoma crom : poblPrincipal.getIndividuos()) {
			suma_aptitud += Evaluacion.evaluar(crom);
		}
		
		poblPrincipal.calcularMejorMedia();
		
		if(poblPrincipal.getMejor().compareTo(this.mejor) >= 1) {			
			this.mejor = poblPrincipal.getMejor();
		}
		
		for(int i = 0; i<poblPrincipal.getTpobl();i++) {
			poblPrincipal.getIndividuos(i).setPunt(poblPrincipal.getIndividuos(i).getApt()/suma_aptitud);
		}
		
		poblPrincipal.calcularMejorMedia();
		poblPrincipal.calcularPuntAcum();
	}
	
	public void selecciona_cruza() {
		poblPrincipal = seleccion.execute(poblPrincipal);
		if(flag_print == true) {
			System.out.println("\n POST-SELECCION: ----------------------- generacion:  -----------------------\n\n");
			System.out.println(mostrarPoblacion());		
		}
		
		reproduccion.ejecutar(poblPrincipal, this.pCruce);
	}
	
	public void muta() {
		mutacion.execute(poblPrincipal, this.pMut);
		
		
	/*	
		int j2 = poblPrincipal.getIndividuos(0).getNumGenes();
		for(int i = 0; i<poblPrincipal.getTpobl(); i++) {		
				for(int z=0; z < poblPrincipal.getIndividuos(i).getNumGenes() ; z++) {
					if(Main.tipo == 0) {
						mutacion.execute(poblPrincipal);	//Mutacion.mutacion.mutacionBasica(poblPrincipal.getIndividuos(i), z);
					}else {
					//Mutacion.mutacion.mutacion_real_NOuniforme(poblPrincipal.getIndividuos(i), z);
					}
				}
			}*/
	}
				
	
	public boolean terminado() {
		return this.generaciones <= poblPrincipal.getGeneracion();
	}
	
	/**
	 * Comprueba que la configuracion del algoritmo es correcta y realiza cambios si es necesario
	 */
	private void configCheck() {
		//Si la funcion es de la Practica 1 la mutacion pasa a ser binaria
		if(Funcion.class.isAssignableFrom(this.funcion.getClass())) {
			this.mutacion = new MutacionBinaria();
			this.reproduccion = new ReproduccionBinaria();
		}
		//Si es de la Practica 2 y utiliza el cruce de Codificacion Ordinal obtiene la lista de ciudades para el cruce
		else if(Practica2.class.isAssignableFrom(this.funcion.getClass())) {
			if(CodificacionOrdinal.class.isAssignableFrom(this.reproduccion.getClass()))
				((CodificacionOrdinal) this.reproduccion).setLista(((Practica2)this.funcion).getLista());
		}
	}
	
	public String exe(Controlador ctrl) {
		this.poblPrincipal = new Poblacion(0, this.poblacion, 0, this.funcion, this.precision);
		this.mejor = this.poblPrincipal.getIndividuos(0);
		
		configCheck();
		
		ctrl.start(this.generaciones, this.funcion.getNumGenes());		
		
		Cromosoma[] eliteP = new Cromosoma[0];
		
		if(flag_print == true) {
			System.out.println("\n PRIMERA GENERACION: ----------------------- generacion:  -----------------------\n\n");
			System.out.println(mostrarPoblacion());			
		}
		
		evalua();
		ctrl.update(this.poblPrincipal, this.mejor);
		if(flag_print == true) {
			System.out.println("\n POST-EVALUACION: ----------------------- generacion: 0 -----------------------\n\n");
			System.out.println(mostrarPoblacion());			
		}
		
		while(!terminado()) {
			
			if(this.elite > 0) {
				eliteP = poblPrincipal.separaMejores(this.elite);
				if(flag_print == true) {
					System.out.println("\n ELITE: ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
					for(Cromosoma c : eliteP)
						System.out.println(c.toString());			
				}
			}
			
			selecciona_cruza();
			
			if(flag_print == true) {
				System.out.println("\n POST-CRUCE: ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());			
			}
			
			muta();

			if(flag_print == true) {
				System.out.println("\n POST-MUTACION: ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());	
			}
			
			if(this.elite > 0)
				poblPrincipal.incluye(eliteP);
			
			evalua();
			if(flag_print == true) {
				System.out.println("\n POST-EVALUACION: ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());			
			}
			
			ctrl.update(this.poblPrincipal, this.mejor);
		}
		
		String texto = "";
		
		if(Practica2.class.isAssignableFrom(this.funcion.getClass())) {
			texto = ((Practica2)this.funcion).cromToString(this.mejor);
		}
		
		ctrl.finish(this.mejor, texto);
		
		System.out.println("El mejor es: " + mejor.toString());
		return "EL MEJOR ES:" + this.mejor.toString();
	}
}
