package project.filter;

import project.users.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/* фільтр до сторінок для лікарів*/

public class DoctorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        int role = 0;
        if (httpServlet.getSession().getAttribute("user") != null)
            role = ((User) httpServlet.getSession().getAttribute("user")).getRolesId();
        if (role != 2) httpServletResponse.sendRedirect("../index.jsp");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
