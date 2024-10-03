package biblioteca.model;

import java.util.ArrayList;
import java.util.Date;

// Clase Bibliotecario que extiende Persona e implementa la interfaz Gestionable
public class Bibliotecario extends Persona implements Gestionable {

    // Lista para almacenar los préstamos realizados por el bibliotecario
    private ArrayList<Prestamo> prestamos;

    // Constructor que inicializa el bibliotecario con nombre y apellido, y crea la lista de préstamos
    public Bibliotecario(String nombre, String apellido) {
        super(nombre, apellido);  // Llama al constructor de la clase Persona
        this.prestamos = new ArrayList<>();  // Inicializa la lista de préstamos
    }

    // Metodo que imprime los datos del bibliotecario, sobrescribiendo el método mostrarDatos de la interfaz
    @Override
    public void mostrarDatos() {
        System.out.println("Bibliotecario: " + getNombreCompleto());  // Muestra el nombre completo del bibliotecario
    }

    // Metodo que realiza un préstamo, asignando la fecha actual y sin fecha de devolución
    @Override
    public void prestarLibro(Usuario usuario, Libro libro) {
        prestarLibro(usuario, libro, new Date(), null);  // Llama a la sobrecarga del método para gestionar el préstamo
    }

    // Método sobrecargado para prestar un libro, permitiendo especificar fechas de préstamo y devolución
    public void prestarLibro(Usuario usuario, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        // Verifica si el libro está disponible
        if (libro.isDisponible()) {
            libro.prestar();  // Marca el libro como prestado
            // Crea un nuevo objeto Prestamo y lo añade a la lista de préstamos
            Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion, this);
            prestamos.add(prestamo);
            // Imprime un mensaje confirmando el préstamo
            System.out.println("Libro prestado: " + libro.getTitulo() + " a " + usuario.getNombre());
        } else {
            // Si el libro no está disponible, imprime un mensaje de error
            System.out.println("El libro no está disponible.");
        }
    }

    // Metodo que permite devolver un libro prestado
    @Override
    public void devolverLibro(Usuario usuario, Libro libro) {
        // Verifica si el libro no está disponible (es decir, está prestado)
        if (!libro.isDisponible()) {
            libro.devolver();  // Marca el libro como devuelto
            // Imprime un mensaje confirmando la devolución
            System.out.println("Libro devuelto: " + libro.getTitulo());
        } else {
            // Si el libro ya está disponible, indica que ya fue devuelto
            System.out.println("Este libro ya fue devuelto.");
        }
    }

    // Sobrescribe el metodo toString para devolver el nombre completo del bibliotecario
    @Override
    public String toString() {
        return getNombreCompleto();  // Retorna el nombre completo como representación de la instancia
    }
}

