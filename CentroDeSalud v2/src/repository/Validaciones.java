package repository;

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
	
}
