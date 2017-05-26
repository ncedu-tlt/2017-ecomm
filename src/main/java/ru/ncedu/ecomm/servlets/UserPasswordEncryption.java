package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploadPassword")
@MultipartConfig

public class UserPasswordEncryption extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        encryptUserPassword(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        encryptUserPassword(req, resp);
    }

    public void encryptUserPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userPassword = request.getParameter("userPassword");
        if (userPassword != null) {
            UserDAOObject checkedUser = DAOFactory.getDAOFactory().getUserDAO().getUserByPassword(userPassword);
            String passwordDigest = EncryptionUtils.getMd5Digest(userPassword);
            checkedUser.setPassword(passwordDigest);
            DAOFactory.getDAOFactory().getUserDAO().updateUser(checkedUser);
        }
    }
}
