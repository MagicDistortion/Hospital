package project.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LogoutTest {
    private final static String path="index.jsp";

    @Test
    void doGet() throws ServletException, IOException {
        final Logout  servlet = new Logout();
        HttpSession session = mock(HttpSession.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getSession()).thenReturn(session);
        servlet.doGet(request,response);

        verify(response,times(1)).sendRedirect(path);
        verify(request,times(1)).getSession();
    }
}