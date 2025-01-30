import java.util.Arrays;

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
        System.out.println("===== Libros Disponibles =====");
        for (int i = 0; i < numLibros; i++) {
            if (!libros[i].isPrestado()) { // Accede correctamente con isPrestado()
                System.out.println("Título: " + libros[i].getTitulo() + ", Autor: " + libros[i].getAutor()
                        + ", Categoría: " + libros[i].getCategoria());
                hayLibrosDisponibles = true;
            }
        }
        if (!hayLibrosDisponibles) {
            System.out.println("No hay libros disponibles.");
        }
    }

    public void registrarUsariosPrincipales(String nombre, String contraseña, Rol rol) {
        usuarios[numUsuarios++] = new Usuario(nombre, contraseña, rol);
    }

    public void crearLibroPrincipal(String titulo, String autor, String categoria) {
        libros[numLibros++] = new Libro(titulo, autor, categoria);
    }

    public void registrarUsuario(String nombre, String contraseña, Rol rol, Usuario admin) {
        if (admin.getRol() == Rol.ADMIN) {
            if (numUsuarios < usuarios.length) {
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
                System.out
                        .println((i + 1) + ". Nombre: " + usuarios[i].getNombre() + " | Rol: " + usuarios[i].getRol());
            }
        } else {
            System.out.println("Acceso denegado: Solo los administradores pueden consultar usuarios.");
        }
    }

    public void realizarPrestamo(String nombreLibro) {
        boolean encontrado = false;

        for (int i = 0; i < numLibros; i++) {
            if (nombreLibro.equals(libros[i].getTitulo())) {
                encontrado = true;

                if (libros[i].isPrestado()) { // Usa isPrestado() en lugar de getPrestado()
                    System.out.println("Este libro ya está prestado.");
                } else {
                    libros[i].setPrestado(true); // Marca el libro como prestado
                    System.out.println("Has realizado el préstamo del libro: " + libros[i].getTitulo());
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se ha encontrado el libro que has solicitado.");

        }
    }

    public void devolverLibrosPrestados(String nombreLibro) {
        boolean libroEncontrado = false;
        for (int i = 0; i < numLibros; i++) {
            if (nombreLibro.equals(libros[i].getTitulo())) {
                libroEncontrado = true;
                if (libros[i].getPrestado() == false) {
                    System.out.println("Este libro no esta prestado");
                } else {
                    libros[i].setPrestado(false);
                    System.out.println("El libro se ha devuelto correctamente");
                }
            }
            if (!libroEncontrado) {
                System.out.println("No se ha encontrado el libro que has solicitado");
            }
        }
    }

    public void librosPrestados() {
        boolean encontrado = false;
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getPrestado() == true) {
                System.out.println(libros[i].getTitulo());
                encontrado = true;
            }

        }
        if (encontrado == false) {
            System.out.println("No hay libros prestados");

        }
    }

    public void agregarLibro(String titulo2, String autor, String categoria, Usuario admin) {
        if (admin.getRol() == Rol.ADMIN) {
            if (numLibros < libros.length) {
                libros[numLibros++] = new Libro(titulo2, autor, categoria);
                System.out.println("Libro agregado correctamente: " + titulo2);
            } else {
                System.out.println("No se pueden agregar más libros. Espacio insuficiente.");
            }
        } else {
            System.out.println("Acceso denegado: Solo los administradores pueden agregar libros.");
        }
    }

    public void eliminarLibro(String titulo, Usuario admin) {
        if (admin.getRol() == Rol.ADMIN) {
            for (int i = 0; i < numLibros; i++) {
                if (libros[i].getTitulo().equals(titulo)) {
                    libros[i] = libros[numLibros - 1];
                    libros[numLibros - 1] = null;
                    numLibros--;
                    System.out.println("Libro eliminado correctamente: " + titulo);
                    return;
                }
            }
            System.out.println("Libro no encontrado.");
        } else {
            System.out.println("Acceso denegado: Solo los administradores pueden eliminar libros.");
        }
    }

    public void mostrarNumeroPrestamos() {
        int prestamosActivos = 0;
        int prestamosTotales = 0;
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].isPrestado()) {
                prestamosActivos++;
            }
            prestamosTotales++;
        }
        System.out.println("Total de : " + prestamosTotales);
        System.out.println("Préstamos activos: " + prestamosActivos);
    }

    public void listarLibrosMasPrestados() {
        System.out.println("===== Libros más prestados =====");

        // Ordenamos los libros según las veces prestados en orden descendente
        Arrays.sort(libros, 0, numLibros, (a, b) -> Integer.compare(b.getVecesPrestado(), a.getVecesPrestado()));

        // Mostrar los 5 libros más prestados o el total de libros si hay menos de 5
        for (int i = 0; i < Math.min(5, numLibros); i++) {
            System.out.println(libros[i].getTitulo() + " - Prestado " + libros[i].getVecesPrestado() + " veces");
        }
    }

    public void usuarioConMasPrestamos() {
        Usuario usuarioMasPrestamos = null;
        int maxPrestamos = 0;
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].getPrestamosActivos() > maxPrestamos) {
                maxPrestamos = usuarios[i].getPrestamosActivos();
                usuarioMasPrestamos = usuarios[i];
            }
        }
        if (usuarioMasPrestamos != null) {
            System.out.println("Usuario con más préstamos activos: " + usuarioMasPrestamos.getNombre() + " con "
                    + maxPrestamos + " préstamos.");
        } else {
            System.out.println("No hay préstamos activos.");
        }
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public Libro[] getLibros() {
        return libros;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public int getNumLibros() {
        return numLibros;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public void buscarPorTitulo(String titulo) {
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

    public void buscarPorAutor(String autor) {
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

    public void buscarPorCategoria(String categoria) {
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
