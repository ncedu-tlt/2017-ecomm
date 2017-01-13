package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ProductToShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userSession = req.getSession();
        if(userSession.getAttribute("sales_order_id") == null){
            userSession.setAttribute("sales_order_id", ProductToShoppingCart.nextSalesOrderId());
        }
        long productId = Long.parseLong(req.getParameter("productId"));
        long salesOrderId = Long.parseLong(userSession.getAttribute("sales_order_id").toString());
        ProductToShoppingCart productToShoppingCart = new ProductToShoppingCart(productId, salesOrderId);
        productToShoppingCart.addToShoppingCart();
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

}