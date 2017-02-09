package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.services.ProductViewService;
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

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

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
            long userId = UserService.getInstance().getCurrentUserId(request, response);
            long orderStatusId = 1;
            List<ProductViewModel> productViewModels = null;
            productViewModels = incrementAmountInOrderItem(1, 137);
            SalesOrderViewModel showSalesOrderList = ShoppingCartService.getInstaince().getSalesOrderModel(orderStatusId, userId);
            request.setAttribute("showSalesOrderList", showSalesOrderList);
            request.getRequestDispatcher("/views/pages/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<ProductViewModel> incrementAmountInOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getDAOFactory().getOrderItemsDAO().getOrderItemByUserConfig(productId, salesOrderId);
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(salesOrderId);
        for (ProductViewModel product : products) {
            long amount = product.getPrice();
            for (long i = 1; i < orderItem.getQuantity(); i++) {
                amount *= i;
            }
            product.setPrice(amount);
        }
        return products;
    }
}