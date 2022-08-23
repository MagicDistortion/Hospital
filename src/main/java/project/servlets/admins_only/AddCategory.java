package project.servlets.admins_only;

import project.categories.Categories;
import project.methods.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admins_only/add_category")
public class AddCategory extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        if (dbManager.findCategory(category) != null) {
            req.setAttribute("mes", "category already exists");
        } else req.setAttribute("mes", "category added");
        dbManager.insertCategory(new Categories(category));
        req.getRequestDispatcher("/admins_only/add_category.jsp").forward(req, resp);


    }
}