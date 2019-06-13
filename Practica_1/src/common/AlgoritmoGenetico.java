package common;

import common.cruce.Reproduccion;
import common.cruce.practica1.ReproduccionBinariaMonopunto;
import common.cruce.practica2.CodificacionOrdinal;
import common.evaluacion.Evaluacion;
import common.mutacion.Mutacion;
import common.mutacion.practica1.MutacionBinaria;
import common.seleccion.Seleccion;
import common.seleccion.estocastico.SeleccionRuleta;
import interfaz.controlador.Controlador;
import practicas.Problema;
import practicas.ProblemaArbol;
import practicas.ProblemaNoBinario;
import practicas.practica1.Funcion;
import practicas.practica1.Funcion1;
import practicas.practica1.Funcion5;
import practicas.practica2.Practica2;
import practicas.practica3.Practica3;

public class AlgoritmoGenetico {
	/**Tamano de la poblacion*/
	private int poblacion;
	private Poblacion poblPrincipal;
	/**Funcion a optimizar*/
	private Problema<?> funcion;	
	/**Mejor individuo*/
	private Cromosoma mejor;
	/**Metodo de seleccion*/
	private Seleccion seleccion;	
	/**Metodo de mutacion*/
	private Mutacion mutacion;		
	/**Numero de generaciones*/
	private int generaciones;
	/**Tamano de la elite*/
	private int elite;	
	/**Prob. de mutacion*/
	private int pMut;
	/**Precision*/
	private double precision;
	/**Prob. cruce*/
	private int pCruce;
	/**Metodo de reproducion*/
	private Reproduccion reproduccion;
	/**Contractividad*/
	private boolean contractividad;
	/**Criterio de terminacion (solo de contenido)*/
	private boolean critTerminacion;
	
	/**
	 * Variable para pruebas.
	 * Poner a true para que se muestre en consola la lista de cromosomas en cada paso de la generacion
	 */
	private boolean flag_print = false;
	
