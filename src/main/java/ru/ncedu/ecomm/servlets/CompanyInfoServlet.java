package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.models.CompanyInfoViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CompanyInfoViewModelBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "CompanyInfo", urlPatterns = {"/companyInfo"})
public class CompanyInfoServlet extends HttpServlet {

    private void process(HttpServletRequest request) {
        CompanyInfoViewModel companyInfo = new CompanyInfoViewModelBuilder()
                .setSocials(getDAOFactory().getPropertyDAO().getSocials())
                .setEmail(getDAOFactory().getPropertyDAO().getPropertyById("Email").getValue())
                .setPhone(getDAOFactory().getPropertyDAO().getPropertyById("Phone").getValue())
                .setRights(getDAOFactory().getPropertyDAO().getPropertyById("Rights").getValue())
                .build();
        request.setAttribute("companyInfo", companyInfo);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }

}
