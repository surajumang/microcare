<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
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
        <h2>Password reset link will be sent to your mail</h2><br>
        <h3>Please enter your email to continue </h3>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${OTHER}</h2>

        <form action="${pageContext.request.contextPath}/visitor/login.do" method="get">
             <input type="submit" name="home" value="Home">
        </form>

        <form action="${pageContext.request.contextPath}/visitor/captureEmail.do" method="get">
            <label>Email </label>
            <input type="text" name="email" value="${email}">
            <c:out value="${emailError}"/>
            <input type="submit" value="Submit">
        </form>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