	/**Numero de generaciones sin obtener un individuo mejor*/
	private int genSinMejora;
	/**Mejor media obtenida, usada para el criterio de terminacion*/
	private double mejorMedia;
	
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
		this.reproduccion = new ReproduccionBinariaMonopunto();
		this.contractividad = false;
		this.genSinMejora = 0;
	}
	
	public AlgoritmoGenetico(int tipo, int tpobl, int generaciones, int elite, String selec, String mut, int pMut) {
//		Function_Controller.setF_actual(0); // permite seleccionar la funcion pasandole un indice
//		this.funcion.Set_Function();
		this.poblPrincipal = new Poblacion(tpobl, 0, this.funcion, this.precision);
		this.generaciones = generaciones;
		this.mejor = poblPrincipal.getIndividuos(0);
		this.elite = (int)(tpobl*elite/100);
		this.pMut = pMut;
		Object[] args = new Object[1];
		args[0] = pMut;
		//this.mutacion = FactoriaMutacion.getMutacion(mut, args);
		this.mutacion = new MutacionBinaria();
		this.genSinMejora = 0;
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
	
	public void setContractividad(boolean contractividad) {
		this.contractividad = contractividad;
	}

	public void setCritTerminacion(boolean critTerminacion) {
		this.critTerminacion = critTerminacion;
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

	public boolean getContractividad() {
		return this.contractividad;
	}
	
	public boolean getCritTerminacion() {
		return critTerminacion;
	}
	
	public Cromosoma getMejor() {
		return this.mejor;
	}
	
	@Override
	public String toString() {
		return "Ag";
	}
	
	private String mostrarPoblacion() {
		String rtn = "";
		
		for (Cromosoma crom : this.poblPrincipal.getIndividuos()) 
			rtn += (crom.toString() + "\n");
	
		return rtn;
	}
	
	private void evalua() {
		double suma_aptitud = 0;
		
		this.funcion.calcularPuntuacion(poblPrincipal);
		
		if(poblPrincipal.getMejor().compararX(this.mejor) >= 1) {
			this.mejor = new Cromosoma(poblPrincipal.getMejor());
			this.genSinMejora = 0;
			this.mejorMedia = poblPrincipal.getMedia();
		}
		else {
			if(this.funcion.MINIMIZAR) {
				if(poblPrincipal.getMedia() > this.mejorMedia)
					this.genSinMejora++;

				else
					this.mejorMedia = poblPrincipal.getMedia();
			}
			else {
				if(poblPrincipal.getMedia() < this.mejorMedia)
					this.genSinMejora++;
	
				else
					this.mejorMedia = poblPrincipal.getMedia();
			}
		}
	}
	
	private void selecciona() {
		poblPrincipal = seleccion.execute(poblPrincipal);
	}
	
	private void cruza() {		
		reproduccion.ejecutar(poblPrincipal, this.pCruce);
	}
	
	private void muta() {
		mutacion.execute(poblPrincipal, this.pMut);
	}
				
	private boolean terminado() {
		return (this.generaciones <= poblPrincipal.getGeneracion()) || (critTerminacion && (this.generaciones/5) < this.genSinMejora);
	}
	
	/**
	 * Comprueba que la configuracion del algoritmo es correcta y realiza cambios si es necesario
	 */
	private void configCheck() {
		//Si la funcion es de la Practica 1 la mutacion pasa a ser binaria
		if(Funcion.class.isAssignableFrom(this.funcion.getClass()) && !Funcion5.class.isAssignableFrom(this.funcion.getClass())) {
			this.mutacion = new MutacionBinaria();
		}
		//Si es de la Practica 2 y utiliza el cruce de Codificacion Ordinal obtiene la lista de ciudades para el cruce
		else if(ProblemaNoBinario.class.isAssignableFrom(this.funcion.getClass())) {
			this.mutacion = ((ProblemaNoBinario)this.funcion).getMutacion();
			
			if(CodificacionOrdinal.class.isAssignableFrom(this.reproduccion.getClass()))
				((CodificacionOrdinal) this.reproduccion).setLista(((Practica2)this.funcion).getLista());
		}
		
		this.reproduccion = this.funcion.getReproduccion();
	}
	
	public String exe(Controlador ctrl) {
		double aptitud;
		this.poblPrincipal = new Poblacion(this.poblacion, 0, this.funcion, this.precision);
		this.mejor = this.poblPrincipal.getIndividuos(0);

		this.poblPrincipal.setTPobl(this.poblPrincipal.getTPoblacion()-this.elite);
		
		configCheck();
		
		ctrl.start(this.generaciones, this.funcion.getNumGenes());		
		
		Cromosoma[] eliteP = new Cromosoma[0];
		
		mensajeDebug("PRIMERA GENERACION");
		
		evalua();
		ctrl.update(this.poblPrincipal, this.mejor);		
		mensajeDebug("POST-EVALUACION");
		
		int intentos = 0;
		aptitud = poblPrincipal.getAptMedia();	
		
		//Inicia bucle		
		while(!terminado()) {
			if(this.elite > 0) {
				if(ProblemaArbol.class.isAssignableFrom(this.funcion.getClass()))
					((ProblemaArbol)this.funcion).calcularPuntuacionNormal(poblPrincipal);
				
				eliteP = poblPrincipal.separaMejores(elite);		
				
				if(flag_print == true) {
					System.out.println("\n ELITE: ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
					for(Cromosoma c : eliteP)
						System.out.println(c.toString());			
				}
			}
			
			selecciona();
			mensajeDebug("POST-SELECCION");
			
			cruza();
			mensajeDebug("POST-CRUCE");		

			muta();
			mensajeDebug("POST-MUTACION");
			
			if(this.elite > 0)
				poblPrincipal.incluye(eliteP);
			
			evalua();
			mensajeDebug("POST-EVALUACION");
			
			if(this.contractividad) {
				if(aptitud >= poblPrincipal.getAptMedia() && intentos < 1000) {
					poblPrincipal.setGeneracion(poblPrincipal.getGeneracion()-1);
					intentos++;
				}
				else {
					ctrl.update(this.poblPrincipal, this.mejor);
					aptitud = poblPrincipal.getAptMedia();
					intentos = 0;
				}
			}
			else {
				ctrl.update(this.poblPrincipal, this.mejor);
			}
		}
		
		String texto = "";
		
		if(Practica2.class.isAssignableFrom(this.funcion.getClass())) {
			texto = ((Practica2)this.funcion).cromToString(this.mejor);
		}
		else if (Practica3.class.isAssignableFrom(this.funcion.getClass())) {
			texto = ((Practica3)this.funcion).cromToString(this.mejor);
		}
		
		ctrl.finish(this.mejor, texto);
		
		if(flag_print == true) {
			System.out.println("El mejor es: " + mejor.toString());
			texto = "EL MEJOR ES:" + this.mejor.toString();
		}
		else
			texto = "";
		
		this.funcion.evalua(mejor);
		return texto;
	}
	
	private void mensajeDebug(String mensaje) {
		if(flag_print) {
			System.out.println("\n " + mensaje + ": ----------------------- generacion: " + poblPrincipal.getGeneracion() + " -----------------------\n\n");
			System.out.println(mostrarPoblacion());	
		}
	}
}
