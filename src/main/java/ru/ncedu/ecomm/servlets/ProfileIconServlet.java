package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ProfileIconServlet", urlPatterns = {"/profileIcon"})
public class ProfileIconServlet extends HttpServlet {
    private static final String EMAIL = "email";
    private static final String AVATAR_PATH = "avatarPath";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isUserAuthorized = UserService.getInstance().isUserAuthorized(req);
        if (isUserAuthorized) {
            showProfileIcon(req);
        }
    }

    private void showProfileIcon(HttpServletRequest req) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req);
        User userById = getDAOFactory().getUserDAO().getUserById(userId);
        passDataToProfileIcon(userById, req);
    }

    private void passDataToProfileIcon(User userById, HttpServletRequest req) {
        String email = userById.getEmail();
        String avatarPath = userById.getUserAvatar();
        req.setAttribute(EMAIL, email);
        req.setAttribute(AVATAR_PATH, avatarPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doGet(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
