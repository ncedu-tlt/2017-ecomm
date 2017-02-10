package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.ProfileService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "ProfileChangeServlet", urlPatterns = {"/profileChange"})
public class ProfileChangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkOnEmpty(req)) {
            initAttributesSuccessChange(req, resp);
        } else {
            initAttributesNothingChange(req, resp);
        }
    }

    private void initAttributesNothingChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("answer", "Nothing to change."); //TODO: в JSP
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    private void initAttributesSuccessChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req, resp);
        changeProfile(userId, req);
        req.setAttribute("answer", "Profile was changed."); //TODO: в JSP
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    private boolean checkOnEmpty(HttpServletRequest req) {
        List<String> userParameters = getUserParameters(req);
        for (String parameter : userParameters) {
            if (!parameter.trim().isEmpty())
                return true;
        }
        return false;
    }

    private List<String> getUserParameters(HttpServletRequest req) {
        List<String> userParameters = new ArrayList<>();
        userParameters.add(req.getParameter("firstName"));
        userParameters.add(req.getParameter("lastName"));
        userParameters.add(req.getParameter("email"));
        userParameters.add(req.getParameter("password"));

        return userParameters;
    }

    private void changeProfile(long userId, HttpServletRequest req) {
        ProfileService profile = getProfileForChange(req, userId);

        User userProfile = profile.getUserProfile();

        userProfile = profile.changeProfile(userProfile);

        getDAOFactory().getUserDAO().updateUser(userProfile);
    }

    private ProfileService getProfileForChange(HttpServletRequest req, long userId) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        return new ProfileService(firstName, lastName, email, password, userId);
    }
}

