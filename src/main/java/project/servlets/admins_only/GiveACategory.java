package project.servlets.admins_only;

import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/give_a_category")
public class GiveACategory extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int category = Integer.parseInt(req.getParameter("category"));
        int id = Integer.parseInt(req.getParameter("id"));
        dbManager.updateDoctorCategory(category, id);
        req.getRequestDispatcher("/admins_only/doctors_list").forward(req, resp);
    }
}
