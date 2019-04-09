package common.genes;

public class GenController {
	private static final Gen[] TIPOS = { new GenBi(0, 0, 0, 0), new GenReal(0) };
	
	/**
	 * llama a generar gen del tipo especificado
	 * @param tipo
	 * @param min
	 * @param max
	 * @param precision
	 * @return
	 */
	public static Gen generarGen(int tipo, int pos) {
		if(tipo == 0) {
			return new GenBi(pos, pos, pos, pos);
		} else if (tipo == 1) {
			return new GenReal(pos);
		} else {
			return null;
		}
		
	}
	
	public static Gen copiarGen(int tipo, Gen g) {
		if(tipo == 0) {
			return new GenBi((GenBi)g);
		} else if (tipo == 1) {
			return null;
			//return new GenReal(pos);
		} else {
			return null;
		}
	}
	
/*	public void setAlelo(double valor) {
		TIPOS[1].setAlelo(valor);
	}*/
	
/*	public double getAlelo() {
		return TIPOS[1].getAlelo();
	}*/
}
