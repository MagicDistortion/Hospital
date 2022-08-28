package project.servlets;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import project.controller.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User oldUser = (User) req.getSession().getAttribute("user");
        User user = new User(req.getParameter("surname")
                , req.getParameter("name")
                , req.getParameter("login")
                , req.getParameter("password")
                , req.getParameter("tel"), LocalDate.parse(req.getParameter("date_of_birth")
                , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.setId(oldUser.getId());
        Phonenumber.PhoneNumber tel = null;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        if (!user.getTel().equals(oldUser.getTel())) {
            try {
                tel = phoneUtil.parse(user.getTel(), "UA");
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
            assert tel != null;
            String finalTel = tel.getCountryCode() + "" + tel.getNationalNumber();
            if (dbManager.findAllUsers().stream().noneMatch(x -> x.getTel().equals(finalTel)))
                dbManager.updateUserTel(finalTel, user.getId());
            req.setAttribute("mes", "changes saved");
            req.getSession().setAttribute("tel", user.getTel());
            oldUser.setTel(user.getTel());
        }
        if (!user.getSurname().equals(oldUser.getSurname())) {
            dbManager.updateUseSurname(user.getSurname(), user.getId());
            req.setAttribute("mes", "changes saved");
            req.getSession().setAttribute("surname", user.getSurname());
            oldUser.setSurname(user.getSurname());
        }
        if (!user.getName().equals(oldUser.getName())) {
            dbManager.updateUserName(user.getName(), user.getId());
            req.setAttribute("mes", "changes saved");
            req.getSession().setAttribute("name", user.getName());
            oldUser.setName(user.getName());
        }
        if (!user.getLogin().equals(oldUser.getLogin())) {
            if (dbManager.findUserByLogin(user.getLogin()) == null) {
                dbManager.updateUserLogin(user.getLogin(), user.getId());
                req.setAttribute("mes", "changes saved");
                req.getSession().setAttribute("login", user.getLogin());
                oldUser.setLogin(user.getLogin());
            } else
                req.setAttribute("meserror", "The login is already in use");
        }
        if (!String.valueOf(user.getPassword().hashCode()).equals(oldUser.getPassword())
                && !user.getPassword().isEmpty()) {
            if (user.getPassword().equals(req.getParameter("repassword"))
                    && user.getPassword().length() > 4) {
                dbManager.updateUserPassword(user.getPassword(), user.getId());
                req.getSession().setAttribute("password", user.getPassword());
                req.setAttribute("mes", "changes saved");
                oldUser.setPassword(user.getPassword());
            } else
                req.setAttribute("meserror", "The password is wrong");
        }
        if (!user.getDateOfBirth().equals(oldUser.getDateOfBirth())) {
            if (!user.getDateOfBirth().isBefore(LocalDate.of(1900, 1, 1))
                    && !user.getDateOfBirth().isAfter(LocalDate.now())) {
                dbManager.updateUserDateOfBirth(user.getDateOfBirth(), user.getId());
                req.getSession().setAttribute("date_of_birth", user.getDateOfBirth());
                req.setAttribute("mes", "changes saved");
                oldUser.setDateOfBirth(user.getDateOfBirth());
            } else
                req.setAttribute("meserror", "date is wrong");
        }
        req.getRequestDispatcher("edit_profile.jsp").forward(req, resp);
    }
}
