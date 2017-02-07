package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Review;
import ru.ncedu.ecomm.data.models.builders.ReviewBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addReview(req, resp);
    }

    private void addReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String productId = req.getParameter("productId");

        if (httpSession.getAttribute("userId") != null &&
                req.getParameter("reviewActions").equals("add")) {

            Review review = new ReviewBuilder()
                    .setUserId((Long) httpSession.getAttribute("userId"))
                    .setProductId(Long.parseLong(productId))
                    .setRating(Integer.parseInt(req.getParameter("rating")))
                    .setCreationDate(new Date(System.currentTimeMillis()))
                    .setDescription(req.getParameter("review"))
                    .build();

            addReviewToBase(review);

        } else if (httpSession.getAttribute("userId") != null &&
                req.getParameter("reviewActions").equals("remove")){
            Review review = new ReviewBuilder()
                    .setProductId(Long.parseLong(req.getParameter("productId")))
                    .setUserId(Long.parseLong(req.getParameter("userId")))
                    .build();

            removeRebiewByBase(review);
        }
            resp.sendRedirect("/product?product_id=" + productId);
    }

    private void removeRebiewByBase(Review review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .deleteReviews(review);
    }

    private void addReviewToBase(Review review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .addReviews(review);
    }
}
