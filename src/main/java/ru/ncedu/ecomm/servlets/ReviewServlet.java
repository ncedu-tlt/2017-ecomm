package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.ReviewDAOObject;
import ru.ncedu.ecomm.data.models.builders.ReviewDAOObjectBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {

    private static final String ADD_REVIEW = "add";
    private static final String REMOVE_REVIEW = "remove";
    private static final String UPDATE_REVIEW = "update";
    private static final String EDIT_REVIEW = "edit";
    private static final String USER_ID = "userId";
    private static final String ACTIONS = "reviewActions";
    private static final String REVIEW = "review";
    private static final String PRODUCT_ID = "productId";
    private static final String RATING = "rating";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Configuration.getProperty("page.product")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute(USER_ID) != null) {
            doAction(req, resp);
        }
    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getParameter(ACTIONS)) {
            case ADD_REVIEW: {
                addUserReview(req, resp);

                break;
            }
            case REMOVE_REVIEW: {
                removeReview(req, resp);

                break;
            }
            case UPDATE_REVIEW: {
                updateReview(req, resp);

                break;
            }
            case EDIT_REVIEW: {
                editReview(req, resp);

                break;
            }
        }
    }

    private void editReview(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ReviewDAOObject review = new ReviewDAOObjectBuilder()
                .setDescription(req.getParameter(REVIEW))
                .setProductId(Long.parseLong(req.getParameter(PRODUCT_ID)))
                .setRating(Integer.parseInt(req.getParameter(RATING)))
                .build();

        req.setAttribute(REVIEW, review);
        req.getRequestDispatcher(Configuration.getProperty("page.editReview")).forward(req, resp);
    }

    private void updateReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter(PRODUCT_ID);

        ReviewDAOObject review = new ReviewDAOObjectBuilder()
                .setUserId((Long) req.getSession().getAttribute(USER_ID))
                .setProductId(Long.parseLong(productId))
                .setRating(Integer.parseInt(req.getParameter(RATING)))
                .setCreationDate(new Date(System.currentTimeMillis()))
                .setDescription(req.getParameter(REVIEW))
                .build();

        updateReviewInDAO(review);
        redirectToPage(req, resp, Configuration.getProperty("servlet.productByProductId") + productId);
    }

    private void removeReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter(PRODUCT_ID);

        ReviewDAOObject review = new ReviewDAOObjectBuilder()
                .setProductId(Long.parseLong(productId))
                .setUserId(Long.parseLong(req.getParameter(USER_ID)))
                .build();

        removeReviewFromDAO(review);
        redirectToPage(req, resp, Configuration.getProperty("servlet.productByProductId") + productId);
    }

    private void addUserReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter(PRODUCT_ID);

        ReviewDAOObject review = new ReviewDAOObjectBuilder()
                .setUserId((Long) req.getSession().getAttribute(USER_ID))
                .setProductId(Long.parseLong(productId))
                .setRating(Integer.parseInt(req.getParameter(RATING)))
                .setCreationDate(new Date(System.currentTimeMillis()))
                .setDescription(req.getParameter(REVIEW))
                .build();

        addReviewToDAO(review);
        redirectToPage(req, resp, Configuration.getProperty("servlet.productByProductId") + productId);
    }

    private void updateReviewInDAO(ReviewDAOObject review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .updateReviews(review);
    }

    private void removeReviewFromDAO(ReviewDAOObject review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .deleteReviews(review);
    }

    private void addReviewToDAO(ReviewDAOObject review) {
        DAOFactory.getDAOFactory()
                .getReviewDAO()
                .addReviews(review);
    }
}
