<%-- 
    Document   : itext
    Created on : Dec 16, 2023, 10:35:34 AM
    Author     : suhaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="fc?type=model&page=FileUploadModel" method="POST" enctype="multipart/form-data">
            <h1>Hello World!</h1>
            <input type="file" name="file"/>
            <button>Submit</button>
        </form>
    </body>
</html>
