package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.data.models.builders.UserBuilder;
import ru.ncedu.ecomm.utils.UserValidationUtils;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private static final int ROLE_USER = 3;
    private static final String ANSWER = "answer";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CHECK_PASSWORD = "checkPassword";
    private static final String REGISTRATION = "registration";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Configuration.getProperty("page.registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter(EMAIL).isEmpty()
           && req.getParameter(PASSWORD).isEmpty()
           && req.getParameter(CHECK_PASSWORD).isEmpty()) {
            req.setAttribute(ANSWER, "empty_fields");
            req.getRequestDispatcher(Configuration.getProperty("page.registration")).forward(req, resp);
            return;
        }

        if (!UserValidationUtils.checkEmail(req.getParameter(EMAIL))) {
            req.setAttribute(ANSWER, "incorrect_email");
            req.getRequestDispatcher(Configuration.getProperty("page.registration")).forward(req, resp);
            return;
        }

        if (!req.getParameter(PASSWORD).equals(req.getParameter(CHECK_PASSWORD))) {
            req.setAttribute(ANSWER, "pass_error");
            req.getRequestDispatcher(Configuration.getProperty("page.registration")).forward(req, resp);
            return;
        }

        if (getDAOFactory().getUserDAO().getUserByEmail(req.getParameter(EMAIL)) != null) {

            req.setAttribute(ANSWER, "email_used");
            req.getRequestDispatcher(Configuration.getProperty("page.registration")).forward(req, resp);
            return;
        }

        String hashPassword = EncryptionUtils.getMd5Digest(req.getParameter(PASSWORD));
        User user = new UserBuilder()
                .setEmail(req.getParameter(EMAIL))
                .setPassword(hashPassword)
                .setRoleId(ROLE_USER)
                .build();
        getDAOFactory().getUserDAO().addUser(user);

        req.setAttribute(REGISTRATION, "reg_success");
        req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
    }

}
