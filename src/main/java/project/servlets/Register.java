package project.servlets;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import project.methods.DBManager;
import project.users.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/register")
public class Register extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Phonenumber.PhoneNumber tel = null;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            tel = phoneUtil.parse(req.getParameter("tel"), "UA");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        if (dbManager.findUserByLogin(req.getParameter("login")) != null) {
            req.setAttribute("mes", " The login is already in use");
            req.getRequestDispatcher("register_form.jsp").forward(req, resp);
        } else if (req.getParameter("password").length() < 4) {
            req.setAttribute("mes", " password is so small");
            req.getRequestDispatcher("register_form.jsp").forward(req, resp);
        } else if (!req.getParameter("password").equals(req.getParameter("repassword"))) {
            req.setAttribute("mes", " passwords not a same");
            req.getRequestDispatcher("register_form.jsp").forward(req, resp);
        } else {
            assert tel != null;
            String finalTel = tel.getCountryCode() + "" + tel.getNationalNumber();
            if (finalTel.length() != 12) {
                req.setAttribute("mes", "phone number is wrong");
                req.getRequestDispatcher("register_form.jsp").forward(req, resp);
            } else if (dbManager.findAllUsers().stream().anyMatch(x -> x.getTel().equals(finalTel))) {
                req.setAttribute("mes", "phone number is already in use");
                req.getRequestDispatcher("register_form.jsp").forward(req, resp);
            } else if (LocalDate.parse(req.getParameter("date_of_birth")
                    , DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(LocalDate.of(1900, 1, 1))
                    || LocalDate.parse(req.getParameter("date_of_birth")
                    , DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(LocalDate.now())) {
                req.setAttribute("mes", "date is wrong");
                req.getRequestDispatcher("register_form.jsp").forward(req, resp);
            } else {
                dbManager.insertUser(new User(req.getParameter("surname")
                        ,req.getParameter("name")
                        , req.getParameter("login")
                        , req.getParameter("password")
                        , finalTel
                        , LocalDate.parse(req.getParameter("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

                User user = dbManager.findUserByLogin(req.getParameter("login"));
                req.getSession().setAttribute("surname", user.getName());
                req.getSession().setAttribute("name", user.getName());
                req.getSession().setAttribute("login", user.getLogin());
                req.getSession().setAttribute("tel", user.getTel());
                req.getSession().setAttribute("date_of_birth", user.getDateOfBirth());
                req.getSession().setAttribute("password", user.getPassword());
                req.getSession().setAttribute("role", user.getRolesId());
                req.getSession().setAttribute("id", user.getId());
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}


