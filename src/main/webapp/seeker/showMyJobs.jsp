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
                    if(window.confirm("Are you sure you want to Delete this Job")){
                        form.submit();
                    }
                }
            </script>
        </head>
        <body>

             <jsp:include page="/member/header.jsp"/>

             <div class="container">
             <font color="red">
                 <c:choose>
                     <c:when test="${param.jobedit eq 'true'}">
                        <h2>Edit Successful</h2>
                     </c:when>
                     <c:when test="${param.delete eq 'true'}">
                        <h2>Delete Successful</h2>
                     </c:when>
                 </c:choose>
             </font>

            <c:if test="${fn:length(myJobs) > 0}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <td>Title</td>
                            <td>Status</td>
                            <td>Hourly Pay</td>
                            <td>Start Date</td>
                            <td>End Date</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <c:forEach var="job" items="${myJobs}">
                    <tr>
                        <td>${job.title}</td>
                        <td>${job.status}</td>
                        <td>${job.hourlyPay}</td>
                        <fmt:formatDate var="startDate" value="${job.startDateTime}" pattern="yyyy-MM-dd hh:mm"/>
                        <td>${startDate}</td>
                        <fmt:formatDate var="endDate" value="${job.endDateTime}" pattern="yyyy-MM-dd hh:mm"/>
                        <td>${endDate}</td>
                        <td>
                            <c:if test="${job.status != 'EXPIRED'}">
                            <form action="${pageContext.request.contextPath}/seeker/showJobToEdit.do" method="get">
                                <input type="hidden" name="id" value="${job.id}">
                                <input type="submit" name="" value="Edit">
                            </form>
                            </c:if>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/seeker/deleteJob.do" method="get">
                                <input type="hidden" name="id" value="${job.id}">
                                <input type="button" name="" value="Delete" onclick="confirmDeletion(this.form)">
                            </form>

                        </td>
                        <td>
                           <c:if test="${job.status != 'EXPIRED'}">
                            <form action="${pageContext.request.contextPath}/seeker/viewApplications.do" method="get">
                                <input type="hidden" name="id" value="${job.id}">
                                <input type="submit" name="" value="View Applications">
                            </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </c:if>
</div>
        </body>
        <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
