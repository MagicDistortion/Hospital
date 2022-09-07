package project.servlets.admins_only;

import project.categories.Categories;
import project.dao.CategoriesDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/add_category")
public class AddCategory extends HttpServlet {
    private final CategoriesDAO categoriesDAO = new CategoriesDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        if (categoriesDAO.findCategory(category) != null) {
            req.setAttribute("mes", "category already exists");
        } else {
            categoriesDAO.insertCategory(new Categories(category));
            req.setAttribute("mes", "category added");
        }
        req.getRequestDispatcher("/admins_only/add_category.jsp").forward(req, resp);
    }
}
