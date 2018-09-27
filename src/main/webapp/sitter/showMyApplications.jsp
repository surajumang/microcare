<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <script type="text/javascript">
            function confirmDeletion(form) {
                if (window.confirm("Are you sure you want to Delete this application")){
                    form.submit();
                }
            }
        </script>
    </head>
    <body>
    <jsp:include page="/member/header.jsp"/>

    <div class="container">
        <c:choose>
             <c:when test="${param.login eq 'true'}">
                <h2></h2>
             </c:when>
             <c:when test="${param.delete eq 'true'}">
                 <h2>Application Deleted</h2>
             </c:when>
         </c:choose>
       <h2>${DELSUCCESS}</h2>
       <h2>${SUCCESS}</h2>
       <h2>${FAILURE}</h2>
           <c:if test="${fn:length(allMyApplications) > 0}">
               <table class="table table-striped">
                    <thead>
                        <tr>
                            <td>Title</td>
                            <td>Pay per Hour</td>
                            <td>Expected Pay</td>
                            <td>Status</td>
                            <td></td>
                        </tr>
                    </thead>
                    <c:forEach var="app" items="${allMyApplications}">
                        <tr>
                            <td>${app.job.title}</td>
                            <td>${app.job.hourlyPay}</td>
                            <td>${app.expectedPay}</td>
                            <td>${app.status}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/sitter/deleteApplication.do" method="get">
                                    <input type="hidden" name="id" value="${app.id}">
                                    <input type="button" name="" value="Delete" onclick="confirmDeletion(this.form)">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
           </c:if>
       </div>
    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
