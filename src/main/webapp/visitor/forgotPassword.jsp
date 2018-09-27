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
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container" style="max-width:500px">
            <h2>Password reset link will be sent to your mail</h2><br>
            <h3>Please enter your email to continue </h3>
            <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${OTHER}</h2>

            <div class="btn btn-info" role="button">
              <html:link action="/visitor/login">Login</html:link>
            </div>

            <form action="${pageContext.request.contextPath}/visitor/captureEmail.do" method="get">
                <div class="form-group">
                    <label>Email </label>
                    <input type="text" name="email" value="${email}" class="form-control">
                    <c:out value="${emailError}"/>
                </div>
                <input type="submit" value="Submit" class="form-control">
            </form>

        </div>

    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
