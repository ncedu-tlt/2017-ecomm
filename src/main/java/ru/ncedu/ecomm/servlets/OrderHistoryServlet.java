package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

        UserService.getInstance().redirectToLoginIfNeeded(req, resp);
        long userId = UserService.getInstance().getCurrentUserId(req);

        List<SalesOrderViewModel> orderHistory = null;
        try {
            orderHistory = ShoppingCartService.getInstance().getSalesOrderModelList(1, userId); //TODO: что есть 1?
        } catch (SQLException e) {
            // TODO: что будет, если упадёт эксепшен?
            e.printStackTrace();
        }

        req.setAttribute("orderHistory", orderHistory);
        req.getRequestDispatcher(Configuration.getProperty("page.ordersHistory")).forward(req, resp);
    }
}
