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
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style type="text/css">
            .right{
                float:right;
            }
        </style>
    </head>
    <body>
        <div class="container" style="max-width:500px">
            <h1>Welcome to Home Job Marketplace</h1>

                <c:choose>
                    <c:when test="${param.closed eq 'true'}">
                       <font color="green"><h2>Account Closed</h2></font>
                    </c:when>
                    <c:when test="${param.login eq 'not'}">
                       <font color="red"> <h2>You must log in first</h2></font>
                    </c:when>
                    <c:when test="${param.login eq 'false'}">
                        <font color="red"><h2>Incorrect Email or password</h2></font>
                    </c:when>
                    <c:when test="${param.pass eq 'true'}">
                        <font color="green"><h2>Password reset successful</h2></font>
                    </c:when>
                    <c:when test="${param.mail eq 'true'}">
                        <font color="green"><h2>Password reset link sent</h2></font>
                    </c:when>
                    <c:when test="${param.pass eq 'failed'}">
                        <font color="red"><h2>Password reset Failed</h2></font>
                    </c:when>
                    <c:when test="${param.token eq 'invalid'}">
                        <font color="red"><h2>Invalid Token</h2></font>
                    </c:when>
                </c:choose>

            <html:form action="/visitor/captureLogin" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <html:text property="email" styleClass="form-control" /><br>
                    <font color="red"> <html:errors property="email" /> </font>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <html:password property="password" styleClass="form-control" /><br>
                    <font color="red"> <html:errors property="password" /> </font>
                </div>
                <html:submit property="submit" styleClass="form-control" value="Submit"/>
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
