package biblioteca.model;

public class Usuario extends Persona {
    // Variable para almacenar la cantidad de libros prestados por el usuario
    private int librosPrestados;

    // Constructor que inicializa el nombre, apellido y la cantidad de libros prestados
    public Usuario(String nombre, String apellido) {
        super(nombre, apellido);
        this.librosPrestados = 0;  // Inicialmente, el usuario no tiene libros prestados
    }

    // Metodo para mostrar los datos del usuario, incluyendo el número de libros prestados
    @Override
    public void mostrarDatos() {
        System.out.println("Usuario: " + getNombreCompleto());  // Muestra el nombre completo del usuario
        System.out.println("Libros Prestados: " + librosPrestados);  // Muestra la cantidad de libros prestados
    }

    // Metodo getter para obtener el número de libros prestados por el usuario
    public int getLibrosPrestados() {
        return librosPrestados;
    }

    // Metodo que incrementa la cantidad de libros prestados por el usuario
    public void prestarLibro() {
        librosPrestados++;  // Incrementa el contador de libros prestados
    }

    // Metodo que decrementa la cantidad de libros prestados si tiene libros prestados
    public void devolverLibro() {
        if (librosPrestados > 0) {
            librosPrestados--;  // Reduce el contador de libros prestados solo si es mayor que 0
        }
    }

    // Sobrescribe toString para devolver el nombre completo del usuario
    @Override
    public String toString() {
        return getNombreCompleto();  // Devuelve el nombre completo del usuario
    }
}


