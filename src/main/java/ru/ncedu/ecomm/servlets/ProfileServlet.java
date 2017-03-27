package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.UserDAOObject;
import ru.ncedu.ecomm.servlets.models.EnumRoles;
import ru.ncedu.ecomm.servlets.services.ProfileService;
import ru.ncedu.ecomm.servlets.services.UserService;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String AVATAR = "avatar";
    private static final String PASSWORD = "password";
    private static final String ANSWER = "answer";


    //show profile
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showProfile(req, resp);
        req.getRequestDispatcher(Configuration.getProperty("page.profile")).forward(req, resp);
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectIfUserNotAuthorized(req, resp);
        createAttributeProfile(req);
    }

    private void redirectIfUserNotAuthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isUserAuthorized = UserService.getInstance().isUserAuthorized(req);
        if (!isUserAuthorized) {
            req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
        }
    }

    private void createAttributeProfile(HttpServletRequest req) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        UserDAOObject userProfile = getDAOFactory().getUserDAO().getUserById(userId);
        initAttributesProfileForShow(userProfile, req);
    }

    private void initAttributesProfileForShow(UserDAOObject userProfile, HttpServletRequest req) {
        req.setAttribute(FIRST_NAME, userProfile.getFirstName());
        req.setAttribute(LAST_NAME, userProfile.getLastName());
        req.setAttribute(EMAIL, userProfile.getEmail());
        req.setAttribute(PHONE, userProfile.getPhone());
        req.setAttribute(AVATAR, userProfile.getUserAvatar());
    }

    //profile change

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOObject userForCompare = getUserForCompare(req);
        UserDAOObject userForChange = initUserForChangeFromRequest(req);
        String answer = getAnswerFromProfileService(userForChange, userForCompare);
        req.setAttribute(ANSWER, answer);
        this.doGet(req, resp);
    }

    private UserDAOObject getUserForCompare(HttpServletRequest req) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        return  getDAOFactory().getUserDAO().getUserById(userId);
    }

    private UserDAOObject initUserForChangeFromRequest(HttpServletRequest req) {
        UserDAOObject userForChange = new UserDAOObject();
        userForChange = getUserFromRequest(userForChange, req);

        return userForChange;
    }

    private String getAnswerFromProfileService(UserDAOObject userForChange, UserDAOObject userForCompare) throws ServletException, IOException {
        String resultValidation = getResultValidation(userForChange, userForCompare);
        userForChange = getUserWithoutEmptyEmail(userForChange, userForCompare);
        userForChange = getUserWithCorrectPassword(userForChange, userForCompare);
        return ProfileService.getInstance().getAnswerAccordingValidation(resultValidation, userForChange);
    }

    private UserDAOObject getUserFromRequest(UserDAOObject userForChange, HttpServletRequest req) {
        userForChange.setRoleId(EnumRoles.USER.getRole());
        userForChange.setFirstName(req.getParameter(FIRST_NAME));
        userForChange.setLastName(req.getParameter(LAST_NAME));
        userForChange.setEmail(req.getParameter(EMAIL));
        userForChange.setPassword(req.getParameter(PASSWORD));

        return userForChange;
    }

    private String getResultValidation(UserDAOObject userForChange, UserDAOObject userForCompare) throws ServletException, IOException {
        userForChange = fillingNonEmptyFields(userForChange, userForCompare);
        return ProfileService.getInstance().getResultAfterValidation(userForChange, userForCompare);
    }

    /**
     * Заполняем сущность параметрами, которые не вводим в интерфейсе
     */
    private UserDAOObject fillingNonEmptyFields(UserDAOObject userForChange, UserDAOObject userForCompare) {
        userForChange.setId(userForCompare.getId());
        userForChange.setPhone(userForCompare.getPhone());
        userForChange.setRegistrationDate(userForCompare.getRegistrationDate());
        return userForChange;
    }

    private UserDAOObject getUserWithoutEmptyEmail(UserDAOObject userForChange, UserDAOObject userForCompare) {
        if(Objects.equals(userForChange.getEmail(), "")){
            userForChange.setEmail(userForCompare.getEmail());
        }
        return userForChange;
    }

    private UserDAOObject getUserWithCorrectPassword(UserDAOObject userForChange, UserDAOObject userForCompare){
        if(Objects.equals(userForChange.getPassword(), "")){
            userForChange.setPassword(userForCompare.getPassword());
        }
        else{
            String passwordWithoutHash = userForChange.getPassword();
            userForChange.setPassword(EncryptionUtils.getMd5Digest(passwordWithoutHash));
        }
        return userForChange;
    }
}
