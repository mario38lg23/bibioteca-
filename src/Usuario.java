public class Usuario {
    private String nombre;
    private String contraseña;
    private Rol rol;  
    private int prestamosActivos;

    

    public Usuario(String nombre, String contraseña, Rol rol) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        this.prestamosActivos = 0;
    }
     

}
