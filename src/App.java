import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Inicialización de la biblioteca y creación de datos de prueba
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.registrarUsariosPrincipales("admin", "admin123", Rol.ADMIN);
        biblioteca.registrarUsariosPrincipales("usuario", "usuario123", Rol.NORMAL);
        biblioteca.crearLibroPrincipal("Los juegos del hambre", "Suzanne Collins", "Ciencia ficción");
        biblioteca.crearLibroPrincipal("El hombre en busca de sentido", "Viktor Frankl", "Psicología");
        biblioteca.crearLibroPrincipal("El principito", "Antoine de Saint-Exupéry", "Infantil");
        biblioteca.crearLibroPrincipal("El código Da Vinci", "Dan Brown", "Misterio");

        // Inicio de sesión
        Usuario usuarioActual = null;
        System.out.println("===== Bienvenido a la Biblioteca =====");
        while (usuarioActual == null) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contraseña = sc.nextLine();

            for (int i = 0; i < biblioteca.numUsuarios; i++) {
                if (biblioteca.usuarios[i].getNombre().equals(nombre)
                        && biblioteca.usuarios[i].getContraseña().equals(contraseña)) {
                    usuarioActual = biblioteca.usuarios[i];
                    break;
                }
            }

            if (usuarioActual == null) {
                System.out.println("No se ha encontrado a este usuario");
            }
        }

        System.out.println("Bienvenido, " + usuarioActual.getNombre() + " (Rol: " + usuarioActual.getRol() + ")");

        // Menú de opciones
        boolean salir = false;
        int opcion;
        do {
            if (usuarioActual.getRol() == Rol.ADMIN) {
                System.out.println("===== Menú Administrador =====");
                System.out.println("1. Añadir usuario");
                System.out.println("2. Mostrar Usuarios");
                System.out.println("3. Mostrar libros disponibles");
                System.out.println("4. Prestar Libro");
                System.out.println("5. Devolver libros prestados");
                System.out.println("6. Mostrar libros prestados");
                System.out.println("7. Buscar libros por título, autor o categoría");
                System.out.println("8. Agregar Libro");
                System.out.println("9. Eliminar Libro");
                System.out.println("10. Listar libros más prestados");
                System.out.println("11. Mostrar número de préstamos totales y activos");
                System.out.println("12. Mostrar usuario con más préstamos activos");
                System.out.println("0. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Dime un nombre para el usuario");
                        String nuevoNombre = sc.nextLine();
                        System.out.println("Dime una contraseña");
                        String nuevaContraseña = sc.nextLine();
                        Rol nuevoRol = null;
                        while (nuevoRol == null) {
                            System.out.print("Ingrese el rol del nuevo usuario (ADMIN/USUARIO): ");
                            String rolStr = sc.nextLine().toUpperCase();
                            if (rolStr.equals("ADMIN")) {
                                nuevoRol = Rol.ADMIN;
                            } else if (rolStr.equals("USUARIO")) {
                                nuevoRol = Rol.NORMAL;
                            } else {
                                System.out.println("Rol no válido. Solo se permite ADMIN o USUARIO.");
                            }
                        }
                        biblioteca.registrarUsuario(nuevoNombre, nuevaContraseña, nuevoRol, usuarioActual);
                        break;
                    case 2:
                        biblioteca.consultarUsuarios(usuarioActual);
                        break;
                    case 3:
                        biblioteca.mostrarLibrosDisponibles();
                        break;
                    case 4:
                        System.out.println("Dime el nombre del libro que deseas prestar");
                        String titulo = sc.nextLine();
                        biblioteca.realizarPrestamo(titulo);
                        break;
                    case 5:
                        System.out.println("Dime el nombre del libro que deseas devolver");
                        String titulo2 = sc.nextLine();
                        biblioteca.devolverLibrosPrestados(titulo2);
                        break;
                    case 6:
                        biblioteca.librosPrestados();
                        break;
                    case 7:
                        System.out.println("¿Cómo deseas buscar el libro?");
                        System.out.println("1. Título");
                        System.out.println("2. Autor");
                        System.out.println("3. Categoría");
                        int opcion1 = sc.nextInt();
                        sc.nextLine();
                        switch (opcion1) {
                            case 1:
                                System.out.println("Dime el título del libro que deseas buscar");
                                String titulo1 = sc.nextLine();
                                biblioteca.buscarPorTitulo(titulo1);
                                break;
                            case 2:
                                System.out.println("Dime el autor del libro que deseas buscar");
                                String autor = sc.nextLine();
                                biblioteca.buscarPorAutor(autor);
                                break;
                            case 3:
                                System.out.println("Dime la categoría del libro que deseas buscar");
                                String categoria = sc.nextLine();
                                biblioteca.buscarPorCategoria(categoria);
                                break;
                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }
                        break;
                    case 8:
                        System.out.println("Dime el título del libro:");
                        String titul = sc.nextLine();
                        System.out.println("Dime el autor del libro:");
                        String autorLibro = sc.nextLine();
                        System.out.println("Dime la categoría del libro:");
                        String categoriaLibro = sc.nextLine();
                        biblioteca.agregarLibro(titul, autorLibro, categoriaLibro, usuarioActual);
                        break;
                    case 9:
                        System.out.println("Dime el título del libro que deseas eliminar:");
                        String titul2 = sc.nextLine();
                        biblioteca.eliminarLibro(titul2, usuarioActual);
                        break;
                    case 10:
                        biblioteca.listarLibrosMasPrestados();
                        break;
                    case 11:
                        biblioteca.mostrarNumeroPrestamos();
                        break;
                    case 12:
                        biblioteca.usuarioConMasPrestamos();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            } else if (usuarioActual.getRol() == Rol.NORMAL) {
                System.out.println("===== Menú Usuario =====");
                System.out.println("1. Buscar libros por título, autor o categoría");
                System.out.println("2. Mostrar todos los libros disponibles");
                System.out.println("3. Realizar préstamos de libros");
                System.out.println("4. Devolver libros prestados");
                System.out.println("0. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("¿Cómo deseas buscar el libro?");
                        System.out.println("1. Título");
                        System.out.println("2. Autor");
                        System.out.println("3. Categoría");
                        int opcion2 = sc.nextInt();
                        sc.nextLine();
                        switch (opcion2) {
                            case 1:
                                System.out.println("Dime el título del libro que deseas buscar");
                                String titulo3 = sc.nextLine();
                                biblioteca.buscarPorTitulo(titulo3);
                                break;
                            case 2:
                                System.out.println("Dime el autor del libro que deseas buscar");
                                String autor2 = sc.nextLine();
                                biblioteca.buscarPorAutor(autor2);
                                break;
                            case 3:
                                System.out.println("Dime la categoría del libro que deseas buscar");
                                String categoria2 = sc.nextLine();
                                biblioteca.buscarPorCategoria(categoria2);
                                break;
                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }
                        break;
                    case 2:
                        biblioteca.mostrarLibrosDisponibles();
                        break;
                    case 3:
                        System.out.println("Dime el nombre del libro que deseas prestar");
                        String tituloPrestamo = sc.nextLine();
                        biblioteca.realizarPrestamo(tituloPrestamo);
                        break;
                    case 4:
                        System.out.println("Dime el nombre del libro que deseas devolver");
                        String tituloDevolver = sc.nextLine();
                        biblioteca.devolverLibrosPrestados(tituloDevolver);
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        } while (!salir);
        sc.close();
    }
}