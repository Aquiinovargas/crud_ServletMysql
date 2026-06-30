package control;

import dao.DaoAlumno;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Alumno;
import modelos.Alumnos;

@WebServlet(name = "AlumnoServlet", urlPatterns = {"/AlumnoServlet"})
public class AlumnoServlet extends HttpServlet {

    private DaoAlumno dao = new DaoAlumno();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        Alumno edit = null;
        Alumnos lista;

        try {

            String accion = request.getParameter("accion");

            if (accion == null) {
                accion = "";
            }

            if ("Agregar".equals(accion)) {

                Alumno alumno = new Alumno();

                alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                alumno.setNombre(request.getParameter("tfNombre"));
                alumno.setApPaterno(request.getParameter("tfPaterno"));
                alumno.setApMaterno(request.getParameter("tfMaterno"));
                alumno.setDDI(Double.parseDouble(request.getParameter("tfDDI")));
                alumno.setDWI(Double.parseDouble(request.getParameter("tfDWI")));
                alumno.setECBD(Double.parseDouble(request.getParameter("tfECBD")));

                boolean ok = dao.agregar(alumno);
                if (!ok) {
                    request.setAttribute("error", dao.getUltimoError());
                }

            } else if ("Editar".equals(accion)) {

                edit = dao.buscar(Integer.parseInt(request.getParameter("tfNL")));

                request.setAttribute("edit", edit);

            } else if ("Modificar".equals(accion)) {

                Alumno alumno = new Alumno();

                alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                alumno.setNombre(request.getParameter("tfNombre"));
                alumno.setApPaterno(request.getParameter("tfPaterno"));
                alumno.setApMaterno(request.getParameter("tfMaterno"));
                alumno.setDDI(Double.parseDouble(request.getParameter("tfDDI")));
                alumno.setDWI(Double.parseDouble(request.getParameter("tfDWI")));
                alumno.setECBD(Double.parseDouble(request.getParameter("tfECBD")));

                boolean ok = dao.modificar(Integer.parseInt(request.getParameter("tfNLold")), alumno);
                if (!ok) {
                    request.setAttribute("error", dao.getUltimoError());
                }

                edit = null;

            } else if ("Eliminar".equals(accion)) {

                dao.eliminar(Integer.parseInt(request.getParameter("tfNL")));

            }

            lista = dao.listar();
            if (request.getAttribute("error") == null && dao.getUltimoError() != null) {
                request.setAttribute("error", dao.getUltimoError());
            }

            request.setAttribute("lista", lista);
            request.setAttribute("edit", edit);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Alumnos.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {

            request.setAttribute("error", ex.toString());
            request.setAttribute("edit", null);

            try {
                request.setAttribute("lista", dao.listar());
            } catch (Exception ignored) {
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Alumnos.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Alumno Servlet";
    }
}