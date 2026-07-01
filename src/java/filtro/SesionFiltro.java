package filtro;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Bloquea el acceso a AlumnoServlet y a las acciones de administracion de
 * UsuarioServlet (listar, editar, modificar, cambiar estado) si no hay
 * sesion iniciada. Las acciones "Login" y "Registrar" siempre se permiten,
 * porque son las que usa alguien que TODAVIA no tiene sesion.
 */
@WebFilter(filterName = "SesionFiltro", urlPatterns = {"/AlumnoServlet", "/UsuarioServlet"})
public class SesionFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String accion = req.getParameter("accion");

        boolean esAccionPublica = "Login".equals(accion) || "Registrar".equals(accion);

        HttpSession session = req.getSession(false);
        boolean logueado = session != null && session.getAttribute("usuario") != null;

        if (logueado || esAccionPublica) {

            chain.doFilter(request, response);

        } else {

            resp.sendRedirect(req.getContextPath() + "/Login.jsp");

        }

    }

    @Override
    public void destroy() {
    }

}
