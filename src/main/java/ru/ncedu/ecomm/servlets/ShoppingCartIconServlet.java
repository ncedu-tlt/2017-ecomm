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

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int EMPTY_QUANTITY = 0; //TODO: в константу
        HttpSession authorization = req.getSession();
        if (authorization.getAttribute("userId") == null) {
            req.setAttribute("quantityProducts", EMPTY_QUANTITY);
        } else {
            long userId = UserService.getInstance().getCurrentUserId(req, resp); //TODO: мы его уже получали
            long quantityProducts = getQuantityProducts(userId);
            req.setAttribute("quantityProducts", quantityProducts);
        }
    }

    private long getQuantityProducts(long userId) {
        long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        return getDAOFactory().getOrderItemsDAO().getProductsBySalesOrderId(salesOrderId);
    }
}
