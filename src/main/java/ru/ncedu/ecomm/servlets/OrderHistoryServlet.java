package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.SalesOrder;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/orders"})
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseOrdersHistory(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseOrdersHistory(req, resp);
    }

    private void browseOrdersHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Boolean userInSystem = UserService.getInstance().isUserAuthorized(req);
        if (!userInSystem) {
            req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
        }

        long userId = UserService.getInstance().getCurrentUserId(req);


        List<SalesOrder> orderHistory = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderToOrderHistory(userId);


        req.setAttribute("orderHistory", orderHistory);
        req.getRequestDispatcher(Configuration.getProperty("page.ordersHistory")).forward(req, resp);
    }
}
