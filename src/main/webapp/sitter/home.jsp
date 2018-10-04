<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
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
     <font color="red">
     <c:choose>
         <c:when test="${param.login eq 'true'}">
            <h2></h2>
         </c:when>
         <c:when test="${param.delete eq 'true'}">
             <h2>Application Deleted</h2>
         </c:when>
         <c:when test="${param.pass eq 'true'}">
             <h2>Password updated</h2>
         </c:when>
         <c:when test="${param.profile eq 'true'}">
             <h2>Profile updated</h2>
         </c:when>
         <c:otherwise>

         </c:otherwise>
     </c:choose>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${INVALID}</h2>
        <h2>${ACCOUNT_STATUS}</h2>

        </font>

      <%-- <jsp:include page="/footer.jsp"/> --%>
    </body>
</html>
