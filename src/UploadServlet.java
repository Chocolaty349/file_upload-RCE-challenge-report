
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.PrintWriter;
import java.lang.String;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;

@WebServlet(name = "UploadServlet", urlPatterns = { "/uploadnewfile" })
@MultipartConfig(location = "/tmp", maxFileSize = 1024 * 1024 * 50, // 50MB
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String upload_path = "/opt/tomcat/apache-tomcat-10.1.23/webapps/upload_app/data";
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        if (!validate(fileName)) {
            writer.println(fileName + " not allowed");
            writer.println("only .png and .jpg are allowed");
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            filePart.write(upload_path + File.separator + fileName);
            writer.println("The file uploaded sucessfully.");
        }
        // for (Part part : request.getParts()) {
        // part.write("C:\\upload\\" + fileName);
        // }
    }

    private boolean validate(String filename) {
        filename = filename.toLowerCase();
        if (!filename.contains(".jpg") && !filename.contains(".png"))
            return false;
        return true;
    }
}