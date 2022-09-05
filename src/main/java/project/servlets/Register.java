package project.servlets;

import project.controller.DBManager;
import project.users.User;
import project.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/register")
public class Register extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = Validator.registerValidate(req);
        User user = new User(req.getParameter("surname")
                , req.getParameter("name")
                , req.getParameter("login")
                , req.getParameter("password")
                , Validator.finalTel(req.getParameter("tel"))
                , LocalDate.parse(req.getParameter("date_of_birth")));
        if (errors.isEmpty()) {
            dbManager.insertUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        }else  {
            req.setAttribute("user",user);
            req.setAttribute("tel",req.getParameter("tel"));
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("register_form.jsp").forward(req, resp);
        }
    }
}



