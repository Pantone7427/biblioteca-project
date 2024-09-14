# Gestión de Biblioteca

## Descripción

La aplicación de **Gestión de Biblioteca** permite gestionar libros, usuarios y préstamos en una biblioteca. Utilizando **JavaFX** para la interfaz gráfica, la aplicación ofrece un conjunto completo de funcionalidades para administrar el inventario de libros, realizar préstamos y devoluciones, y gestionar usuarios.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal utilizado para el desarrollo de la aplicación.
- **JavaFX**: Biblioteca para la creación de interfaces gráficas en Java.
- **Maven** (opcional): Sistema de gestión de proyectos y construcción que puede ser usado para manejar dependencias y construir el proyecto.

## Funcionalidades

1. **Agregar Usuario**:
   - Permite ingresar un nuevo usuario con nombre y apellido.
   - Los usuarios se agregan a una lista y se actualiza la interfaz.

2. **Agregar Libro**:
   - Permite ingresar un nuevo libro con título, autor e ISBN.
   - Los libros se agregan a una lista y se actualiza el inventario de libros en la interfaz.

3. **Prestar Libro**:
   - Permite registrar el préstamo de un libro a un usuario.
   - Se requiere seleccionar un libro, un usuario, y proporcionar el nombre y apellido del bibliotecario.
   - El libro se marca como prestado y se actualiza la lista de préstamos.

4. **Devolver Libro**:
   - Permite registrar la devolución de un libro prestado.
   - Se requiere seleccionar el libro y el usuario, y la aplicación verifica si el libro está efectivamente prestado antes de registrar la devolución.

5. **Buscar Libro**:
   - Permite buscar libros por título utilizando un campo de texto.
   - Los libros que coincidan con el criterio de búsqueda se muestran en un combobox para su selección.

6. **Mostrar Inventario**:
   - Muestra una tabla con el inventario actual de libros, incluyendo título, autor y estado (Disponible/Prestado).
   - La tabla se actualiza automáticamente cuando se agregan o modifican libros.

7. **Mostrar Información de Préstamo y Devolución**:
   - Muestra información detallada sobre los préstamos y devoluciones en un área de texto, incluyendo los detalles del libro, usuario y bibliotecario.

## Requisitos

- **Java 11 o superior**: Asegúrate de tener instalada una versión compatible de Java.
- **JavaFX**: La aplicación utiliza JavaFX para la interfaz gráfica. Asegúrate de tener el SDK de JavaFX configurado en tu entorno de desarrollo.


## Instrucciones para Ejecutar el Proyecto

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/gestion-biblioteca.git
2. Abrir el proyecto en IntelliJ IDEA.
3. Ejecutar la clase `BibliotecaGUI.java` que se encuentra en el paquete `biblioteca.gui`.
4. Interactuar con la interfaz gráfica para realizar préstamos y devoluciones de libros.

## Tecnologías Utilizadas

- **Java 11 o superior**: Asegúrate de tener instalada una versión compatible de Java.
- **JavaFX**: La aplicación utiliza JavaFX para la interfaz gráfica. Asegúrate de tener el SDK de JavaFX configurado en tu entorno de desarrollo.
## Mejora Continua

Este proyecto puede ampliarse para incluir nuevas funcionalidades como persistencia de datos (almacenamiento en archivos o bases de datos) o gestión de múltiples bibliotecas.
