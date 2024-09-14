package biblioteca.model;

public class Usuario extends Persona {
    private int librosPrestados;

    public Usuario(String nombre, String apellido) {
        super(nombre, apellido);
        this.librosPrestados = 0;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Usuario: " + getNombreCompleto());  // Usa getNombreCompleto()
        System.out.println("Libros Prestados: " + librosPrestados);
    }

    public int getLibrosPrestados() {
        return librosPrestados;
    }

    public void prestarLibro() {
        librosPrestados++;
    }

    public void devolverLibro() {
        if (librosPrestados > 0) {
            librosPrestados--;
        }
    }

    // Usa los getters para obtener el nombre y apellido
    @Override
    public String toString() {
        return getNombreCompleto();  // Devuelve solo el nombre completo
    }
}

