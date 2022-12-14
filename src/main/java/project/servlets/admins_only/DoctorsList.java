package project.servlets.admins_only;

import project.models.categories.Categories;
import project.dao.CategoriesDAO;
import project.dao.DoctorsDAO;
import project.models.users.Doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admins_only/doctors_list")
public class DoctorsList extends HttpServlet {
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final CategoriesDAO categoriesDAO = new CategoriesDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Categories> allCategories = categoriesDAO.findAllCategories();
        req.setAttribute("categories", allCategories);

        List<Doctor> allDoctors = doctorsDAO.findAllDoctors("WHERE category_id = 1 ORDER BY users.name");
        req.setAttribute("docs", allDoctors);

        if (allDoctors.size() == 0) req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langEmpty"));
        req.getRequestDispatcher("/admins_only/giving_a_category.jsp").forward(req, resp);
    }
}
