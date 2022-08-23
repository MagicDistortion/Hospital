package project.servlets.admins_only;

import project.methods.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admins_only/give_a_role")
public class GiveARole extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int role = Integer.parseInt(req.getParameter("role"));
        int id = Integer.parseInt(req.getParameter("id"));
        User user= dbManager.findUserByID(id);
       switch (role) {
           case 1 : dbManager.insertSysAdmin(user);
               break;
           case 2 : dbManager.insertDoctor(user);
               break;
           case 3 : dbManager.insertNurse(user);
               break;
           case 4 : dbManager.insertPatient(user);
       }
       req.getRequestDispatcher("/admins_only/users_list").forward(req,resp);
    }
}