package project.servlets;

import project.controller.DBManager;
import project.users.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
 /* єтот сервлет возвращает список врачей */
@WebServlet("/doctors_sortlist")
public class DoctorsList extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "Surname":
                sort = "ORDER BY users.Surname";
                break;
            case "category":
                sort = "ORDER BY category_id";
                break;
            case "patients":
                sort = "ORDER BY number_of_patients DESC";
        }
        List<Doctor> allDoctores = dbManager.findAllDoctors(sort);
        req.setAttribute("allDoctores", allDoctores);

        int pagination;
        if ( req.getParameter("pagination")==null|| Integer.parseInt(req.getParameter("pagination")) <= 0) {
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
        List<Doctor> doctores = dbManager.findDoctorsWithLimit(sort, page, pagination);
        req.setAttribute("docList", doctores);
        req.getRequestDispatcher("doctors.jsp").forward(req, resp);
    }
}
