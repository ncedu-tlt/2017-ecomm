package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.services.EnumOrderStatus;
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
            if (request.getParameter("cartActions") != null && request.getParameter("cartActions").equals("delete")){
                ShoppingCartService.getInstance().deletedProductInOrderItemDataBase(
                        Long.parseLong(request.getParameter("productId")),
                        Long.parseLong(request.getParameter("salesOrderId")));
            }
            UserService.getInstance().redirectToLoginIfNeeded(request, response);
            long userId = UserService.getInstance().getCurrentUserId(request, response);
            List<SalesOrderViewModel> salesOrderList = ShoppingCartService.getInstance().getSalesOrderModelList(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute("salesOrderList", salesOrderList);
            request.getRequestDispatcher("/views/pages/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}