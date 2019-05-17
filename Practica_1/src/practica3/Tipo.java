package practica3;

public enum Tipo {
	AVANZA("Terminal"), GIRA_DERECHA("Terminal"), GIRA_IZQUIERDA("Terminal"),
	SIC("Funcion"), PROGN2("Funcion"), PROGN3("Funcion");
	
	private final String tipo;
	
	private Tipo(String tipo) {
		this.tipo = tipo;
	}
		
	public String getTipo() {
		return tipo;
	}

	public static Tipo[] getTerminales() {
		return new Tipo[] {AVANZA, GIRA_DERECHA, GIRA_IZQUIERDA};
	}
	
	public static Tipo[] getFunciones() {
		return new Tipo[] {SIC, PROGN2, PROGN3};
	}
}