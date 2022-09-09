package project.servlets.doctors_only;

import project.dao.AppointmentDetailsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/doctors_only/update_date")
public class UpdateDate extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    int id;
    LocalDate date;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Integer.parseInt(req.getParameter("id")) != 0 && req.getParameter("date") != null) {
            id = Integer.parseInt(req.getParameter("id"));
            date = LocalDate.parse(req.getParameter("date"));
            if (date.isAfter(LocalDate.now()) || date.equals(LocalDate.now())){
                appointmentDetailsDAO.updateDateAppointment(date, id);
                resp.sendRedirect("get_overdue_appointments");
            }else {
                req.setAttribute("err_message", "wrong date");
                req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
            }
        }
    }
}

