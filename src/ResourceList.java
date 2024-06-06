import java.io.IOException;
import java.io.File;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ResourceList", value = "/resources")
public class ResourceList extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String path = "/usr/local/tomcat/webapps/upload_app/data";
        File directory = new File(path);
        

        File[] files = directory.listFiles();
        request.setAttribute("files", files);
        request.getRequestDispatcher("list_resources.jsp").forward(request, response);
    }
}
