package BLL;

public enum Especialidad {
    CARDIOLOGIA(1, "CARDIOLOGIA", "Atención del corazón y sistema circulatorio"),
    PEDIATRIA(2, "PEDIATRIA", "Salud infantil"),
    NEUROLOGIA(3, "NEUROLOGIA", "Sistema nervioso central y periférico"),
    CLINICA(4, "CLINICA MEDICA", "Medicina general para adultos"),
	DERMATOLOGIA(5, "DERMATOLOGIA", "Piel y afecciones"),
	OTRO(6, "OTRA","Otra especialidad");
	/* TABLA ESPECIALIDAD DE \\
	 * (1, 'Clínica Médica', 'Consulta general'), (2, 'Pediatría', 'Atención
	 * infantil'), (3, 'Cardiología', 'Enfermedades del corazón'), (4,
	 * 'Dermatología', 'Piel y afecciones'), (5, 'Neurología', 'Sistema nervioso');
	 */

    private final int codigo;
    private final String nombre;
    private final String descripcion;

    Especialidad(int codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre; // para mostrar en JComboBox u otros componentes
    }

    public static Especialidad fromCodigo(int codigo) {
        for (Especialidad esp : values()) {
            if (esp.getCodigo() == codigo) return esp;
        }
        throw new IllegalArgumentException("Código de especialidad inválido: " + codigo);
    }

    public static Especialidad fromNombre(String nombre) {
        for (Especialidad esp : values()) {
            if (esp.getNombre().equalsIgnoreCase(nombre)) return esp;
        }
        throw new IllegalArgumentException("Nombre de especialidad inválido: " + nombre);
    }
}
