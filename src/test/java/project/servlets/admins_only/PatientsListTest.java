package project.servlets.admins_only;

import org.junit.jupiter.api.Test;
import project.servlets.DoctorsList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PatientsListTest {
    private final static String pathGet = "/admins_only/patients.jsp";
    private final static String pathPost = "/admins_only/appoint_a_doctor.jsp";
    @Test
    void doGet() throws ServletException, IOException {
        final PatientsList servlet = new PatientsList();

        final HttpSession session = mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(pathGet)).thenReturn(dispatcher);
        when(request.getParameter("sort")).thenReturn("surname");
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(pathGet);
        verify(request, never()).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }

    @Test
    void doPost() throws ServletException, IOException {
        final PatientsList servlet = new PatientsList();

        final HttpSession session = mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(pathPost)).thenReturn(dispatcher);
        when(request.getParameter("sort")).thenReturn("surname");
        when(request.getSession()).thenReturn(session);

        servlet.doPost(request, response);

        verify(response, never()).sendRedirect(pathPost);
        verify(dispatcher,times(1)).forward(request, response);
    }
}