package project.servlets.admins_only;

import project.categories.Categories;
import project.methods.DBManager;
import project.users.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admins_only/doctors_list")
public class DoctorsList extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Categories> allCategories = dbManager.findAllCategories();
        req.setAttribute("categories",allCategories);

        List<Doctor> allDoctores = dbManager.findAllDoctores("WHERE category_id = 1 ORDER BY users.name");
        req.setAttribute("docs", allDoctores);

        if (allDoctores.size()==0)req.setAttribute("mes","empty");
        req.getRequestDispatcher("/admins_only/giving_a_category.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Doctor> allDoctores = dbManager.findAllDoctores("WHERE category_id = 1 ORDER BY users.name");
        req.setAttribute("docs", allDoctores);
        if (allDoctores.size()==0)req.setAttribute("mes","empty");
        req.getRequestDispatcher("/admins_only/giving_a_category.jsp").forward(req, resp);
    }
}
