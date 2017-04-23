package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "ProductComparatorServlet", urlPatterns = {"/compare"})
public class ProductComparatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }


    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        redirectToPage(req, resp, Configuration.getProperty("page.compare"));
    }
}
