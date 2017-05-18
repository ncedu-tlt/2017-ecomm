package ru.ncedu.ecomm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@WebServlet("/uploadImage")
@MultipartConfig
public class UserImageServlet extends HttpServlet {

    //private final static String IMAGE_SAVE_URL_TEST = System.getProperty( "catalina.base" ) + "/webapps/userImage";
    private final static String IMAGE_SAVE_URL = "/images/useravatars/";
    private final static byte[] BYTES = new byte[1024];
    private final static int START_OFFSET_IN_THE_DATA = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imageUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imageUser(req, resp);
    }

    private void imageUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part file = request.getPart("uploadFile");
            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
            String saveDirectory = this.getServletContext().getRealPath(File.separator) + IMAGE_SAVE_URL;
            OutputStream outputStream = null;
            InputStream inputStream = null;
            final PrintWriter writer = response.getWriter();
            try {
                File folder = new File(saveDirectory);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                File imageFile = new File(saveDirectory + File.separator + fileName);
                if (!imageFile.exists()){
                    outputStream = new FileOutputStream(imageFile);
                    inputStream = file.getInputStream();

                    int bytesToWrite = 0;

                    while ((bytesToWrite = inputStream.read(BYTES)) != -1) {
                        outputStream.write(BYTES, START_OFFSET_IN_THE_DATA, bytesToWrite);
                    }
                    writer.println("New file " + fileName + " created at " + saveDirectory);
                }
            } catch (FileNotFoundException fne) {
                writer.println("New file not created");
                writer.println("<br/> ERROR: " + fne.getMessage());
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}