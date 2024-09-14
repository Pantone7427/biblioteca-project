package biblioteca.model;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;  // Atributo para indicar si el libro está disponible o no

    // Constructor
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;  // Inicialmente, el libro está disponible
    }

    // Métodos getters y setters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Método para cambiar el estado de disponibilidad del libro
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método para prestar el libro
    public void prestar() {
        if (disponible) {
            this.disponible = false;  // Cambia el estado del libro a no disponible
        } else {
            throw new IllegalStateException("El libro ya está prestado");
        }
    }

    // Método para devolver el libro
    public void devolver() {
        if (!disponible) {
            this.disponible = true;  // Cambia el estado del libro a disponible
        } else {
            throw new IllegalStateException("El libro no está prestado");
        }
    }

    @Override
    public String toString() {
        return titulo + " (" + isbn + ")";
    }
}


