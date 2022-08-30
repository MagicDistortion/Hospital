package project.servlets.patients_only;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MyHospitalCardTest {
    private final static String path = "/patients_only/my_appointments.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final MyHospitalCard servlet = new MyHospitalCard();

        final HttpSession session =mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(87);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request,times(2)).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }
}