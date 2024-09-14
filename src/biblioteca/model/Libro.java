package biblioteca.model;

public class Libro {
    // Atributos para almacenar el título, autor, ISBN y estado de disponibilidad del libro
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;  // Indica si el libro está disponible para préstamo

    // Constructor para inicializar los atributos del libro
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;  // El libro se marca como disponible inicialmente
    }

    // Métodos getters para obtener los valores de los atributos
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    // Metodo para verificar si el libro está disponible para préstamo
    public boolean isDisponible() {
        return disponible;
    }

    // Metodo para cambiar el estado de disponibilidad del libro
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Metodo para prestar el libro. Si está disponible, cambia su estado a no disponible.
    public void prestar() {
        if (disponible) {
            this.disponible = false;  // El libro se marca como no disponible
        } else {
            throw new IllegalStateException("El libro ya está prestado");  // Lanza una excepción si ya está prestado
        }
    }

    // Metodo para devolver el libro. Si no está disponible, cambia su estado a disponible.
    public void devolver() {
        if (!disponible) {
            this.disponible = true;  // El libro se marca como disponible
        } else {
            throw new IllegalStateException("El libro no está prestado");  // Lanza una excepción si ya está devuelto
        }
    }

    // Sobrescribe el metodo toString para devolver una representación en texto del libro
    @Override
    public String toString() {
        return titulo + " (" + isbn + ")";  // Devuelve el título y el ISBN del libro
    }
}



