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


            <h2>Password can be reset from here</h2>
            <c:out value="${FAILURE}"/>

            <div class="btn btn-info" role="button">
              <html:link action="/visitor/login">Login</html:link>
            </div>
            <c:if test="${not empty passwordResetForm.token}">
                <html:form action="/visitor/captureResetPassword" method="post">
                    <div class="form-group">
                        <label>Enter Password</label>
                        <input type="password" name="password" value="" class="form-control">
                        <font color="red"> <html:errors property="password" /> </font>
                    </div>
                    <div class="form-group">
                        <label>Re-enter Password</label>
                        <input type="password" name="password2" value="" class="form-control">
                        <font color="red"> <html:errors property="password2" /> </font>
                    </div>
                    <html:hidden property="id" />
                    <html:hidden property="token" />
                    <html:submit value="Submit" styleClass="form-control"/>
                </html:form>
            </c:if>

        </div>

    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
