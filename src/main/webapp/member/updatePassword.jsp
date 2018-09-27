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
        <jsp:include page="/member/header.jsp"/>
        <div class="container" style="max-width:500px">
            <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${OTHER}</h2>

            <html:form action="/member/captureResetPassword" method="post">
                    <div class="form-group">
                        <label>Current Password</label>
                        <input type="password" name="currentPassword" class="form-control"/>
                        <font color="red"> <html:errors property="currentPassword" /> </font>
                    </div>
                    <div class="form-group">
                        <label>New Password</label>
                        <input type="password" name="password" class="form-control"/>
                        <font color="red"> <html:errors property="password" /> </font>
                    </div>
                    <div class="form-group">
                        <label>Re-Enter Password</label>
                        <input type="password" name="password2" class="form-control"/>
                        <font color="red"> <html:errors property="password2" /> </font>
                    </div>

                    <html:submit value="Submit" styleClass="form-control"/>
            </html:form>
        </div>
    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
