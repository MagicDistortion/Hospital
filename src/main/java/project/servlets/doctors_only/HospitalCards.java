package project.servlets.doctors_only;

import project.hospitalcard.HospitalCard;
import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors_only/hospital_cards")
public class HospitalCards extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HospitalCard> hospitalCardList = dbManager.getAllHospitalCards();
        req.setAttribute("hospital_cards", hospitalCardList);

        int pagination;
        if (req.getParameter("pagination")==null|| Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);

        int pages = hospitalCardList.size() / pagination;
        if (hospitalCardList.size() % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);

        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<HospitalCard> cards = dbManager.getHospitalCardsWithLimit(page, pagination);
        req.setAttribute("cards", cards);

        req.getRequestDispatcher("/doctors_only/hospital_cards.jsp").forward(req, resp);
    }

}
