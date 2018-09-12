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
        <jsp:include page="/header.jsp"/>
        <h3></h3>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${OTHER}</h2>

        <form action="${pageContext.request.contextPath}/member/ResetPassword.do" method="get">
            <label>Current Password</label>
            <input type="password" name="currentPassword" value="">
            <br>
             <label>New Password</label>
             <input type="Password" name="password" value="">
             <c:out value="${errors.password}"/>
             <br>
             <label>Re-Enter Password</label>
             <input type="Password" name="password2" value="">
             <c:out value="${errors.password2}"/>

            <input type="submit" value="Submit">
        </form>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
