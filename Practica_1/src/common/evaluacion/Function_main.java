package common.evaluacion;

import common.Poblacion;

public class Function_main {
	public static double[] MAX;
	public static double[] MIN;
	public static boolean MAX_MIN; //False => Máximo; True => Mínimo
	public static double[] PRECISION;
	private static Function_Controller controller;
	private static int Tarrays;
	
	
	public static void Set_Arrays(int n) {
		System.out.println(n);
		Function_main.MAX = new double[n];
		Function_main.MIN = new double[n];
		Function_main.PRECISION = new double[n];
	}
	
	/**
	 * @param pobl_evaluar
	 * @param tipo
	 */
	public void Evalua(Poblacion pobl_evaluar, int tipo) {
		int x = 0;
		
		System.out.println(pobl_evaluar.getTpobl());
		for (int i = 0; i<pobl_evaluar.getTpobl();i++) {
			controller.Evalua(pobl_evaluar.getIndividuos(i),tipo);
			x += pobl_evaluar.getIndividuos(i).getApt();
		}
		
		
		for(int i = 0; i<pobl_evaluar.getTpobl();i++) {	
			pobl_evaluar.getIndividuos(i).setPunt(pobl_evaluar.getIndividuos(i).getApt()/x);
		}
		pobl_evaluar.calcularPuntAcum();
	}
	
	
}
