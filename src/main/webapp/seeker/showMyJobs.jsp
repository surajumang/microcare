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
                <title></title>

                <style>
                table {
                    font-family: arial, sans-serif;
                    border-collapse: collapse;
                    width: 100%;
                }

                td, th {
                    border: 1px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                }

                tr:nth-child(even) {
                    background-color: #dddddd;
                }
                </style>

            <script type="text/javascript">
                function confirmDeletion(form) {
                    if(window.confirm("Are you sure you want to Delete this Job")){
                        form.submit();
                    }
                }
            </script>
        </head>
        <body>

         <jsp:include page="./header.jsp"/>
         <c:choose>
             <c:when test="${param.edit eq 'true'}">
                <h2>Edit Successful</h2>
             </c:when>
             <c:when test="${param.delete eq 'true'}">
                <h2>Delete Successful</h2>
             </c:when>
         </c:choose>
         <h2>${param.edit}</h2>
         <h2>${param.delete}</h2>

         <h2>${SUCCESS}</h2>
         <h2>${FAILURE}</h2>
         <h2>${INVALID}</h2>
         <h2>${DELSUCCESS}</h2>
         <h2>${EDITSUCCESS}</h2>

    <c:if test="${fn:length(myJobs) > 0}">
        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Status</td>
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
                <fmt:formatDate var="startDate" value="${job.startDate}" pattern="yyyy-MM-dd hh:mm"/>
                <td>${startDate}</td>
                <fmt:formatDate var="endDate" value="${job.endDate}" pattern="yyyy-MM-dd hh:mm"/>
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
        </body>
        <jsp:include page="/footer.jsp"/>
</html>
