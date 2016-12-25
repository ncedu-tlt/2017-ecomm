package ru.ncedu.ecomm.servlets;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.data.models.builders.UserBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!req.getParameter("email").isEmpty()
                && !req.getParameter("password").isEmpty()
                && !req.getParameter("ConfirmPassword").isEmpty()) {
            if (checkEmail(req.getParameter("email"))) {
                if (req.getParameter("password").equals(req.getParameter("ConfirmPassword"))) {
                    User user = new UserBuilder()
                            .setEmail(req.getParameter("email"))
                            .setPassword(req.getParameter("password"))
                            .build();
                    getDAOFactory().getUserDAO().addUser(user);
                } else {
                    req.setAttribute("answer", "Passwords don't match");
                    req.getRequestDispatcher("/views/pages/registration.jsp").forward(req, resp);
                }
            } else  {
                req.setAttribute("answer", "Wrong email");
                req.getRequestDispatcher("/views/pages/registration.jsp").forward(req, resp);
            }
        } else  {
            req.setAttribute("answer", "Fields must not be empty");
            req.getRequestDispatcher("/views/pages/registration.jsp").forward(req, resp);
        }
        req.setAttribute("registration", "Registration success! Please sign in");
        req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
    }

    public static boolean checkEmail(String email){
        Pattern patternEmailValidation = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$"
                                                         ,Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);

        return matcher.find();
    }

}
