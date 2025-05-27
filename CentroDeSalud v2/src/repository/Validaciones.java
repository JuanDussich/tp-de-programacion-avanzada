package repository;

public interface Validaciones {
	
	
	default String validarCaracteres(String Mensaje) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Mensaje;
	}
	
	default String validarNumeros(String Num) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return Num;
	}
	
}
