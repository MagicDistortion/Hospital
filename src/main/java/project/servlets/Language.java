package project.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/lang")
public class Language extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String replace = req.getHeader("referer").replace("http://localhost:8080/Hospital/", "");
        req.getSession().setAttribute("lang", req.getParameter("lang"));
        resp.sendRedirect(replace);
    }
}
