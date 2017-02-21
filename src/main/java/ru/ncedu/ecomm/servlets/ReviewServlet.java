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

    private static final String ADD_REVIEW = "add";
    private static final String REMOVE_REVIEW = "remove";
    private static final String UPDATE_REVIEW = "update";
    private static final String EDIT_REVIEW = "edit";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("userId") != null) {
            addReview(req, resp);
        }
    }

    private void addReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String productId = req.getParameter("productId");

        switch (req.getParameter("reviewActions")) {
            case ADD_REVIEW: {

                Review review = new ReviewBuilder()
                        .setUserId((Long) httpSession.getAttribute("userId"))
                        .setProductId(Long.parseLong(productId))
                        .setRating(Integer.parseInt(req.getParameter("rating")))
                        .setCreationDate(new Date(System.currentTimeMillis()))
                        .setDescription(req.getParameter("review"))
                        .build();

                addReviewToBase(review);
                resp.sendRedirect("/product?product_id=" + productId);
                break;
            }
            case REMOVE_REVIEW: {

                Review review = new ReviewBuilder()
                        .setProductId(Long.parseLong(req.getParameter("productId")))
                        .setUserId(Long.parseLong(req.getParameter("userId")))
                        .build();

                removeReviewByBase(review);
                resp.sendRedirect("/product?product_id=" + productId);
                break;
            }
            case UPDATE_REVIEW: {

                Review review = new ReviewBuilder()
                        .setUserId((Long) httpSession.getAttribute("userId"))
                        .setProductId(Long.parseLong(req.getParameter("productId")))
                        .setRating(Integer.parseInt(req.getParameter("rating")))
                        .setCreationDate(new Date(System.currentTimeMillis()))
                        .setDescription(req.getParameter("reviewDescription"))
                        .build();

                updateReviewByBase(review);
                resp.sendRedirect("/product?product_id=" + productId);
                break;
            }
            case EDIT_REVIEW: {

                resp.sendRedirect("/views/components/editReview.jsp");
                break;
            }
        }
    }

    private void updateReviewByBase(Review review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .updateReviews(review);
    }

    private void removeReviewByBase(Review review) {
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
