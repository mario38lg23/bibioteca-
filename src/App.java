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


        // Agregar los usuarios al sistema
        /*biblioteca.registrarUsuario(admin.getNombre(), admin.getContraseña(), admin.getRol(), admin);
        biblioteca.registrarUsuario(usuarioNormal.getNombre(), usuarioNormal.getContraseña(), usuarioNormal.getRol(), admin);
        biblioteca.mostrarLibrosDisponibles();*/
        // Login
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
        //menu temporal y ahyq ue realizar dos menus uno para usuarios normales y otros para usuarios
         boolean salir = false;
         int opcion;
         do{
            System.out.println("Bienvenido seleccione una opcion");
            System.out.println("1.Añadir usuario");
            System.out.println("2.Moatrar Usuarios");
            System.out.println("3.Mostar libros disponibles");
            System.out.println("4.Prestrar Libro");
            System.out.println("5.Devolver libro");
            System.out.println("6.Mostrar libros prestados");
            System.out.println("8.Agregar Libro");
            System.out.println("9.eliminar Libro");
            System.out.println("10.Listar libros mas prestados"); 
            System.out.println("11.Mostrar numero de prestamos totales y activos"); 
            System.out.println("12.mostrar usuario con mas prestamos activos"); 
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
              
                    case 8:
                    System.out.println("Dime el título del libro:");
                    String titul = sc.nextLine();  // Leer el título del libro
                
                    System.out.println("Dime el autor del libro:");
                    String autor = sc.nextLine();  // Leer el autor del libro
                
                    System.out.println("Dime la categoría del libro:");
                    String categoria = sc.nextLine();  // Leer la categoría del libro
                
                    // Llamada al método agregarLibro, pasándole los parámetros correspondientes
                    biblioteca.agregarLibro(titul, autor, categoria, usuarioActual);  // Asegúrate de que los parámetros estén bien
                
                    break;
                    case 9:
                    System.out.println("Dime el título del libro que deseas eliminar:");
                    String titul2 = sc.nextLine();  // Leer el título del libro

                    biblioteca.eliminarLibro(titul2, usuarioActual);
                    break;
                    case 10:
                    biblioteca.listarLibrosMasPrestados();
                    case 11:
                    biblioteca.mostrarNumeroPrestamos();
                    case 12:
                    biblioteca.usuarioConMasPrestamos();

                   
            
                default:
                    break;
            }
         }while(!salir);

        
    }
}
