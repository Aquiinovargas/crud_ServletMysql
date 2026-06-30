package modelos;

/**
 *
 * @author aquinojr
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private String password;
    private String estado;

    public Usuario() {
    }

    public Usuario(String nombre, String apPaterno, String apMaterno, String correo, String password) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.password = password;
        this.estado = "activo";
    }

    public Usuario(int idUsuario, String nombre, String apPaterno, String apMaterno, String correo, String password, String estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCompleto() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }

}
