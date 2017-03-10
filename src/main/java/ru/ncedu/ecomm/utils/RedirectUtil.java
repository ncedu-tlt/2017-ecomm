package ru.ncedu.ecomm.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectUtil {
    public static void redirectToPage(HttpServletRequest req, HttpServletResponse resp, String path){
        String  location  = req.getContextPath() + path;
        try {
            resp.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
