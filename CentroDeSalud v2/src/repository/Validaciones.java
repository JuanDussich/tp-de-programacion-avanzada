package repository;

public interface Validaciones {
	
	
	default String validarCaracteres(String Mensaje) {
		
		return Mensaje;
	}
	
	default String validarNumeros(String Num) {
		
		return Num;
	}
	
}
