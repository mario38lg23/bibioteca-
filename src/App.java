import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Inicialización de la biblioteca y creación de datos de prueba
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.registrarUsariosPrincipales("admin", "admin123", Rol.ADMIN); 
        biblioteca.registrarUsariosPrincipales("usuario", "usuario123", Rol.NORMAL); 
        biblioteca.crearLibroPrincipal("Los juegos del hambre", "Suzanne Collins", "Ciencia ficcion");
        biblioteca.crearLibroPrincipal("El hombre en busca de sentido", "Viktor Frankl", "Psicologia");
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
                if (biblioteca.usuarios[i].getNombre().equals(nombre) && biblioteca.usuarios[i].getContraseña().equals(contraseña)) {
                    usuarioActual = biblioteca.usuarios[i];
                    break;
                }
            }

            if (usuarioActual == null) {
                System.out.println("No se ha encontrado a este usuario");
            }
        }

        System.out.println("Bienvenido, " + usuarioActual.getNombre() + " (Rol: " + usuarioActual.getRol() + ")");
        //menú de opciones
         boolean salir = false;
         int opcion;
         do{
         if (usuarioActual.getRol()== Rol.ADMIN) {
            System.out.println("Bienvenido administrador");
            System.out.println("1.Añadir usuario");
            System.out.println("2.Mostrar Usuarios");
            System.out.println("3.Mostrar libros disponibles");
            System.out.println("4.Prestar Libro");
            System.out.println("5.Devolver libros prestados");
            System.out.println("6.Mostrar libros prestados");
            System.out.println("7.Buscar libros por titulo autor y categoria");
            System.out.println("8.agregar libros");
            System.out.println("9.Eliminar libros existentes");
            System.out.println("10.Mostrar numeros de prestamos totales y activos");
            System.out.println("11.Listar los libros mas prestados");
            System.out.println("12.Mostar que usuario tiene mas prestamos activos");
            System.out.println("0.Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                if (usuarioActual.getRol()== Rol.ADMIN) {
                    
                
                System.out.println("Dime un nombre para el usuario");
                String nombre = sc.nextLine();
                System.out.println("Dime una contraseña");
                String contraseña = sc.nextLine();
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
                biblioteca.registrarUsuario(nombre, contraseña, nuevoRol, usuarioActual); 
                    }else{
                        System.out.println("Acceso denegado: Solo los administradores pueden registrar usuarios."); 
                    }       
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
                    System.out.println("¿Como deseas buscar el libro?");
                    System.out.println("1.titulo");
                    System.out.println("2.Autor");
                    System.out.println("3.Categoria");
                    int opcion1=sc.nextInt();
                    sc.nextLine();
                    switch (opcion1) {
                        case 1:
                        System.out.println("Dime el titulo del libro que deseas buscar");
                        String titulo1 = sc.nextLine();
                            biblioteca.buscarPorTitulo(titulo1);
                            break;
                            case 2:
                            System.out.println("Dime el autor del libro que deseas buscar");
                            String autor = sc.nextLine();
                            biblioteca.buscarPorAutor(autor);
                            break;
                            case 3:
                            System.out.println("Dime la categoria del libro que deseas buscar");
                            String categoria = sc.nextLine();
                            biblioteca.buscarPorCategoria(categoria);
                            break;
                    
                        default:
                            break;
                    }
                    break;
                    case 8:

                    break;
                    case 9:

                    break;
                    case 10:

                    break;
                    case 11:

                    break;
                    case 12:

                    break;
                    case 13:

                    break;
                    case 0:
                    salir=true;
                    break;
            
                default:
                    break;
            }
         
        }else if (usuarioActual.getRol()==Rol.NORMAL) {
            //menu de opciones para usuarios normales
            System.out.println("Biemvenido usuario");
            System.out.println("1.Buscar libors por titulo, autor o categoria");
            System.out.println("2.Mostrar todos los libros disponibles");
            System.out.println("3.Realizar prestamos de libros");
            System.out.println("4.Devolver libros prestados");
            System.out.println("5.Salir");
            opcion= sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                System.out.println("¿Como deseas buscar el libro?");
                    System.out.println("1.titulo");
                    System.out.println("2.Autor");
                    System.out.println("3.Categoria");
                    int opcion1=sc.nextInt();
                    switch (opcion1) {
                        case 1:
                        System.out.println("Dime el titulo del libro que deseas buscar");
                        String titulo1 = sc.nextLine();
                            biblioteca.buscarPorTitulo(titulo1);
                            break;
                            case 2:
                            System.out.println("Dime el autor del libro que deseas buscar");
                            String autor = sc.nextLine();
                            biblioteca.buscarPorAutor(autor);
                            break;
                            case 3:
                            System.out.println("Dime la categoria del libro que deseas buscar");
                            String categoria = sc.nextLine();
                            biblioteca.buscarPorCategoria(categoria);
                            break;
                    
                        default:
                            break;
                    }
                    break;
                    case 2:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                    case 3:
                    System.out.println("Dime el nombre del libro que deseas prestar");
                    String titulo = sc.nextLine();
                    biblioteca.realizarPrestamo(titulo);
                    break;
                    case 4:
                    System.out.println("Dime el nombre del libro que deseas devolver");
                    String titulo2 = sc.nextLine(); 
                    biblioteca.devolverLibrosPrestados(titulo2);
                    break;
                    case 0:
                    salir = true;
                    break;
            
                default:
                    break;
            }
        }
    }while(!salir);
    sc.close();

        
    }
}
