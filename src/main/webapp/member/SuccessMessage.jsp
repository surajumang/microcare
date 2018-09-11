<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
    </head>

    <jsp:include page="/header.jsp"/>
    <body>
            <p>Registration Successful</p>
            You Can now login.
            <a href="{pageContext.request.contextPath}/index.jsp">Login</a>
    </body>
   <jsp:include page="/footer.jsp"/>
</html>
