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
        <h2>Password can be rest from here</h2>
        <c:out value="${FAILURE}"/>
        <c:if test="${SUCCESS eq 'VERIFIED'}">
            <form action="${pageContext.request.contextPath}/visitor/updatePassword.do" method="get">
                <label>Enter Password</label>
                <input type="password" name="password" value="">
                <c:out value="${errors.password}"/>
                <label>Re-enter Password</label>
                <input type="password" name="password2" value="">
                <c:out value="${errors.password2}"/>

                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="token" value="${token}">
                <input type="submit" value="Submit">
            </form>
        </c:if>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
