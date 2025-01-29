public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private boolean prestado;
    private int vecesPrestado;  // Nueva variable para contar los préstamos

    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.prestado = false;
        this.vecesPrestado = 0;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public boolean isPrestado() { 
        return this.prestado;
    }

    public void setPrestado(boolean prestado) { 
        this.prestado = prestado;
        if (prestado) {
            this.vecesPrestado++;  // Aumenta el contador cuando se presta el libro
        }
    }

    public int getVecesPrestado() { 
        return this.vecesPrestado;
    }
}
