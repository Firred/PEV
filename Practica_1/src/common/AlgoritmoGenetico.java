package common;

import java.io.IOException;

import common.cruce.CodificacionOrdinal;
import common.cruce.CruceCiclos;
import common.cruce.Reproduccion;
import common.cruce.ReproduccionBinaria;
import common.evaluacion.Evaluacion;
import common.evaluacion.Function_Controller;
import common.evaluacion.Function_main;
import common.mutacion.FactoriaMutacion;
import common.mutacion.Heuristica;
import common.mutacion.Mutacion;
import common.mutacion.MutacionBinaria;
import common.seleccion.FactoriaSeleccion;
import common.seleccion.Seleccion;
import common.seleccion.estocastico.SeleccionRuleta;
import interfaz.controlador.ControladorImp;
import practicas.Problema;
import practicas.practica1.Funcion;
import practicas.practica1.Funcion1;
import practicas.practica2.Practica2;

public class AlgoritmoGenetico {
	/**Tama�o de la poblaci�n*/
	private int poblacion;
	private Poblacion poblPrincipal;
	/**Funci�n a optimizar*/
	private Problema<?> funcion;	
	/**Mejor individuo*/
	private Cromosoma mejor;
	/**M�todo de selecci�n*/
	private Seleccion seleccion;	
	/**M�todo de mutaci�n*/
	private Mutacion mutacion;		
	/**N�mero de generaciones*/
	private int generaciones;		
	private boolean flag_print = true;
	/**Tama�o de la �lite*/
	private int elite;	
	/**Prob. de mutaci�n*/
	private int pMut;
	/**Precisi�n*/
	private double precision;
	/**Prob. cruce*/
	private int pCruce;
	/**M�todo de reproducion*/
	private Reproduccion reproduccion;
	
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
		this.funcion = Function_Controller.getF_actual();
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
		/*String rtn = "";
		for(int i = 0; i<poblPrincipal.getTpobl();i++) {
			rtn += (poblPrincipal.getIndividuos(i).toString() + "\n");
		}
		return rtn;*/
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
		/*for(int i = 0; i<poblPrincipal.getTpobl();i++) {
			poblPrincipal.getIndividuos(i).setApt(funcion.Evalua(poblPrincipal.getIndividuos(i)));
			
			if(poblPrincipal.getIndividuos(i).compareTo(this.mejor) > 1) {
				this.mejor = new Cromosoma(poblPrincipal.getIndividuos(i));
			}*/
			
			/*if(!Function_main.MAX_MIN) {
				if(poblPrincipal.getIndividuos(i).getApt() > this.mejor.getApt()) {
					this.mejor = new Cromosoma(poblPrincipal.getIndividuos(i));
				}
			} else {
				if(poblPrincipal.getIndividuos(i).getApt() < this.mejor.getApt()) {
					this.mejor = new Cromosoma(poblPrincipal.getIndividuos(i));
				}
			}*/
		//	suma_aptitud += poblPrincipal.getIndividuos(i).getApt();
	//	}
		
		for(Cromosoma crom : poblPrincipal.getIndividuos()) {
			/*if(crom.compareTo(this.mejor) >= 1) {
				this.mejor = new Cromosoma(crom);
			}*/

			suma_aptitud += Evaluacion.evaluar(crom);
		}
		
		poblPrincipal.calcularMejorMedia();
		
		if(poblPrincipal.getMejor().compareTo(this.mejor) >= 1) {
			
			this.mejor = poblPrincipal.getMejor();
			System.out.println("Mejor que el otro: " + this.mejor);
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
		return this.generaciones < poblPrincipal.getGeneracion();
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
		if(Practica2.class.isAssignableFrom(this.funcion.getClass())) {
			if(CodificacionOrdinal.class.isAssignableFrom(this.reproduccion.getClass()))
				((CodificacionOrdinal) this.reproduccion).setLista(((Practica2)this.funcion).getLista());
		}
	}
	
	public String exe(ControladorImp ctrl) {
		this.poblPrincipal = new Poblacion(0, this.poblacion, 0, this.funcion, this.precision);
		this.mejor = this.poblPrincipal.getIndividuos(0);
		
		configCheck();
		
		ctrl.start(this.generaciones, this.funcion.genes);		
		
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
		
		for(int i = 0 ; i<this.generaciones ; i++) {
			if(this.elite > 0) {
				eliteP = poblPrincipal.separaMejores(this.elite);
				if(flag_print == true) {
					System.out.println("\n ELITE: ----------------------- generacion: " + i + " -----------------------\n\n");
					for(Cromosoma c : eliteP)
						System.out.println(c.toString());			
				}
			}
			selecciona_cruza();
			if(flag_print == true) {
				System.out.println("\n POST-CRUCE: ----------------------- generacion: " + i + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());			
			}
			muta();
			if(flag_print == true) {
				System.out.println("\n POST-MUTACION: ----------------------- generacion: " + i + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());	
			}
			
			if(this.elite > 0)
				poblPrincipal.incluye(eliteP);
			
			evalua();
			if(flag_print == true) {
				System.out.println("\n POST-EVALUACION: ----------------------- generacion: " + i + " -----------------------\n\n");
				System.out.println(mostrarPoblacion());			
			}
			
			ctrl.update(this.poblPrincipal, this.mejor);
		}
		
		System.out.println("El mejor es: " + mejor.toString());
		return "EL MEJOR ES:" + this.mejor.toString();
	}
}
