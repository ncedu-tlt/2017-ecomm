package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "submitOrderServlet", urlPatterns = {"/submitOrder"})
public class submitOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Configuration.getProperty("page.submitOrder")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionButton(req, resp);
    }

    private void setOrderStatusId(HttpServletRequest request) throws ServletException, IOException, SQLException {
        long userId = UserService.getInstance().getCurrentUserId(request);
        long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        SalesOrder salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setOrderStatusId(EnumOrderStatus.SUBMITTED.getStatus());
        DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    private void actionButton(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter("submitButton") != null) {
                switch (request.getParameter("submitButton")) {
                    case "okay": {
                        setOrderStatusId(request);
                        response.sendRedirect("/home");
                        break;
                    }
                    case "cancel": {
                        response.sendRedirect("/cart");
                        break;
                    }
                }

            }
        } catch (IOException | SQLException | ServletException e) {
            e.printStackTrace();
        }
    }
}
