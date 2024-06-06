
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
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;

@WebServlet(name = "UploadServlet", urlPatterns = { "/uploadnewfile" })
@MultipartConfig(location = "/tmp", maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String upload_path = "/usr/local/tomcat/webapps/upload_app/data";
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        filePart.write(upload_path + File.separator + fileName);

        if (!validate(fileName)) {
            writer.println(fileName + " not allowed");
            writer.println("only .png and .jpg are allowed");
            File delete = new File(upload_path + File.separator + fileName);
            if (delete.exists()) {
                
                delete.delete();
                writer.println("file deleted");
            }
            request.getRequestDispatcher("index.html").include(request, response);
        }
        else
            writer.println("The file uploaded sucessfully.");
        // for (Part part : request.getParts()) {
        // part.write("C:\\upload\\" + fileName);
        // }
    }

    private boolean validate(String filename) {
        filename = filename.toLowerCase();
        try {
            TimeUnit.MILLISECONDS.sleep(4);; // represent some validate that took more time
        } catch (InterruptedException Ite) {
            Ite.printStackTrace();
        }
        if (!filename.contains(".jpg") && !filename.contains(".png"))
            return false;
        return true;
    }
}