package modelos;

import java.util.ArrayList;

/**
 *
 * @author aquinojr
 */
public class Usuarios {

    private ArrayList<Usuario> item;

    public Usuarios() {
        item = new ArrayList<>();
    }

    public void add(Usuario u) {
        item.add(u);
    }

    public Usuario get(int posicion) {
        return item.get(posicion);
    }

    public int getSize() {
        return item.size();
    }

    public Usuario buscar(int idUsuario) {
        for (Usuario u : item) {
            if (u.getIdUsuario() == idUsuario) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Usuario> getList() {
        return item;
    }

    public String mostrar() {

        String r = "<table border='0'>"
                + "<caption>Listado de Usuarios</caption>"
                + "<thead>"
                + "<tr>"
                + "<th>ID</th>"
                + "<th>Nombre</th>"
                + "<th>Paterno</th>"
                + "<th>Materno</th>"
                + "<th>Correo</th>"
                + "<th>Estado</th>"
                + "<th colspan='2'>Opciones</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";

        for (Usuario u : getList()) {

            String accionEstado = "activo".equals(u.getEstado()) ? "Desactivar" : "Activar";

            r += "<tr>"
                    + "<td>" + u.getIdUsuario() + "</td>"
                    + "<td>" + u.getNombre() + "</td>"
                    + "<td>" + u.getApPaterno() + "</td>"
                    + "<td>" + u.getApMaterno() + "</td>"
                    + "<td>" + u.getCorreo() + "</td>"
                    + "<td>" + u.getEstado() + "</td>"

                    + "<td>"
                    + "<form method='post' action='UsuarioServlet'>"
                    + "<input type='hidden' name='accion' value='Editar'>"
                    + "<input type='hidden' name='tfId' value='" + u.getIdUsuario() + "'>"
                    + "<button type='submit'>Editar</button>"
                    + "</form>"
                    + "</td>"

                    + "<td>"
                    + "<form method='post' action='UsuarioServlet'>"
                    + "<input type='hidden' name='accion' value='CambiarEstado'>"
                    + "<input type='hidden' name='tfId' value='" + u.getIdUsuario() + "'>"
                    + "<button type='submit'>" + accionEstado + "</button>"
                    + "</form>"
                    + "</td>"

                    + "</tr>";

        }

        r += "</tbody></table>";

        return r;

    }

}
