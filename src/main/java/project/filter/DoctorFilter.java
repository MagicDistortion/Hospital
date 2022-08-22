package project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DoctorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        int role = 0;
        if (httpServlet.getSession().getAttribute("role") != null) role = (int) httpServlet.getSession().getAttribute("role");
        if (role != 2) servletRequest.getRequestDispatcher("../before_index.jsp").forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
