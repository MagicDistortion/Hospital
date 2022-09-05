package project.servlets.admins_only;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AddCategoryTest {
    private final static String path = "/admins_only/add_category.jsp";
    @Test
    void doPost() throws ServletException, IOException {
        final AddCategory servlet = new AddCategory();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("category")).thenReturn("Хірург");

        servlet.doPost(request, response);

        verify(response,never()).sendRedirect(path);
        verify(dispatcher,times(1)).forward(request, response);

    }
}