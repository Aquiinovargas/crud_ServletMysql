package modelos;

import java.util.ArrayList;

/**
 *
 * @author aquinojr
 */
public class Alumnos {

    private ArrayList<Alumno> item;

    public Alumnos() {
        item = new ArrayList<>();
    }

    public void add(Alumno a) {
        item.add(a);
    }

    public Alumno get(int posicion) {
        return item.get(posicion);
    }

    public int getSize() {
        return item.size();
    }

    public Alumno buscar(int nl) {
        for (Alumno a : item) {
            if (a.getNL() == nl) {
                return a;
            }
        }
        return null;
    }

    public Alumno remove(int nl) {
        for (Alumno a : item) {
            if (a.getNL() == nl) {
                item.remove(a);
                return a;
            }
        }
        return null;
    }

    public boolean update(int nlOld, Alumno actualizado) {

        for (int i = 0; i < item.size(); i++) {

            if (item.get(i).getNL() == nlOld) {

                item.set(i, actualizado);
                return true;

            }

        }

        return false;

    }

    public ArrayList<Alumno> getList() {
        return item;
    }

    public String mostrar() {

        String r = "<table border='0'>"
                + "<caption>Listado de Alumnos</caption>"
                + "<thead>"
                + "<tr>"
                + "<th>NL</th>"
                + "<th>Nombre</th>"
                + "<th>Paterno</th>"
                + "<th>Materno</th>"
                + "<th>DDI</th>"
                + "<th>DWI</th>"
                + "<th>ECBD</th>"
                + "<th>Promedio</th>"
                + "<th colspan='2'>Opciones</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";

        for (Alumno a : getList()) {

            r += "<tr>"
                    + "<td>" + a.getNL() + "</td>"
                    + "<td>" + a.getNombre() + "</td>"
                    + "<td>" + a.getApPaterno() + "</td>"
                    + "<td>" + a.getApMaterno() + "</td>"
                    + "<td>" + a.getDDI() + "</td>"
                    + "<td>" + a.getDWI() + "</td>"
                    + "<td>" + a.getECBD() + "</td>"
                    + "<td>" + String.format("%.2f", a.getPromedio()) + "</td>"

                    + "<td>"
                    + "<form method='post' action='AlumnoServlet'>"
                    + "<input type='hidden' name='accion' value='Editar'>"
                    + "<input type='hidden' name='tfNL' value='" + a.getNL() + "'>"
                    + "<button type='submit'>Editar</button>"
                    + "</form>"
                    + "</td>"

                    + "<td>"
                    + "<form method='post' action='AlumnoServlet'>"
                    + "<input type='hidden' name='accion' value='Eliminar'>"
                    + "<input type='hidden' name='tfNL' value='" + a.getNL() + "'>"
                    + "<button type='submit'>Eliminar</button>"
                    + "</form>"
                    + "</td>"

                    + "</tr>";

        }

        r += "</tbody></table>";

        return r;

    }

}