package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        addToShoppingCart(req, resp);
    }

    private void addToShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") == null) {
            PrintWriter out = resp.getWriter();
            out.println("error");
        } else {
            long userId = UserService.getInstance().getCurrentUserId(req);
            long productId = Long.parseLong(req.getParameter("productId"));
            addToCartAndGetQuantity(resp, userId, productId);
        }
    }

    private void addToCartAndGetQuantity(HttpServletResponse resp, long userId, long productId) throws IOException {
        try {
            ShoppingCartService.getInstance().addToShoppingCart(userId, productId);
            long quantityProducts = getQuantityProducts(userId);
            PrintWriter out = resp.getWriter();
            out.println(quantityProducts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(HttpServletRequest req) throws ServletException, IOException, SQLException {
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
