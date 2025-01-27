public class Libro {
    String titulo;
    String autor;
    String categoria;
    boolean prestado;
    
    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.prestado = false;
    } 

}
