package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.SalesOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long userId = getUserIdFromSession(req, resp);
        long userId = 1;
        String quantityProducts = String.valueOf(getQuantityProducts(userId));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(quantityProducts);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    private long getUserIdFromSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if(authorization.getAttribute("user_id") == null){
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
        long userIdFromSession = Long.parseLong(authorization.getAttribute("userId").toString());
        return userIdFromSession;
    }

    private int getQuantityProducts(long userId) {
        List<SalesOrder> salesOrderByUserId = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        int quantityProducts = salesOrderByUserId.size();
        return quantityProducts;
    }


}
