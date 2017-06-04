
package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.PropertyDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.PropertyDAOObjectBuilder;
import ru.ncedu.ecomm.servlets.models.EnumRoles;
import ru.ncedu.ecomm.servlets.services.UserService;
import ru.ncedu.ecomm.utils.RedirectUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "PropertyServlet", urlPatterns = {"/properties"})
public class PropertyServlet extends HttpServlet {
    private static final String THREE_WIDE_COLUMN = "three wide column jsPropertId jsProperty";
    private static final String THIRTEEN_WIDE_COLUMN = "thirteen wide column jsPropertVal jsProperty";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null)
            doAction(request, response);
        else
            browseProperty(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action") != null)
            doAction(request, response);
        else
            browseProperty(request, response);
    }

    private void browseProperty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean userInSystem = UserService.getInstance().isUserAuthorized(request);
        if (!userInSystem) {
            request.getRequestDispatcher(Configuration.getProperty("page.login")).forward(request, response);
        }

        Boolean userAreAdministrator = UserService.getInstance().redirectIfNotAllowed(request, EnumRoles.ADMINISTRATOR.getRole());
        if (!userAreAdministrator)
            RedirectUtil.redirectToPage(request,response,"/home");


        List<PropertyDAOObject> properties = DAOFactory.getDAOFactory().getPropertyDAO().getProperties();
        String propertiesURL = request.getContextPath() + Configuration.getProperty("servlet.properties");

        request.setAttribute("propertiesURL", propertiesURL);
        request.setAttribute("properties", properties);
        request.getRequestDispatcher(Configuration.getProperty("page.property")).forward(request, response);
    }

    void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("action")) {
            case "add": {
                addNewPropertyToDAO(request, response);
                break;
            }
            case "remove": {
                removePropertyFromDAO(request, response);
                break;
            }
            case "save": {
                updateValueInDAO(request, response);
                break;
            }
            case "edit": {
                editProperty(request, response);
                break;
            }
            case "saveRow": {
                saveRow(request, response);
                break;
            }
        }

    }


    private void updateValueInDAO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String field = request.getParameter("field");

        PropertyDAOObject property = new PropertyDAOObjectBuilder()
                .setPropertyId(request.getParameter("propertyId"))
                .setValue(request.getParameter("valueText"))
                .build();

        if (Objects.equals(field,THREE_WIDE_COLUMN)) {
            DAOFactory.getDAOFactory().getPropertyDAO().updateIdProperty(property);
        }
        if (Objects.equals(field,THIRTEEN_WIDE_COLUMN )) {
            DAOFactory.getDAOFactory().getPropertyDAO().updateValueProperty(property);
        }

        request.setAttribute("property", property);
        request.setAttribute("field", field);
        request.getRequestDispatcher(Configuration.getProperty("page.showChangeValue")).forward(request, response);

    }

    private void removePropertyFromDAO(HttpServletRequest request, HttpServletResponse response) {

        PropertyDAOObject property = new PropertyDAOObjectBuilder()
                .setPropertyId(request.getParameter("propertyId"))
                .setValue(request.getParameter("valueText"))
                .build();

        DAOFactory.getDAOFactory().getPropertyDAO().deleteProperty(property);
    }


    private void addNewPropertyToDAO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Configuration.getProperty("page.addProperty")).forward(request, response);
    }


    private void editProperty(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String propertyVal = request.getParameter("valueText");

        String field = request.getParameter("field");
        PropertyDAOObject property = new PropertyDAOObjectBuilder()
                .setPropertyId(request.getParameter("propertyId"))
                .setValue(propertyVal)
                .build();

        boolean setTextArea = propertyVal.length() > 100;

        request.setAttribute("property", property);
        request.setAttribute("field", field);
        request.setAttribute("setTextArea", setTextArea);
        request.getRequestDispatcher(Configuration.getProperty("page.editProperty")).forward(request, response);
    }

    private void saveRow(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PropertyDAOObject property = new PropertyDAOObjectBuilder()
                .setPropertyId(request.getParameter("propertyId"))
                .setValue(request.getParameter("valueText"))
                .build();

        request.setAttribute("property", property);
        DAOFactory.getDAOFactory().getPropertyDAO().addProperty(property);
        request.getRequestDispatcher(Configuration.getProperty("page.showNewProperty")).forward(request, response);
    }


}
