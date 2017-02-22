package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.data.models.builders.UserBuilder;
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
    private static final String CHECK = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGISTRATION = "/views/pages/registration.jsp";
    private static final String LOGIN = "/views/pages/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("email").isEmpty()
           && req.getParameter("password").isEmpty()
           && req.getParameter("checkPassword").isEmpty()) {
            req.setAttribute("answer", "Fields must not be empty");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (!checkEmail(req.getParameter("email"))) {
            req.setAttribute("answer", "Email is incorrect");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (!req.getParameter("password").equals(req.getParameter("checkPassword"))) {
            req.setAttribute("answer", "Passwords dont match");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        if (getDAOFactory().getUserDAO().getBoolUserByEmail(req.getParameter("email"))) {

            req.setAttribute("answer", "Email is already in use");
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            return;
        }

        String hashPassword = EncryptionUtils.getMd5Digest(req.getParameter("password"));
        User user = new UserBuilder()
                .setEmail(req.getParameter("email"))
                .setPassword(hashPassword)
                .setRoleId(3)
                .build();
        getDAOFactory().getUserDAO().addUser(user);

        req.setAttribute("registration", "Registration success! Please sign in");
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }

    private static boolean checkEmail(String email){

        Pattern patternEmailValidation = Pattern.compile(CHECK
                                                         ,Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);

        return matcher.find();
    }

}
