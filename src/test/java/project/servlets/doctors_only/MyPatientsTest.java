package project.servlets.doctors_only;

import org.junit.jupiter.api.Test;
import project.servlets.doctors_only.MyPatients;
import project.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MyPatientsTest {
    private final static String path = "/doctors_only/my_patients.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final MyPatients servlet = new MyPatients();

        final HttpSession session =mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        User user = new User("test","test","test","9595","6688882211", LocalDate.now());
        user.setId(28);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("sort")).thenReturn("surname");

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request,times(1)).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }
}