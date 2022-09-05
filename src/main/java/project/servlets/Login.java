package project.servlets;

import project.controller.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/login")
public class Login extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();
    String login;
    String password;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        password = req.getParameter("password");
        User user = dbManager.findUserByLogin(login);
        Map<?, ?> phrases = (Map<?, ?>) req.getAttribute("phrases");
        if (user != null && user.getPassword().equals(String.valueOf(password.hashCode()))) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        }
        if (user == null) {

            req.setAttribute("mes", " Login -> " + login +" "+ phrases.get("langNotFound"));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        if (user != null && !user.getPassword().equals(String.valueOf(password.hashCode()))) {
            req.setAttribute("mes", phrases.get("langWrongPassword"));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
