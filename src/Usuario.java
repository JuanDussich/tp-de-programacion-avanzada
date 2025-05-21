
public class Usuario {
	
	//ATRIBUTOS
    protected String Nombre;
    protected String Apellido;
    private String contrasenia;
    private String email;

    // CONSTRUCTOR
    public Usuario() {
    }

    public Usuario(String nombre,String apellido,String email, String contrasenia) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    // METODOS
    public void Login(){

    }
    public void Registro(){

    }



    // GETTERS Y SETTERS
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Nombre='" + Nombre + '\'' + ", Apellido='" + Apellido + '\'' + ", contrasenia='" + contrasenia + '\'' + ", mail='" + email + '\'' +
                '}';
    }

}
