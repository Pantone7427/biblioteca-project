package biblioteca;

import biblioteca.model.*;

public class BibliotecaMain {
    public static void main(String[] args) {
        // Crear instancias de usuarios, bibliotecarios y libros
        Usuario usuario1 = new Usuario("Juan", "Perez");
        Usuario usuario2 = new Usuario("Ana", "Lopez");
        Bibliotecario bibliotecario = new Bibliotecario("Carlos", "Gomez");

        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "12345");
        Libro libro2 = new Libro("1984", "George Orwell", "67890");

        // Simular préstamos
        bibliotecario.prestarLibro(usuario1, libro1);
        bibliotecario.prestarLibro(usuario1, libro2);

        // Mostrar datos de los usuarios y bibliotecario
        usuario1.mostrarDatos();
        usuario2.mostrarDatos();
        bibliotecario.mostrarDatos();

        // Devolver libro
        bibliotecario.devolverLibro(usuario1, libro1);

        // Mostrar datos nuevamente
        usuario1.mostrarDatos();
    }
}
