public class CLogica {
	
	static final String anidarComa(String cadena){
		if (cadena.indexOf(".") == -1){
			return ".";
		}else{
			return "";
		}
	}
	
	static final double masMenos(double val1){
		if (val1 == 0){
			return val1;
		}else{
			return val1 * -1;
		}
	}
	
	static final double suma(double val1, double val2){
		return val1 + val2;
	}
	
	static final double resta(double val1, double val2){
		return val1 - val2;
	}
	
	static final double multiplicacion(double val1, double val2){
		return val1 * val2;
	}
	
	static final double division(double val1, double val2){
		return val1/val2;
	}
	
	static final double porCiento(double val1){
		return val1 / 100;
	}

	static final double raizCuadrada(double val1){
		return Math.sqrt(val1);
	}
	
	static final double unoEntreX(double val1){
		return 1/(val1);
	}
	 

}
