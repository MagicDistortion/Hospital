package project.servlets.patients_only;

import org.junit.jupiter.api.Test;
import project.BaseTest;
import project.models.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MyAppointmentsTest extends BaseTest {
    private final static String path = "/patients_only/my_appointments.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final MyAppointments servlet = new MyAppointments();
        Map<?,?> map =new HashMap<>();
        User user = new User("test","test","test","9595","6688882211", LocalDate.now());
        user.setId(31);

        final HttpSession session =mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getAttribute("phrases")).thenReturn(map);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request,times(1)).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }
}