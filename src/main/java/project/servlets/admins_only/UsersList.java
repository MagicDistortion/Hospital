package project.servlets.admins_only;

import project.controller.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admins_only/users_list")
public class UsersList extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = dbManager.findUsersWitchOutRole();
        req.setAttribute("userList", allUsers);
        if (allUsers.size()==0)  req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langEmpty"));
        req.getRequestDispatcher("/admins_only/giving_a_role.jsp").forward(req, resp);
    }
}
