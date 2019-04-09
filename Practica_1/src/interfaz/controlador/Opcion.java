package interfaz.controlador;

public class Opcion {
	int pobl;
	int generaciones;
	double cruces;
	double pMut;
	double prec;
	double elite;
	String selec;
	String mut;
	int func;
	
	public Opcion(int pobl, int gener, double cruces, double pMut, double prec, double elite, String selec, String mut, int func) {
		this.pobl = pobl;
		this.generaciones = gener;
		this.cruces = cruces;
		this.pMut = pMut;
		this.prec = prec;
		this.elite = elite;
		this.selec = selec;
		this.mut = mut;
		this.func = func;
	}
}
