package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        final int EMPTY_QUANTITY = 0;
        HttpSession authorization = req.getSession();
        Object userId = authorization.getAttribute("userId");
        if (userId == null) {
            req.setAttribute("quantityProducts", EMPTY_QUANTITY);
        } else {
            long quantityProducts = getQuantityProducts((Long) userId);
            req.setAttribute("quantityProducts", quantityProducts);
        }
    }

    private long getQuantityProducts(long userId) throws SQLException {
        Long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        return getDAOFactory().getOrderItemsDAO().getProductsBySalesOrderId(salesOrderId);
    }
}
