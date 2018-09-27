<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<!DOCTYPE html>
<html>
<head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
    <body>

    <jsp:include page="/member/header.jsp"/>
        <div class="container">

        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${INVALID}</h2>

        <c:if test="${fn:length(getApplications) > 0}">
            <center><h2>${getApplications[0].job.title}</h2></center>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Status</td>
                        <td>Expected Pay</td>
                    </tr>
                </thead>
                <c:forEach var="app" items="${getApplications}">
                    <tr>
                        <td>${app.sitter.firstName}</td>
                        <td>${app.sitter.lastName}</td>
                        <td>${app.status}</td>
                        <td>${app.expectedPay}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        </div>
    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
