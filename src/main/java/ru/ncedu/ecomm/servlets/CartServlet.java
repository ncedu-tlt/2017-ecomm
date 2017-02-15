package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSalesOrderList(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSalesOrderList(req, resp);
    }

    private void showSalesOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserService.getInstance().redirectToLoginIfNeeded(request, response);
            long userId = UserService.getInstance().getCurrentUserId(request);
            formActionOnShoppingCart(request, userId);
            setLimitInDataBase(request);
            List<SalesOrderViewModel> salesOrderList = ShoppingCartService.getInstance()
                    .getSalesOrderModelList(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute("salesOrderList", salesOrderList);
            request.getRequestDispatcher("/views/pages/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void formActionOnShoppingCart(HttpServletRequest request, long userId) throws SQLException {
        if (request.getParameter("cartActions") != null && request.getParameter("cartActions").equals("delete")) {
            ShoppingCartService.getInstance().deletedProductInOrderItemDataBase(
                    Long.parseLong(request.getParameter("productId")),
                    Long.parseLong(request.getParameter("salesOrderId")));
        } else if (request.getParameter("emptyActions") != null && request.getParameter("emptyActions").equals("emptyTrash")) {
            ShoppingCartService.getInstance().deletedAllProductsInOrderItemDataBase(userId);
        }
    }

    private void setLimitInDataBase(HttpServletRequest request) {
        if (request.getParameter("limitInput") != null) {
            BigDecimal limit = BigDecimal.valueOf(Long.parseLong(request.getParameter("limitInput")));
            SalesOrder salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO()
                    .getSalesOrderById(Long.parseLong(request.getParameter("salesOrderId")));
            salesOrder.setLimit(limit);
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        }
    }
}