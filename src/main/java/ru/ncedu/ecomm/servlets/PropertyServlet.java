package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.servlets.models.EnumRoles;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PropertyServlet", urlPatterns = {"/properties"})
public class PropertyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        browseProperty(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        browseProperty(request, response);
    }

    private void browseProperty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean userInSystem = UserService.getInstance().isUserAuthorized(request);
        if (!userInSystem){
            request.getRequestDispatcher(Configuration.getProperty("page.login")).forward(request, response);
        }


        Boolean userAreAdministrator = UserService.getInstance().redirectIfNotAllowed(request, EnumRoles.ADMINISTRATOR.getRole());
        if (!userAreAdministrator)
            request.getRequestDispatcher("/home").include(request,response);


        List<Property> properties = DAOFactory.getDAOFactory().getPropertyDAO().getProperties();



        request.setAttribute("properties", properties);
        request.getRequestDispatcher(Configuration.getProperty("page.property")).forward(request,response);
    }







}


