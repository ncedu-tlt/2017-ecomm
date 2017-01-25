package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.models.UserViewModel;
import ru.ncedu.ecomm.servlets.models.builders.UserViewModelBuilder;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseUser(req, resp);
    }

    private void browseUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserViewModel> users = getUserToView();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/pages/users.jsp").forward(req, resp);

    }

    private List<UserViewModel> getUserToView() {

        List<UserViewModel> userViewModels = new ArrayList();
        List<User> users = DAOFactory.getDAOFactory().getUserDAO().getUsers();
        UserViewModel userViewModel = null;

        for (User user : users) {

            userViewModel = new UserViewModelBuilder()
                    .setId(user.getId())
                    .setFio(UserService.getInstance().getUserName(user))
                    .setRole(getRolesbyId(user.getRoleId()))
                    .setEmail(user.getEmail())
                    .setRegistrationDate(user.getRegistrationDate())
                    .build();
            userViewModels.add(userViewModel);
        }
        return userViewModels;
    }

    private String getRolesbyId(long id) {
        Role role = DAOFactory.getDAOFactory().getRoleDAO().getRoleById(id);
        return role.getName();
    }
}
