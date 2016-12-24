package ru.ncedu.ecomm.servlets;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import ru.ncedu.ecomm.data.models.User;

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
        // handle operations

        if(!req.getParameter("email").isEmpty()
                && !req.getParameter("password").isEmpty()
                && !req.getParameter("ConfirmPassword").isEmpty()) {
            if (checkEmail(req.getParameter("email"))) {
                if (req.getParameter("password").equals(req.getParameter("ConfirmPassword"))) {
                    User user = new User();
                    user.setEmail(req.getParameter("email"));
                    user.setPassword(req.getParameter("password"));
                    getDAOFactory().getUserDAO().addUser(user);
                } else resp.getWriter().write("пароли не совпали");
            } else  resp.getWriter().write("wrong email, чисто для теста");
        } else  resp.getWriter().write("Строки не должны быть пустыми, тоже для теста");

        //req.getRequestDispatcher("/views/pages/registration.jsp").forward(req, resp);
    }

    public static boolean checkEmail(String email){
        Pattern patternEmailValidation = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);

        return matcher.find();
    }

}
