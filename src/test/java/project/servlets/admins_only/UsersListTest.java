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

class UsersListTest {
    private final static String path = "/admins_only/giving_a_role.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final UsersList servlet = new UsersList();

        final HttpSession session = mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(dispatcher,times(1)).forward(request, response);
    }
}