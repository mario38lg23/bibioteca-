import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Inicialización de la biblioteca y creación de datos de prueba
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.agregarUsuario("admin", "admin123", Rol.ADMIN);
        biblioteca.agregarUsuario("usuario1", "pass123", Rol.USUARIO);
        biblioteca.agregarUsuario("usuario2", "pass456", Rol.USUARIO);

        biblioteca.agregarLibro("El Hobbit", "J.R.R. Tolkien", "Fantasía", biblioteca.usuarios[0]);
        biblioteca.agregarLibro("1984", "George Orwell", "Distopía", biblioteca.usuarios[0]);
        biblioteca.agregarLibro("Cien Años de Soledad", "Gabriel García Márquez", "Realismo mágico", biblioteca.usuarios[0]);

        Scanner scanner = new Scanner(System.in);
    }
}
