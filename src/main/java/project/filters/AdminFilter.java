package project.filters;

import project.models.users.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* фільтр до сторінок для сіс адмінів*/
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        int role = 0;
        if (httpServlet.getSession().getAttribute("user") != null)
            role = ((User) httpServlet.getSession().getAttribute("user")).getRolesId();
        if (role != 1) {
            httpServletResponse.sendRedirect("../index.jsp");
        }else filterChain.doFilter(servletRequest, servletResponse);
    }

}
