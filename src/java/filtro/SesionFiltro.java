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
 * Bloquea el acceso a AlumnoServlet si no hay sesion iniciada.
 * Manda a Login.jsp en caso de que no exista "usuario" en sesion.
 */
@WebFilter(filterName = "SesionFiltro", urlPatterns = {"/AlumnoServlet"})
public class SesionFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {

            chain.doFilter(request, response);

        } else {

            resp.sendRedirect(req.getContextPath() + "/Login.jsp");

        }

    }

    @Override
    public void destroy() {
    }

}
