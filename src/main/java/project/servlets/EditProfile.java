package project.servlets;

import project.dao.UsersDAO;
import project.models.users.User;
import project.validator.ValidatorEditForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {
    private final UsersDAO usersDAO = new UsersDAO();
    private final ValidatorEditForm validatorEditForm = new ValidatorEditForm();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = validatorEditForm.editValidate(req);
        User user = usersDAO.findUserByID(((User) req.getSession().getAttribute("user")).getId());
        req.getSession().setAttribute("user", user);
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("edit_profile.jsp").forward(req, resp);
    }
}
