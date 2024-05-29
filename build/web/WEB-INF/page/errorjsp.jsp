<%-- 
    Document   : newjsp
    Created on : Sep 23, 2023, 2:49:20 PM
    Author     : suhaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String error = request.getParameter("error");
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1> ${error}</h1>
    </body>
</html>
