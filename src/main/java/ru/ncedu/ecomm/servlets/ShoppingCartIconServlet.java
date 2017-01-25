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
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int EMPTY_QUANTITY = 0;
        long userId = getUserIdFromSession(req);
        System.out.println(userId);
        if (userId == 0) {
            req.setAttribute("quantityProducts", EMPTY_QUANTITY);
        } else {
            int quantityProducts = getQuantityProducts(userId);
            System.out.println(quantityProducts);
            req.setAttribute("quantityProducts", quantityProducts);
        }
    }

    private int getQuantityProducts(long userId) {
        List<SalesOrder> salesOrderByUserId = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        int quantityProducts = salesOrderByUserId.size();
        return quantityProducts;
    }

    private long getUserIdFromSession(HttpServletRequest req) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if (authorization.getAttribute("userId") == null) {
            return 0;
        }
        long userIdFromSession = Long.parseLong(authorization.getAttribute("userId").toString());
        return userIdFromSession;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        showQuantityProducts(req, resp);
    }

//    private void showQuantityProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long userId = getUserIdFromSession(req, resp);
//        String quantityProducts = String.valueOf(getQuantityProducts(userId));
//        req.setAttribute("quantityProducts", quantityProducts);
//    }
//
//    private long getUserIdFromSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession authorization = req.getSession();
//        if(authorization.getAttribute("userId") == null){
//            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
//        }
//        long userIdFromSession = Long.parseLong(authorization.getAttribute("userId").toString());
//        return userIdFromSession;
//    }
//


}
