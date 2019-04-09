package common.mutacion;

public class FactoriaMutacion {
	public static Mutacion getMutacion(String mutacion, Object[] args) {
		switch (mutacion) {
		case "Normal":
			if(args.length == 1 && args[0].getClass() == Double.class) 
				return new MutacionImp((Double)args[0]);
			else
				return null;
			

		default:
			return new MutacionImp(0.002);
		}
	}
}
