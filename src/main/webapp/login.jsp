<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>

        <style type="text/css">
            form {
                text-align: center;
            }
            input {
                width: 100px;
            }
            .right{
                float:right;
            }
        </style>
    </head>
    <body>
        <div class="form1" al>
            <h1>Welcome to Home Job Marketplace</h1>
            <c:choose>
                <c:when test="${param.closed eq 'true'}">
                   <h2>Account Closed</h2>
                </c:when>
                <c:when test="${param.login eq 'false'}">
                    <h2>Invalid credentials</h2>
                </c:when>
                <c:when test="${param.pass eq 'false'}">
                    <h2>Password reset successful</h2>
                </c:when>

            </c:choose>

            <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${OTHER}</h2>

            <h2>${message}</h2>
            <html:form action="/visitor/captureLogin" method="post">
                <label>Email</label>
                <html:text property="email"/><br>
                <font color="red"> <html:errors property="email" /> </font>

                <label>Password</label>
                <html:password property="password"/><br>
                <font color="red"> <html:errors property="password" /> </font>
                <html:submit property="submit" value="Submit"/>

            </html:form>

        </div>

        <div class="right">
        <br>
                    New User
                    <a href="${pageContext.request.contextPath}/visitor/registration.do">Register Here</a>
                    <br>
                    <a href="${pageContext.request.contextPath}/visitor/forgotPassword.jsp">Forgot Passwod</a>
        </div>
    </body>
</html>
