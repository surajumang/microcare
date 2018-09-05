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
        <h2>Join in as </h2>
        <form action="${pageContext.request.contextPath}/visitor/SeekerRegistration.jsp" method="post">
            <input type="submit" name="" value="Seeker">
        </form>
        <br><br>
        <form action="${pageContext.request.contextPath}/visitor/SitterRegistration.jsp" method="post">
            <input type="submit" name="" value="Sitter">
        </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
