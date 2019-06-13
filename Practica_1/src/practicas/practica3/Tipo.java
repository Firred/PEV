package practicas.practica3;

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
	
	public static Tipo[] getConOperandos(int op) {
		switch (op) {
		case 2:
			return new Tipo[] {SIC, PROGN2};
	
		case 3: 
			return new Tipo[] {PROGN3};

		default:
			return null;
		}
	}
	
	public int operandos() {
		switch (this) {
		case AVANZA:	
		case GIRA_DERECHA:
		case GIRA_IZQUIERDA:
			return 0;

		case PROGN2:
		case SIC:
			return 2;
			
		case PROGN3:
			return 3;
			
		default:
			return 0;
		}
	}
}