package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.models.EnumRoles;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ildu0217
 *         Date: 12.04.2017
 *         Time: 12:42
 */
@WebServlet(name = "ManagementServlet", urlPatterns = "/management")
public class ManagementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean userInSystem = UserService.getInstance().isUserAuthorized(request);
        if(userInSystem) {
            Boolean userAreAdministrator = UserService.getInstance().redirectIfNotAllowed(request, EnumRoles.ADMINISTRATOR.getRole());
            if (!userAreAdministrator) {
                request.getRequestDispatcher("/home").forward(request, response);
            }
            request.getRequestDispatcher("/management.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/home").forward(request, response);
    }
}
