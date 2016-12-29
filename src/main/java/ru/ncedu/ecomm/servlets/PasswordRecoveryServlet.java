package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.passwordRecovery.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // handle operations
        sendLettersEmail(req, resp);
    }

    private void sendLettersEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String toEmail = req.getParameter("email");
        String fromEmail = "overylord@mail.ru";
        String recoveryHash = generateRecoveryHash();

        SendMail sender = new SendMail(toEmail, fromEmail, "Test message", "Hello. It's a first letter: yandex.ru", recoveryHash);

        if (sender.checkEmail()) {
            User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
            userByEmail.setRecoveryHash(recoveryHash);
            getDAOFactory().getUserDAO().updateUser(userByEmail);

            req.setAttribute("answer", sender.sendMail());
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        } else if (!sender.checkEmail()) {
            req.setAttribute("answer", "Uncorrect email! Please try enter other email");
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }
    }


    private String generateRecoveryHash() {
        List<Integer> uniqueHash = new ArrayList<Integer>();
        Random random = new Random();

        while (uniqueHash.size() < 10) {
            uniqueHash.add(random.nextInt(9));
        }

        String recoveryHash = "";
        for (Integer hash : uniqueHash) {
            recoveryHash += hash.toString();
        }

        return recoveryHash;
    }
}