package project.servlets;

import org.junit.jupiter.api.Test;
import project.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class LoginTest extends BaseTest {
    private final static String path = "index.jsp";

    @Test
    void doPost() throws ServletException, IOException {
        final Login servlet = new Login();

        final HttpSession session = mock((HttpSession.class));
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("Doctor");
        when(request.getParameter("password")).thenReturn("9582");

        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, times(1)).getSession();
        verify(dispatcher,never()).forward(request, response);
    }
    @Test
    void doPostWrongLogin() throws ServletException, IOException {
        final Login servlet = new Login();
        Map<?,?> map =new HashMap<>();

        final HttpSession session = mock((HttpSession.class));
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("login")).thenReturn("orox");
        when(request.getParameter("password")).thenReturn("9582");
        when(request.getAttribute("phrases")).thenReturn(map);

        servlet.doPost(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher,times(1)).forward(request, response);
    }
}