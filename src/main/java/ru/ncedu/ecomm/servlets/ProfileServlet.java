package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.UserDAOObject;
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
    private static final String OLD_PASSWORD = "oldPassword";
    private static final String ANSWER = "answer";
    private static final String SUCCESS = "Success";
    private static final String PROFILE_REDIRECT = Configuration.getProperty("page.profile");
    private static final String LOGIN_REDIRECT = Configuration.getProperty("page.login");
    private static final String ERROR_INPUT_OLD_PASSWORD = "ErrorInputOldPassword";

    //show profile
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showProfile(req, resp);
        req.getRequestDispatcher(PROFILE_REDIRECT).forward(req, resp);
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectIfUserNotAuthorized(req, resp);
        createAttributeProfile(req);
    }

    private void redirectIfUserNotAuthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isUserAuthorized = UserService.getInstance().isUserAuthorized(req);
        if (!isUserAuthorized) {
            req.getRequestDispatcher(LOGIN_REDIRECT).forward(req, resp);
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
        req.setAttribute(AVATAR, req.getContextPath() + userProfile.getUserAvatar());
    }

    //profile change
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOObject userForCompare = getUserForCompare(req);
        UserDAOObject userForChange = getUserFromRequest(req, userForCompare);
        if (userForChange == null) {
            req.setAttribute(ANSWER, ERROR_INPUT_OLD_PASSWORD);
            this.doGet(req, resp);
        } else {
            String validationMessage = getValidationMessage(userForChange, userForCompare);
            req.setAttribute(ANSWER, validationMessage);
            this.doGet(req, resp);
        }
    }

    private UserDAOObject getUserForCompare(HttpServletRequest req) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        return getDAOFactory().getUserDAO().getUserById(userId);
    }


    private UserDAOObject getUserFromRequest(HttpServletRequest req, UserDAOObject oldUser) {
        UserDAOObject userForChange = new UserDAOObject();

        userForChange.setRoleId(oldUser.getRoleId());
        userForChange.setFirstName(req.getParameter(FIRST_NAME));
        userForChange.setLastName(req.getParameter(LAST_NAME));
        userForChange.setEmail(req.getParameter(EMAIL));
        userForChange.setPhone(req.getParameter(PHONE));
        if (isPasswordChecked(req, oldUser))
            userForChange.setPassword(req.getParameter(PASSWORD));
        else
            return null;
        return userForChange;
    }

    private boolean isPasswordChecked(HttpServletRequest req, UserDAOObject oldUser) {
        if(!Objects.equals(req.getParameter(PASSWORD), "")) {
            String enteredPasswordHash = EncryptionUtils.getMd5Digest(req.getParameter(OLD_PASSWORD));
            return Objects.equals(enteredPasswordHash, oldUser.getPassword());
        }
        return true;
    }

    private String getValidationMessage(UserDAOObject userForChange, UserDAOObject userForCompare) throws ServletException, IOException {
        String resultValidation = getResultValidation(userForChange, userForCompare);
        if (Objects.equals(resultValidation, SUCCESS)) {
            userForChange = setEmailFieldEmpty(userForChange, userForCompare);
            userForChange = setCorrectPassword(userForChange, userForCompare);
            return ProfileService.getInstance().getAnswerAccordingValidation(resultValidation, userForChange);
        } else
            return resultValidation;
    }

    private String getResultValidation(UserDAOObject userForChange, UserDAOObject userForCompare) throws ServletException, IOException {
        userForChange = fillingNonEmptyFields(userForChange, userForCompare);
        return ProfileService.getInstance().getResultValidation(userForChange, userForCompare);
    }

    /**
     * Заполняем сущность параметрами, которые не вводим в интерфейсе
     */
    private UserDAOObject fillingNonEmptyFields(UserDAOObject userForChange, UserDAOObject userForCompare) {
        userForChange.setId(userForCompare.getId());
        userForChange.setRegistrationDate(userForCompare.getRegistrationDate());
        return userForChange;
    }

    private UserDAOObject setEmailFieldEmpty(UserDAOObject userForChange, UserDAOObject userForCompare) {
        if (Objects.equals(userForChange.getEmail(), "")) {
            userForChange.setEmail(userForCompare.getEmail());
        }
        return userForChange;
    }

    private UserDAOObject setCorrectPassword(UserDAOObject userForChange, UserDAOObject userForCompare) {
        if (Objects.equals(userForChange.getPassword(), ""))
            userForChange.setPassword(userForCompare.getPassword());
        else {
            String passwordWithoutHash = userForChange.getPassword();
            userForChange.setPassword(EncryptionUtils.getMd5Digest(passwordWithoutHash));
        }
        return userForChange;
    }
}
