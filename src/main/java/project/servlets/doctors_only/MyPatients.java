package project.servlets.doctors_only;

import project.controller.DBManager;
import project.users.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors_only/my_patients")
public class MyPatients extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        String sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "surname":
                sort = "WHERE current_doctor_id ="+id+" ORDER BY surname";
                break;
            case "date":
                sort = "WHERE current_doctor_id ="+id+" ORDER BY date_of_birth";
        }
        List<Patient> patients = dbManager.findAllPatients(sort);
        req.setAttribute("patients", patients);

        int pagination;
        if (Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);

        int pages = patients.size() / pagination;
        if (patients.size() % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);

        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Patient> patientList = dbManager.findPatientsWithLimit(sort, page, pagination);
        req.setAttribute("patientlist", patientList);
        if (patientList.size()==0) req.setAttribute("mes", req.getSession().getAttribute("langEmpty"));

        req.getRequestDispatcher("/doctors_only/my_patients.jsp").forward(req, resp);
    }
}
