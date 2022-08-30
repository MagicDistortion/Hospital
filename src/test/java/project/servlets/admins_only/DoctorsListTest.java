package project.servlets.admins_only;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class DoctorsListTest {
    private final static String path = "/admins_only/giving_a_category.jsp";
    @Test
    void doGet() throws ServletException, IOException {
        final DoctorsList servlet = new DoctorsList();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(path);
        verify(dispatcher,times(1)).forward(request, response);
    }
}