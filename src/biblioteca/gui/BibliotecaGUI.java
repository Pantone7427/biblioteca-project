package biblioteca.gui;

import biblioteca.model.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.Date;

public class BibliotecaGUI extends Application {

    // Listas para almacenar libros, usuarios y préstamos
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    // Controles de la interfaz
    private ComboBox<Libro> libroComboBox;
    private ComboBox<Usuario> usuarioComboBox;
    private TextField bibliotecarioNombreTextField;
    private TextField bibliotecarioApellidoTextField;
    private TextArea textArea;
    private TableView<Libro> inventarioTable;

    @Override
    public void start(Stage primaryStage) {
        // Inicialización de las listas
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();

        primaryStage.setTitle("Gestión de Biblioteca");

        // Panel principal usando GridPane para disposición de componentes
        GridPane panel = new GridPane();
        panel.setVgap(15);  // Espaciado vertical
        panel.setHgap(10);  // Espaciado horizontal
        panel.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;");  // Estilo de padding y color de fondo

        // Campos de entrada y botones
        TextField buscarLibroField = new TextField();
        buscarLibroField.setPromptText("Buscar libro...");

        TextField nombreUsuarioField = new TextField();
        TextField apellidoUsuarioField = new TextField();
        Button agregarUsuarioBtn = new Button("Agregar Usuario");

        TextField tituloLibroField = new TextField();
        TextField autorLibroField = new TextField();
        TextField isbnLibroField = new TextField();
        Button agregarLibroBtn = new Button("Agregar Libro");

        // Comboboxes para seleccionar libros y usuarios
        libroComboBox = new ComboBox<>();
        usuarioComboBox = new ComboBox<>();

        // Campos para ingresar datos del bibliotecario
        bibliotecarioNombreTextField = new TextField();
        bibliotecarioApellidoTextField = new TextField();

        // Botones para prestar y devolver libros
        Button btnPrestar = new Button("Prestar Libro");
        btnPrestar.setId("prestarLibro");  // Asigna un ID único para referencia
        Button btnDevolver = new Button("Devolver Libro");
        btnDevolver.setId("devolverLibro");  // Asigna un ID único para referencia

        // Crear un HBox para agrupar los botones de prestar y devolver
        HBox botonesPrestarDevolver = new HBox(20);  // Espaciado de 20 unidades entre botones
        botonesPrestarDevolver.getChildren().addAll(btnPrestar, btnDevolver);

        // Área de texto para mostrar resultados
        textArea = new TextArea();
        textArea.setEditable(false);  // No editable por el usuario
        textArea.setPrefHeight(300);  // Altura preferida
        textArea.setPrefWidth(600);   // Anchura preferida

        // Configuración de la tabla de inventario
        inventarioTable = new TableView<>();
        TableColumn<Libro, String> tituloCol = new TableColumn<>("Título");
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        TableColumn<Libro, String> autorCol = new TableColumn<>("Autor");
        autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
        TableColumn<Libro, String> estadoCol = new TableColumn<>("Estado");
        estadoCol.setCellValueFactory(cellData -> {
            boolean disponible = cellData.getValue().isDisponible();
            return new SimpleStringProperty(disponible ? "Disponible" : "Prestado");
        }); // Columna para estado del libro
        inventarioTable.getColumns().addAll(tituloCol, autorCol, estadoCol);

        // Agregar componentes al panel
        panel.add(new Label("Buscar libro:"), 0, 0);
        panel.add(buscarLibroField, 1, 0);
        panel.add(new Label("Selecciona un libro:"), 0, 1);
        panel.add(libroComboBox, 1, 1);
        panel.add(new Label("Selecciona un usuario:"), 0, 2);
        panel.add(usuarioComboBox, 1, 2);
        panel.add(new Label("Nombre del bibliotecario:"), 0, 3);
        panel.add(bibliotecarioNombreTextField, 1, 3);
        panel.add(new Label("Apellido del bibliotecario:"), 0, 4);
        panel.add(bibliotecarioApellidoTextField, 1, 4);
        panel.add(botonesPrestarDevolver, 0, 5, 2, 1);  // Agrega el HBox con los botones en la fila 5
        panel.add(new Label("Nombre usuario:"), 0, 6);
        panel.add(nombreUsuarioField, 1, 6);
        panel.add(new Label("Apellido usuario:"), 0, 7);
        panel.add(apellidoUsuarioField, 1, 7);
        panel.add(agregarUsuarioBtn, 1, 8);
        panel.add(new Label("Título libro:"), 0, 9);
        panel.add(tituloLibroField, 1, 9);
        panel.add(new Label("Autor libro:"), 0, 10);
        panel.add(autorLibroField, 1, 10);
        panel.add(new Label("ISBN libro:"), 0, 11);
        panel.add(isbnLibroField, 1, 11);
        panel.add(agregarLibroBtn, 1, 12);

        // Configuración de los eventos de los botones
        btnPrestar.setOnAction(e -> realizarPrestamo());
        btnDevolver.setOnAction(e -> devolverLibro());
        agregarUsuarioBtn.setOnAction(e -> agregarUsuario(nombreUsuarioField, apellidoUsuarioField));
        agregarLibroBtn.setOnAction(e -> agregarLibro(tituloLibroField, autorLibroField, isbnLibroField));

        // Listener para buscar libros en tiempo real
        buscarLibroField.textProperty().addListener((observable, oldValue, newValue) -> filtrarLibros(newValue));

        // Crear el layout principal y agregar componentes
        HBox root = new HBox(10);
        root.getChildren().addAll(panel, new ScrollPane(textArea), inventarioTable);

        // Configurar la escena
        Scene scene = new Scene(root, 1200, 600);

        // Enlazar archivo CSS para estilos
        scene.getStylesheets().add(getClass().getResource("/biblioteca/css/estilos.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        // Agregar datos de prueba y actualizar inventario
        agregarDatosPrueba();
        actualizarInventario();
    }

    private void realizarPrestamo() {
        // Obtener datos de la interfaz
        Libro libroSeleccionado = libroComboBox.getValue();
        Usuario usuarioSeleccionado = usuarioComboBox.getValue();
        String nombreBibliotecario = bibliotecarioNombreTextField.getText();
        String apellidoBibliotecario = bibliotecarioApellidoTextField.getText();

        if (libroSeleccionado != null && usuarioSeleccionado != null && !nombreBibliotecario.isEmpty() && !apellidoBibliotecario.isEmpty()) {
            if (usuarioSeleccionado.getLibrosPrestados() >= 3) {
                mostrarAlerta("Error", "El usuario ya tiene 3 libros prestados.", AlertType.ERROR);
                return;
            }

            // Validar datos y realizar préstamo
            if (libroSeleccionado.isDisponible()) {
                Date fechaPrestamo = new Date();
                Date fechaTentativaDevolucion = new Date(fechaPrestamo.getTime() + (14L * 24 * 60 * 60 * 1000));

                Bibliotecario bibliotecario = new Bibliotecario(nombreBibliotecario, apellidoBibliotecario);
                Prestamo prestamo = new Prestamo(libroSeleccionado, usuarioSeleccionado, fechaPrestamo, fechaTentativaDevolucion, bibliotecario);
                prestamos.add(prestamo);

                usuarioSeleccionado.prestarLibro();
                libroSeleccionado.setDisponible(false);

                textArea.appendText("Préstamo realizado:\n" + prestamo.obtenerInformacionCompleta() + "\n\n");
            } else {
                mostrarAlerta("Error", "El libro no está disponible.", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Faltan datos o el bibliotecario no ha sido ingresado.", AlertType.ERROR);
        }
        actualizarInventario();
    }

    private void devolverLibro() {
        // Obtener datos de la interfaz
        Libro libroSeleccionado = libroComboBox.getValue();
        Usuario usuarioSeleccionado = usuarioComboBox.getValue();

        // Validar datos y procesar devolución
        if (libroSeleccionado != null && usuarioSeleccionado != null) {
            Prestamo prestamo = buscarPrestamo(usuarioSeleccionado, libroSeleccionado);
            if (prestamo != null && !libroSeleccionado.isDisponible()) {
                prestamo.registrarDevolucion();  // Registrar la fecha real de devolución
                usuarioSeleccionado.devolverLibro();
                libroSeleccionado.setDisponible(true);

                textArea.appendText("Libro devuelto:\n" + prestamo.obtenerInformacionCompleta() + "\n\n");
            } else {
                mostrarAlerta("Error", "El libro no pertenece a este usuario o ya fue devuelto.", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "El libro no está prestado o falta información.", AlertType.ERROR);
        }
        actualizarInventario();
    }

    private void agregarUsuario(TextField nombre, TextField apellido) {
        // Validar campos y agregar usuario
        if (nombre.getText().isEmpty() || apellido.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos de usuario son requeridos.", AlertType.ERROR);
            return;
        }

        Usuario usuario = new Usuario(nombre.getText(), apellido.getText());
        usuarios.add(usuario);
        usuarioComboBox.getItems().add(usuario);
        mostrarAlerta("Éxito", "Usuario agregado correctamente.", AlertType.INFORMATION);

        // Limpiar campos de texto
        nombre.clear();
        apellido.clear();
    }

    private void agregarLibro(TextField titulo, TextField autor, TextField isbn) {
        // Validar campos y agregar libro
        if (titulo.getText().isEmpty() || autor.getText().isEmpty() || isbn.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos del libro son requeridos.", AlertType.ERROR);
            return;
        }

        Libro libro = new Libro(titulo.getText(), autor.getText(), isbn.getText());
        libros.add(libro);
        libroComboBox.getItems().add(libro);
        mostrarAlerta("Éxito", "Libro agregado correctamente.", AlertType.INFORMATION);
        actualizarInventario();

        // Limpiar campos de texto
        titulo.clear();
        autor.clear();
        isbn.clear();
    }

    private void filtrarLibros(String filtro) {
        // Filtrar libros en el ComboBox basado en el texto de búsqueda
        libroComboBox.getItems().clear();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(filtro.toLowerCase())) {
                libroComboBox.getItems().add(libro);
            }
        }
    }

    // Crear y agregar usuarios de prueba
    private void agregarDatosPrueba() {
        Usuario usuario1 = new Usuario("Juan", "Pérez");
        Usuario usuario2 = new Usuario("Ana", "Martínez");

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        usuarioComboBox.getItems().addAll(usuario1, usuario2);

        // Crear y agregar libros de prueba
        Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "1234567890");
        Libro libro2 = new Libro("Don Quijote", "Miguel de Cervantes", "0987654321");
        Libro libro3 = new Libro("1984", "George Orwell", "1122334455");
        Libro libro4 = new Libro("Matar a un Ruiseñor", "Harper Lee", "2233445566");
        Libro libro5 = new Libro("El Gran Gatsby", "F. Scott Fitzgerald", "3344556677");
        Libro libro6 = new Libro("Orgullo y Prejuicio", "Jane Austen", "4455667788");
        Libro libro7 = new Libro("Crimen y Castigo", "Fyodor Dostoevsky", "5566778899");
        Libro libro8 = new Libro("El Hobbit", "J.R.R. Tolkien", "6677889900");
        Libro libro9 = new Libro("La Sombra del Viento", "Carlos Ruiz Zafón", "7788990011");
        Libro libro10 = new Libro("Los Pilares de la Tierra", "Ken Follett", "8899001122");
        Libro libro11 = new Libro("El Código Da Vinci", "Dan Brown", "9900112233");
        Libro libro12 = new Libro("Cumbres Borrascosas", "Emily Brontë", "0011223344");
        Libro libro13 = new Libro("El Nombre de la Rosa", "Umberto Eco", "1122336677");
        Libro libro14 = new Libro("La Casa de los Espíritus", "Isabel Allende", "2233447788");
        Libro libro15 = new Libro("Los Juegos del Hambre", "Suzanne Collins", "3344559900");

        // Agregar los libros a la lista y al ComboBox
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);
        libros.add(libro6);
        libros.add(libro7);
        libros.add(libro8);
        libros.add(libro9);
        libros.add(libro10);
        libros.add(libro11);
        libros.add(libro12);
        libros.add(libro13);
        libros.add(libro14);
        libros.add(libro15);

        libroComboBox.getItems().addAll(libro1, libro2, libro3, libro4, libro5, libro6, libro7, libro8, libro9, libro10, libro11, libro12, libro13, libro14, libro15);
    }

    private void actualizarInventario() {
        inventarioTable.getItems().clear();
        inventarioTable.getItems().addAll(libros);
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private Prestamo buscarPrestamo(Usuario usuario, Libro libro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().equals(libro) && prestamo.getUsuario().equals(usuario)) {
                return prestamo;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

