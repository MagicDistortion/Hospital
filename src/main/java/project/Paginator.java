package project;

import project.dao.DoctorsDAO;
import project.dao.HospitalCardDAO;
import project.dao.PatientsDAO;
import project.models.hospitalcard.HospitalCard;
import project.models.users.Doctor;
import project.models.users.Patient;
import project.models.users.User;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/* Клас пагінатор*/
public class Paginator {
    private final PatientsDAO patientsDAO = new PatientsDAO();
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();
    /* метод повертає у сервлет лист пацієнтів за id лікаря*/
    public List<Patient> paginationMyPatients(HttpServletRequest req) {
        int id = ((User)req.getSession().getAttribute("user")).getId();
        String sort = "surname";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "surname":
                sort = "WHERE current_doctor_id ="+id+" ORDER BY surname";
                break;
            case "date":
                sort = "WHERE current_doctor_id ="+id+" ORDER BY date_of_birth";
        }
        int patientsCount= patientsDAO.patientsCountForDoctor(id);
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, patientsCount, pagination);
        return patientsDAO.findPatientsWithLimit(sort, page, pagination);
    }
    /* метод повертає у сервлет лист Лікарняних карт*/
    public List<HospitalCard> paginationHospitalCards(HttpServletRequest req) {
        int hospitalCardsCount = hospitalCardDAO.hospitalCardsCount();
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, hospitalCardsCount, pagination);
        return hospitalCardDAO.getHospitalCardsWithLimit(page, pagination);
    }
    /* метод повертає у сервлет лист пацієнтів*/
    public List<Patient> paginationPatientsList(HttpServletRequest req) {
        String sort = "surname";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "surname":
                sort = "WHERE `status`!='discharged' ORDER BY surname";
                break;
            case "date":
                sort = "WHERE `status`!='discharged' ORDER BY date_of_birth";
        }
        int patientsCount = patientsDAO.patientsCount();
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, patientsCount, pagination);
        return patientsDAO.findPatientsWithLimit(sort, page, pagination);
    }
    /* метод повертає у сервлет лист лікарів*/
    public List<Doctor> paginationDoctorsList(HttpServletRequest req) {
        String sort = "surname";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
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
        int doctorsCount = doctorsDAO.doctorsCount();
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, doctorsCount, pagination);
        return doctorsDAO.findDoctorsWithLimit(sort, page, pagination);
    }
    /* метод повертає номер сторінки, та передає його у запит*/
    private int getPage(HttpServletRequest req) {
        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        req.setAttribute("page", page);
        return page;
    }
    /* метод повертає кількість відповідей які отримає користувач на одній сторінці,також передає у запит */
    private int getPagination(HttpServletRequest req) {
        int pagination;
        if (req.getParameter("pagination") == null || Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);
        return pagination;
    }
    /* метод повертає загальну кількість сторінок, також передає її у запит*/
    private void getPages(HttpServletRequest req, int size, int pagination) {
        int pages = size / pagination;
        if (size % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);
    }
}

