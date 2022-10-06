package project.servlets.doctors_only;

import project.Paginator;
import project.models.hospitalcard.HospitalCard;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors_only/hospital_cards")
public class HospitalCards extends HttpServlet {
    private final Paginator paginator = new Paginator();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HospitalCard> hospitalCards = paginator.paginationHospitalCards(req);
        req.getRequestDispatcher("/doctors_only/hospital_cards.jsp").forward(req, resp);
    }
}
