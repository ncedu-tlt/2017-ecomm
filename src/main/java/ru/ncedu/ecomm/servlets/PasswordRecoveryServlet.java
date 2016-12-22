package ru.ncedu.ecomm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // handle operations
        String messageForSend = "Your email is ";
        String email = req.getParameter("email");
        SendMail sender = new SendMail(email);
        if(sender.checkEmail()){
            req.setAttribute("answer", messageForSend+" correct!");
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", messageForSend+" not correct!");
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }

    }

    class SendMail{
        private final String EMAIL;

        SendMail(String email){
            EMAIL = email;
        }

        public boolean checkEmail(){
            Pattern patternEmailValidation = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = patternEmailValidation.matcher(EMAIL);

            return matcher.find();
        }
    }

}
