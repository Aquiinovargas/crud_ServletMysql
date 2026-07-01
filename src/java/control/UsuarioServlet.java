package control;

import dao.DaoUsuario;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import modelos.Usuarios;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private DaoUsuario dao = new DaoUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        Usuario edit = null;
        Usuarios lista;

        try {

            String accion = request.getParameter("accion");

            if (accion == null) {
                accion = "";
            }

            if ("Registrar".equals(accion)) {

                String correo = request.getParameter("tfCorreo");

                if (dao.correoExiste(correo)) {

                    request.setAttribute("error", "Ese correo ya esta registrado");

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Registro.jsp");
                    rd.forward(request, response);
                    return;

                } else {

                    Usuario usuario = new Usuario();
                    usuario.setNombre(request.getParameter("tfNombre"));
                    usuario.setApPaterno(request.getParameter("tfPaterno"));
                    usuario.setApMaterno(request.getParameter("tfMaterno"));
                    usuario.setCorreo(correo);
                    usuario.setPassword(request.getParameter("tfPassword"));

                    boolean ok = dao.registrar(usuario);

                    if (!ok) {

                        request.setAttribute("error", dao.getUltimoError());

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Registro.jsp");
                        rd.forward(request, response);
                        return;

                    }

                    response.sendRedirect("Login.jsp?registrado=1");
                    return;

                }

            } else if ("Editar".equals(accion)) {

                edit = dao.buscar(Integer.parseInt(request.getParameter("tfId")));
                request.setAttribute("edit", edit);

            } else if ("Modificar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("tfId"));

                Usuario usuario = new Usuario();
                usuario.setNombre(request.getParameter("tfNombre"));
                usuario.setApPaterno(request.getParameter("tfPaterno"));
                usuario.setApMaterno(request.getParameter("tfMaterno"));
                usuario.setCorreo(request.getParameter("tfCorreo"));

                boolean ok = dao.modificar(id, usuario);
                if (!ok) {
                    request.setAttribute("error", dao.getUltimoError());
                }

                edit = null;

            } else if ("CambiarEstado".equals(accion)) {

                dao.cambiarEstado(Integer.parseInt(request.getParameter("tfId")));

            } else if ("Login".equals(accion)) {

                String correo = request.getParameter("tfCorreo");
                String password = request.getParameter("tfPassword");

                Usuario u = dao.autenticar(correo, password);

                if (u != null) {

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", u);

                    response.sendRedirect("AlumnoServlet");
                    return;

                } else {

                    request.setAttribute("error", "Correo, contraseña o cuenta inactiva");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
                    rd.forward(request, response);
                    return;

                }

            } else if ("Logout".equals(accion)) {

                request.getSession().invalidate();
                response.sendRedirect("Login.jsp");
                return;

            }

            lista = dao.listar();
            if (request.getAttribute("error") == null && dao.getUltimoError() != null) {
                request.setAttribute("error", dao.getUltimoError());
            }

            request.setAttribute("lista", lista);
            request.setAttribute("edit", edit);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {

            request.setAttribute("error", ex.toString());
            request.setAttribute("edit", null);

            try {
                request.setAttribute("lista", dao.listar());
            } catch (Exception ignored) {
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios.jsp");
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
        return "Usuario Servlet";
    }
}
