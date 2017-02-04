package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Review;
import ru.ncedu.ecomm.data.models.builders.ReviewBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.ncedu.ecomm.servlets.ProductServlet.NOT_AUTHORIZED_USER_ID;

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

        String  productId = req.getParameter("productId");

        Review review = new ReviewBuilder()
                .setUserId((Long) httpSession.getAttribute("userId"))
                .setProductId(Long.parseLong(productId))
                .setDescription(String.valueOf(req.getAttribute("review")))
                .build();


        if (review.getUserId() > NOT_AUTHORIZED_USER_ID) {
            addReviewToBase(review);
        }
        req.getRequestDispatcher("/views/pages/product.jsp?product_id=" + review.getProductId()).forward(req, resp);

    }

    private void addReviewToBase(Review review) {

    }
}
