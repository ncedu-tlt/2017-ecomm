package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.UserDAOObject;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        String passwordDigest = EncryptionUtils.getMd5Digest(password);
        UserDAOObject user = getDAOFactory().getUserDAO().getUserByEmail(email);
        if (user != null && user.getPassword().equals(passwordDigest)) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRoleId", user.getRoleId());
            redirectToPage(req, resp, Configuration.getProperty("servlet.home"));
        } else {
            req.setAttribute("answer", "Uncorrect user! Check email and password");
            req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
        }
    }
}
