package biblioteca.model;

public abstract class Persona {
    // Atributos
    private final String nombre;
    private final String apellido;

    // Constructor
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Metodo abstracto para mostrar datos, implementado por las clases hijas
    public abstract void mostrarDatos();

    // Representación en texto del objeto Persona
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido;
    }

    // Metodo para obtener el nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Métodos para obtener el nombre y apellido individualmente
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

}
