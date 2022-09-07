package project.servlets.admins_only;

import project.dao.*;
import project.models.users.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/give_a_role")
public class GiveARole extends HttpServlet {
    private final UsersDAO usersDAO=new UsersDAO();
    private final SysAdminsDAO sysAdminsDAO = new SysAdminsDAO();
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final NursesDAO nursesDAO=new NursesDAO();
    private final PatientsDAO patientsDAO=new PatientsDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int role = Integer.parseInt(req.getParameter("role"));
        int id = Integer.parseInt(req.getParameter("id"));
        User user= usersDAO.findUserByID(id);
       switch (role) {
           case 1 : sysAdminsDAO.insertSysAdmin(user);
               break;
           case 2 : doctorsDAO.insertDoctor(user);
               break;
           case 3 : nursesDAO.insertNurse(user);
               break;
           case 4 : patientsDAO.insertPatient(user);
               break;
           default: return;
       }
       resp.sendRedirect("users_list");
    }
}
