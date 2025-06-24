package repository;

import java.util.function.Function;

import javax.swing.JOptionPane;

public interface Validaciones {
	
	
	default String validarCaracteres(String Mensaj) {
		boolean flag;
		String mensaje = "";
		try {
			do {
				flag = true;
				while (mensaje == null || mensaje.isEmpty()){
	                mensaje = JOptionPane.showInputDialog("error ingrese de nuevo, no puede estar vacio");
	            }
				for (int i = 0; i < mensaje.length(); i++) {
					if (Character.isDigit(mensaje.charAt(i))) {
						JOptionPane.showMessageDialog(null, "no se puede ingresar numeros en el nombre");
						flag = false;
					}
				
				}
			} while (flag != true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mensaje;
	}
	
	default int validarNumeros(String Num) {
		boolean flag;
		String numero = "";
		try {
			do {
				flag = true;
				while (numero == null || numero.isEmpty()) {
					numero = JOptionPane.showInputDialog("error, no puede estar vacio");
				}
				for (int i = 0; i < numero.length(); i++) {
					if (Character.isAlphabetic(numero.charAt(i))) {
						JOptionPane.showMessageDialog(null, "no pueden haber letras");
					}
					if (!Character.isDigit(numero.charAt(i))) {
						JOptionPane.showMessageDialog(null, "tienen que ser digitos");
						flag = false;
					}
				}
			} while (!flag == true);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return Integer.parseInt(numero);
	}
	
	// validaciones para dni

	default int validardni(String num) {
	    String numero = num;

	    while (true) {
	        if (numero == null || numero.trim().isEmpty()) {
	            numero = JOptionPane.showInputDialog("Error: DNI no puede estar vacío.");
	            continue;
	        }

	        // Elimina espacios en blanco
	        numero = numero.trim();

	        // Verifica si tiene solo dígitos
	        if (!numero.matches("\\d+")) {
	            JOptionPane.showMessageDialog(null, "Error: DNI debe contener solo números.");
	            numero = JOptionPane.showInputDialog("Ingresá un DNI válido:");
	            continue;
	        }

	        // Verifica la cantidad de dígitos
	        if (numero.length() < 7 || numero.length() > 8) {
	            JOptionPane.showMessageDialog(null, "Error: DNI debe tener 7 u 8 dígitos.");
	            numero = JOptionPane.showInputDialog("Ingresá un DNI válido:");
	            continue;
	        }

	        break; // Si pasa todas las validaciones, se sale del bucle
	    }

	    return Integer.parseInt(numero);
	}



}
