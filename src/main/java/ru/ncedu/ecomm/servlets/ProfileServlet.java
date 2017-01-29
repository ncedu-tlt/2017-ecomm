package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if (authorization.getAttribute("user_id") == null) {
            req.setAttribute("answer", "You are not logged in!");
            req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
        } else if (checkOnEmpty(req)) {
            long userId = getUserIdFromSession(authorization);
            changeProfile(userId, req);
            req.setAttribute("answer", "Profile was changed.");
            req.getRequestDispatcher("/views/pages/profile.jsp?user_id=" + userId).forward(req, resp);
        } else {
            req.setAttribute("answer", "Nothing to change.");
            req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
        }
    }

    private boolean checkOnEmpty(HttpServletRequest req) {
        List<String> userParameters = new ArrayList<>();
        userParameters.add(req.getParameter("firstName"));
        userParameters.add(req.getParameter("lastName"));
        userParameters.add(req.getParameter("email"));
        userParameters.add(req.getParameter("password"));

        for (String parameter : userParameters) {
            if (!parameter.trim().isEmpty())
                return true;
        }
        return false;
    }

    private long getUserIdFromSession(HttpSession authorization) {
        String parametersFromSession = authorization.getAttribute("user_id").toString();
        long userId = Long.parseLong(parametersFromSession);
        return userId;
    }

    private void changeProfile(long userId, HttpServletRequest req) {
        User user = getDAOFactory().getUserDAO().getUserById(userId);

        ProfileService profile = new ProfileService(req, userId);

        user = profile.changeProfile(user);

        getDAOFactory().getUserDAO().updateUser(user);
    }
}
