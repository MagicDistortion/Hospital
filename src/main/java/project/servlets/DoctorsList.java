package project.servlets;

import project.methods.DBManager;
import project.users.Doctor;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors_sortlist")
public class DoctorsList extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "name":
                sort = "ORDER BY users.name";
                break;
            case "category":
                sort = "ORDER BY category_id";
                break;
            case "patients":
                sort = "ORDER BY number_of_patients DESC";
        }
        List<Doctor> allDoctores = dbManager.findAllDoctores(sort);
        req.setAttribute("allDoctores", allDoctores);

        int pagination;
        if (Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);

        int pages = allDoctores.size() / pagination;
        if (allDoctores.size() % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);

        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Doctor> doctores = dbManager.findDoctoresWithLimit(sort, page, pagination);
        req.setAttribute("docList", doctores);
        req.getRequestDispatcher("doctors.jsp").forward(req, resp);
    }
}
