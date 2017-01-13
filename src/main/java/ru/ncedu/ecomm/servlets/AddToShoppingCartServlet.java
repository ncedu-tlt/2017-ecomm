package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.SalesOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if(authorization.getAttribute("user_id") == null){
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
        long userId = Long.parseLong(authorization.getAttribute("userId").toString());
        long productId = Long.parseLong(req.getParameter("productId"));
        addToShoppingCart(userId);
        req.getRequestDispatcher("/views/pages/cart.jsp").forward(req, resp);
    }

   private void addToShoppingCart(long userId){
       BigDecimal limit = new BigDecimal("2000.00");
       Date creationDate = java.sql.Date.valueOf("2016-11-11");
       long orderStatusId = 1;
       SalesOrder saleOrder = addToSalesOrder(userId, limit, creationDate, orderStatusId);
       getDAOFactory().getSalesOrderDAO().addSalesOrder(saleOrder);
   }

    private SalesOrder addToSalesOrder(long userId, BigDecimal limit, Date creationDate, long orderStatusId) {
        SalesOrder saleOrder = new SalesOrder();
        saleOrder.setUserId(userId);
        saleOrder.setCreationDate(creationDate);
        saleOrder.setLimit(limit);
        saleOrder.setOrderStatusId(orderStatusId);

        return saleOrder;
    }
}