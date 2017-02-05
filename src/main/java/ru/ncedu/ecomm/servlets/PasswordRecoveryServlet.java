package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SendLetterToEmail(req, resp);
    }

    private void SendLetterToEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PasswordRecoveryService recoveryService = new PasswordRecoveryService(req);
        SendMail sender = SendMail.buildSenderByPasswordRecovery(recoveryService);

        if (sender.checkEmail()) {
            updateUserByEmail(recoveryService);
            req.setAttribute("answer", sender.sendMail());
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        } else if (!sender.checkEmail()) {
            req.setAttribute("answer", "Incorrect email! Please try enter other email");
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }
    }

    private void updateUserByEmail(PasswordRecoveryService recoveryService) {
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(recoveryService.getToEmail());
        userByEmail.setRecoveryHash(recoveryService.getRecoveryHash());
        getDAOFactory().getUserDAO().updateUser(userByEmail);
    }


}