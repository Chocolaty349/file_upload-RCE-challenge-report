import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ViewFile", value = "/viewFile")
public class viewFileServlet extends HttpServlet {

    private final String path = "/opt/tomcat/apache-tomcat-10.1.23/webapps/upload_app/data";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("fileName");

        File file = new File(path + File.separator + fileName);
        if (file.exists() && file.isFile()) {
            try (PrintWriter out = response.getWriter()) {
                Files.lines(Paths.get(file.getPath())).forEach(out::println);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
    }
}
