package biblioteca.model;

import java.util.ArrayList;
import java.util.Date;

public class Bibliotecario extends Persona implements Gestionable {
    private ArrayList<Prestamo> prestamos;

    public Bibliotecario(String nombre, String apellido) {
        super(nombre, apellido);
        this.prestamos = new ArrayList<>();
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Bibliotecario: " + getNombreCompleto());
    }

    @Override
    public void prestarLibro(Usuario usuario, Libro libro) {
        prestarLibro(usuario, libro, new Date(), null);
    }

    public void prestarLibro(Usuario usuario, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        if (libro.isDisponible()) {
            libro.prestar();
            Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion, this);
            prestamos.add(prestamo);
            System.out.println("Libro prestado: " + libro.getTitulo() + " a " + usuario.getNombre());
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    @Override
    public void devolverLibro(Usuario usuario, Libro libro) {
        if (!libro.isDisponible()) {
            libro.devolver();
            System.out.println("Libro devuelto: " + libro.getTitulo());
        } else {
            System.out.println("Este libro ya fue devuelto.");
        }
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}

