

public class Biblioteca {
    Usuario[] usuarios; 
    Libro[] libros;     
    int numUsuarios;    
    int numLibros;      

    public Biblioteca() {
        usuarios = new Usuario[100]; 
        libros = new Libro[100];     
        numUsuarios = 0;             
        numLibros = 0;               
    }

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
    public void registrarUsariosPrincipales(String nombre, String contraseña, Rol rol){
        usuarios[numUsuarios++] = new Usuario(nombre, contraseña, rol);
    }
    public void crearLibroPrincipal(String titulo, String autor, String categoria ){
        libros[numLibros++] = new Libro(titulo, autor, categoria);
    }
    public void registrarUsuario(String nombre, String contraseña, Rol rol, Usuario admin) {
        if (admin.getRol() == Rol.ADMIN) {
            if (numUsuarios < usuarios.length) { // Controla que no exceda el tamaño del array
                usuarios[numUsuarios++] = new Usuario(nombre, contraseña, rol);
                System.out.println("Usuario registrado correctamente: " + nombre + " (Rol: " + rol + ")");
            } else {
                System.out.println("No se pueden registrar más usuarios. Espacio insuficiente.");
            }
        } else {
            System.out.println("Acceso denegado: Solo los administradores pueden registrar usuarios.");
        }
    }
    public void consultarUsuarios(Usuario admin) {
        if (admin.getRol() == Rol.ADMIN) {
            System.out.println("===== Usuarios Registrados =====");
            for (int i = 0; i < numUsuarios; i++) {
                System.out.println((i + 1) + ". Nombre: " + usuarios[i].getNombre() + " | Rol: " + usuarios[i].getRol());
            }
        } else {
            System.out.println("Acceso denegado: Solo los administradores pueden consultar usuarios.");
        }
    }
    public void realizarPrestamo(String nombreLibro){
        boolean encontrado = false;

    for (int i = 0; i < numLibros; i++) {
        if (nombreLibro.equals(libros[i].getTitulo())) { // Comparación correcta
            encontrado = true; // Marcamos que encontramos el libro

            if (libros[i].getPrestado()) { // Verificamos si está prestado
                System.out.println("Este libro ya está prestado.");
            } else {
                libros[i].setPrestado(true); // Cambiamos el estado del libro
                System.out.println("Has realizado el préstamo del libro: " + libros[i].getTitulo());
            }
            break; // Salimos del bucle porque ya encontramos el libro
        }
    }

    if (!encontrado) { // Si no se encontró ningún libro con ese título
        System.out.println("No se ha encontrado el libro que has solicitado.");
    }
    }
    public void devolverLibrosPrestados(String nombreLibro){
        boolean libroEncontrado = false;
        for(int i=0;i<numLibros;i++){
            if (nombreLibro.equals(libros[i].getTitulo())) {
                libroEncontrado = true;
                if(libros[i].getPrestado() == false){
                    System.out.println("Este libro no esta prestado");
                }else{
                    libros[i].setPrestado(false);
                    System.out.println("El libro se ha devuelto correctamente");
                }
        }
        if (!libroEncontrado) {
            System.out.println("No se ha encontrado el libro que has solicitado");
        }
    }
  }
    public void librosPrestados(){
        boolean encontrado = false;
        for(int i=0;i<numLibros;i++){
            if (libros[i].getPrestado()==true) {
                System.out.println(libros[i].getTitulo());
                encontrado = true;
            }
        }
        if (encontrado==false) {
            System.out.println("No hay libros prestados");
            
        }
    }/* 
    public void buscarLibro(int opcion){
        boolean libroEncontrado= false;
        Scanner sc = new Scanner(System.in);
        
        switch (opcion) {
            case 1:
            System.out.println("Dime el titulo que deseas buscar ");
            String titulo = sc.nextLine();
                for(int i= 0; i<numLibros;i++){
                    if (titulo.equals(libros[i].getTitulo())) {
                        System.out.println("El autor de este libro es " + libros[i].getAutor());
                        System.out.println("La categoria de este libro es " + libros[i].getCategoria());
                        libroEncontrado= true;
                    }
                    
                }
                if (!libroEncontrado) {
                    System.out.println("No se encontró un libro con ese título.");
                }
                break;
                case 2:
                System.out.println("Dime el autor que deseas buscar");
                String autor = sc.nextLine();
                for (int i = 0; i<numLibros;i++){
                    if (autor.equals(libros[i].getAutor())) {
                        System.out.println("El titulo de este libro es " + libros[i].getTitulo());
                        System.out.println("La categoria de este libro es " + libros[i].getCategoria());
                        libroEncontrado= true;
                    }
                
                }
                if (!libroEncontrado) {
                    System.out.println("No se encontró un libro con ese autor");
                }
                break;
                case 3:
                System.out.println("Dime la cateoria que deseas");
                String categoria = sc.nextLine();
                for(int i=0; i<numLibros;i++){
                    if (categoria.equals(libros[i].getCategoria())) {
                        System.out.println("El titulo de este libro es " + libros[i].getTitulo());
                        System.out.println("El autor de este libro es " + libros[i].getAutor());
                        libroEncontrado= true;
                    }
                }
                if (!libroEncontrado) {
                    System.out.println("No se encontró un libro con esa categoria.");
                }
                break;
        
            default:
            System.out.println("Selecciona una opcion correcta ");
                break;
        }
    }*/
        public void buscarPorTitulo(String titulo){
            boolean encontrado = false;
            for (int i = 0; i < numLibros; i++) {
                if (titulo.equals(libros[i].getTitulo())) {
                    System.out.println("Autor: " + libros[i].getAutor() + ", Categoría: " + libros[i].getCategoria());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se ha encontrado un libro con ese título.");
            }
        }
        public void buscarPorAutor(String autor){
            boolean encontrado = false;
            for (int i = 0; i < numLibros; i++) {
                if (autor.equals(libros[i].getAutor())) {
                    System.out.println("Título: " + libros[i].getTitulo() + ", Categoría: " + libros[i].getCategoria());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se ha encontrado un libro con ese autor.");
            }
        }
        public void buscarPorCategoria(String categoria){
            boolean encontrado = false;
            for (int i = 0; i < numLibros; i++) {
                if (categoria.equals(libros[i].getCategoria())) {
                    System.out.println("Título: " + libros[i].getTitulo() + ", Autor: " + libros[i].getAutor());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se ha encontrado un libro con esa categoría.");
            }
        }
        
    
}
