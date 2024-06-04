<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Directory Listing</title>
</head>
<body>
    <h1>Directory Listing</h1>
    <ul>
        <%
            File[] file_list = (File[]) request.getAttribute("files");
            for (File file : file_list){
                out.println("<li><a href=\"viewFile?fileName=" + file.getName() +"\">" + file.getName() +"</a></li>");
            }
        %>
    </ul>
</body>
</html>
