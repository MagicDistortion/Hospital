package project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        int role = 0;
        if (httpServlet.getSession().getAttribute("role") != null) role = (int) httpServlet.getSession().getAttribute("role");
        if (role != 1) httpServletResponse.sendRedirect("../index.jsp");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
