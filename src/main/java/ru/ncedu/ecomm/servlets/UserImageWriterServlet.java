package ru.ncedu.ecomm.servlets;

import org.apache.log4j.Logger;

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

public class UserImageWriterServlet extends HttpServlet {

    protected final static String IMAGE_SAVE_URL = System.getProperty( "catalina.base" ) + "/userImage/";
    private static final Logger LOG = Logger.getLogger(UserImageWriterServlet.class);
    private final static byte[] BYTES = new byte[1024];
    private final static int START_OFFSET_IN_THE_DATA = 0;
    private final static String UPLOAD_FILE = "uploadFile";

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
            Part file = request.getPart(UPLOAD_FILE);
            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
            OutputStream outputStream = null;
            InputStream inputStream = null;
            final PrintWriter writer = response.getWriter();
            try {
                File folder = new File(IMAGE_SAVE_URL);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                File imageFile = new File(IMAGE_SAVE_URL + File.separator + fileName);
                if (!imageFile.exists()){
                    outputStream = new FileOutputStream(imageFile);
                    inputStream = file.getInputStream();

                    int bytesToWrite;

                    while ((bytesToWrite = inputStream.read(BYTES)) != -1) {
                        outputStream.write(BYTES, START_OFFSET_IN_THE_DATA, bytesToWrite);
                    }
                }
            } catch (FileNotFoundException fne) {
                LOG.error(fne);
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