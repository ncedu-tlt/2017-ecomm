package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.data.models.builders.UserBuilder;
import ru.ncedu.ecomm.utils.EmailUtils;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private static final String REGISTRATION = "/views/pages/registration.jsp";
    private static final String LOGIN = "/views/pages/login.jsp";
    private static final int ROLE_USER = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("email").isEmpty()
           && req.getParameter("password").isEmpty()
           && req.getParameter("checkPassword").isEmpty()) {
            req.setAttribute("answer", "empty_fields");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (!EmailUtils.checkEmail(req.getParameter("email"))) {
            req.setAttribute("answer", "incorrect_email");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (!req.getParameter("password").equals(req.getParameter("checkPassword"))) {
            req.setAttribute("answer", "pass_error");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (getDAOFactory().getUserDAO().getUserByEmail(req.getParameter("email")) != null) {

            req.setAttribute("answer", "email_used");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        String hashPassword = EncryptionUtils.getMd5Digest(req.getParameter("password"));
        User user = new UserBuilder()
                .setEmail(req.getParameter("email"))
                .setPassword(hashPassword)
                .setRoleId(ROLE_USER)
                .build();
        getDAOFactory().getUserDAO().addUser(user);

        req.setAttribute("registration", "Registration success! Please sign in");
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }

}
