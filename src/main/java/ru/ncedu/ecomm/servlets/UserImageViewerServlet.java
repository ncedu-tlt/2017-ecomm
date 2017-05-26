package ru.ncedu.ecomm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import static ru.ncedu.ecomm.servlets.UserImageWriterServlet.IMAGE_SAVE_URL;

@WebServlet("/image/*")

public class UserImageViewerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File fileTest = new File(IMAGE_SAVE_URL, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(fileTest.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileTest.getName() + "\"");
        Files.copy(fileTest.toPath(), response.getOutputStream());
    }
}

