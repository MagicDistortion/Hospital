package project.servlets;

import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

class LanguageTest {
    private final static String path="index.jsp";

    @Test
    void doPost() throws ServletException, IOException {
        final Language  servlet = new Language();
        final HttpSession session = mock(HttpSession.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getHeader("referer")).thenReturn("http://localhost:8080/Hospital/index.jsp");
        when(request.getParameter("lang")).thenReturn("UA");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request,response);

        verify(request,times(1)).getSession();
        verify(response,times(1)).sendRedirect(path);
    }
}