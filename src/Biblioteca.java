public class Biblioteca {
    Libro[] libros = new Libro[100];
    Usuario[] usuarios = new Usuario[50];
    int numLibros = 0;
    int numUsuarios = 0;

    public void mostrarLibrosDisponibles() {
        boolean hayLibrosDisponibles = false;
        for (int i = 0; i < numLibros; i++) {
            if (!libros[i].prestado) { // Solo muestra libros no prestados
                System.out.println("Título: " + libros[i].titulo + ", Autor: " + libros[i].autor + ", Categoría: " + libros[i].categoria);
                hayLibrosDisponibles = true;
            }
        }
        if (!hayLibrosDisponibles) {
            System.out.println("No hay libros disponibles.");
        }
    }
}
