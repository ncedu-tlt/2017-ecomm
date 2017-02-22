package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.ProfileService;
import ru.ncedu.ecomm.servlets.services.UserService;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showProfile(req, resp);
    }

    //show profile

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectIfUserNotAuthorized(req, resp);
        createAttributeProfile(req);
        resp.sendRedirect(Configuration.getProperty("page.profile"));
    }

    private void redirectIfUserNotAuthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean userInSystem = UserService.getInstance().redirectToLoginIfNeeded(req);
        if (!userInSystem){
            req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
        }
    }

    private void createAttributeProfile(HttpServletRequest req) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        ProfileService profileService = ProfileService.getInstance();
        User userProfile = profileService.getUserProfileById(userId);
        initAttributesProfileForShow(userProfile, req);
    }

    private void initAttributesProfileForShow(User userProfile, HttpServletRequest req) {
        req.setAttribute("firstName", userProfile.getFirstName());
        req.setAttribute("lastName", userProfile.getLastName());
        req.setAttribute("email", userProfile.getEmail());
    }

    //profile change

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isEmptyInputs(req)) {
            this.doGet(req, resp);
        } else {
            initAttributesSuccessChange(req, resp);
        }
    }

    private boolean isEmptyInputs(HttpServletRequest req) {
        List<String> userParameters = getUserParameters(req);
        for (String parameter : userParameters) {
            if (parameter.trim().isEmpty())
                return true;
        }
        return false;
    }

    private void initAttributesSuccessChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        changeProfile(userId, req);
        resp.sendRedirect("/profile");
    }

    private void changeProfile(long userId, HttpServletRequest req) {
        User userByChange = getDAOFactory().getUserDAO().getUserById(userId);
        userByChange = setUserNewParameters(userByChange, req);
        getDAOFactory().getUserDAO().updateUser(userByChange);
    }

    private User setUserNewParameters(User userByChange, HttpServletRequest req) {
        userByChange.setFirstName(req.getParameter("firstName"));
        userByChange.setLastName(req.getParameter("lastName"));
        userByChange.setEmail(req.getParameter("email"));
        String newPassword = EncryptionUtils.getMd5Digest(req.getParameter("password"));
        userByChange.setPassword(newPassword);

        return userByChange;
    }

    private List<String> getUserParameters(HttpServletRequest req) {
        List<String> userParameters = new ArrayList<>();
        userParameters = addNewUserParameters(userParameters, req);
        return userParameters;

    }

    private List<String> addNewUserParameters(List<String> userParameters, HttpServletRequest req) {
        userParameters.add(req.getParameter("firstName"));
        userParameters.add(req.getParameter("lastName"));
        userParameters.add(req.getParameter("email"));
        userParameters.add(req.getParameter("password"));

        return userParameters;
    }



}
