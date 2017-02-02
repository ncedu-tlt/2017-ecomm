package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.OrderItem;
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
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = getUserIdFromSession(req, resp);
        long productId = Long.parseLong(req.getParameter("productId"));
        addToShoppingCart(userId, productId);
        resp.sendRedirect("/views/pages/cart.jsp");
    }

    private long getUserIdFromSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if(authorization.getAttribute("userId") == null){
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
        long userIdFromSession = Long.parseLong(authorization.getAttribute("userId").toString());
        return userIdFromSession;
    }

    private void addToShoppingCart(long userId, long productId) {
        BigDecimal limit = new BigDecimal("50000.00");
        Date creationDate = new Date(System.currentTimeMillis());
        long orderStatusId = 1;

        SalesOrder saleOrder = addToSalesOrder(userId, limit, creationDate, orderStatusId);
        getDAOFactory().getSalesOrderDAO().addSalesOrder(saleOrder);

        long salesOrderId = getSalesOrderId();

        OrderItem orderItem = addToOrderItem(productId, salesOrderId);
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    private SalesOrder addToSalesOrder(long userId, BigDecimal limit, Date creationDate, long orderStatusId) {
        SalesOrder saleOrder = new SalesOrder();

        saleOrder.setUserId(userId);
        saleOrder.setCreationDate(creationDate);
        saleOrder.setLimit(limit);
        saleOrder.setOrderStatusId(orderStatusId);

        return saleOrder;
    }

    private OrderItem addToOrderItem(long productId, long salesId) {
        int defaultQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesId);
        orderItem.setQuantity(defaultQuantity);

        return orderItem;
    }

    private long getSalesOrderId() {
        long salesOrderId;

        List<SalesOrder> salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrders();
        SalesOrder lastSales = salesOrder.get(salesOrder.size() - 1);
        salesOrderId = lastSales.getSalesOrderId();

        return salesOrderId;
    }
}