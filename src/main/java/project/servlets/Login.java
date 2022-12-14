package project.servlets;

import project.dao.UsersDAO;
import project.models.users.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    private final UsersDAO usersDAO=new UsersDAO();
    String login;
    String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        password = req.getParameter("password");
        User user = usersDAO.findUserByLogin(login);
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
