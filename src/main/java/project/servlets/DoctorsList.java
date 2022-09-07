package project.servlets;

import project.dao.DoctorsDAO;
import project.models.users.Doctor;

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
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();

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
        List<Doctor> allDoctors = doctorsDAO.findAllDoctors(sort);
        req.setAttribute("allDoctors", allDoctors);

        int pagination;
        if (req.getParameter("pagination") == null || Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);

        int pages = allDoctors.size() / pagination;
        if (allDoctors.size() % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);

        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        req.setAttribute("page", page);

        List<Doctor> doctors = doctorsDAO.findDoctorsWithLimit(sort, page, pagination);
        req.setAttribute("docList", doctors);
        req.getRequestDispatcher("doctors.jsp").forward(req, resp);
    }
}
