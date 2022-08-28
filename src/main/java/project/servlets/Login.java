package project.servlets;

import project.controller.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        if (user != null && user.getPassword().equals(String.valueOf(password.hashCode()))) {
            req.getSession().setAttribute("surname", user.getSurname());
            req.getSession().setAttribute("name", user.getName());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("tel", user.getTel());
            req.getSession().setAttribute("date_of_birth", user.getDateOfBirth());
            req.getSession().setAttribute("password", user.getPassword());
            req.getSession().setAttribute("role", user.getRolesId());
            req.getSession().setAttribute("id", user.getId());
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        }
        if (user == null) {
            req.setAttribute("mes", " Login -> " + login + req.getSession().getAttribute("langNotFound"));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        if (user != null && !user.getPassword().equals(String.valueOf(password.hashCode()))) {
            req.setAttribute("mes", req.getSession().getAttribute("langWrongPassword"));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
