package project.servlets.doctors_only;

import org.junit.jupiter.api.Test;
import project.servlets.doctors_only.GetAppointmentDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetAppointmentDetailsTest {
    private final static String path = "/doctors_only/edit_hospital_cards.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final GetAppointmentDetails servlet = new GetAppointmentDetails();

        final HttpSession session = mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id_card")).thenReturn(33);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request,times(2)).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }
}