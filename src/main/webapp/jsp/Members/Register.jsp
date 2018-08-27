<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style type="text/css">
            form {
                text-align: center;
            }
        </style>
    </head>
    <body>

        <form action="${pageContext.request.contextPath}/Members/Seeker/Registration.jsp" method="post">
            <input type="submit" name="" value="Submit">
        </form>
        <form action="${pageContext.request.contextPath}/Members/Sitter/Registration.jsp" method="post">            
            <input type="submit" name="" value="Submit">
        </form>

    </body>
</html>
