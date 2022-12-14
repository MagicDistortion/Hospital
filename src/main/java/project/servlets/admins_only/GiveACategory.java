package project.servlets.admins_only;

import project.dao.DoctorsDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/give_a_category")
public class GiveACategory extends HttpServlet {
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int category = Integer.parseInt(req.getParameter("category"));
        int id = Integer.parseInt(req.getParameter("id"));
        doctorsDAO.updateDoctorCategory(category, id);
        resp.sendRedirect("doctors_list");
    }
}
