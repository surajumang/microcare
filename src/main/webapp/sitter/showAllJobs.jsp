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
        </head>
        <body>

        <jsp:include page="/member/header.jsp"/>
        <c:choose>
              <c:when test="${param.apply eq 'true'}">
                  <h2>Applied successfully </h2>
              </c:when>
              <c:when test="${param.delete eq 'true'}">
                  <h2>Job Deleted</h2>
              </c:when>
        </c:choose>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${INVALID}</h2>
        <h2>${APPSUCCESS}</h2>

    <c:if test="${fn:length(allJobs) > 0}">
        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Pay per Hour</td>
                    <td>Start Date</td>
                    <td>End Date</td>
                    <td>Apply</td>
                </tr>
            </thead>
            <c:forEach var="job" items="${allJobs}">
            <tr>
                <td>${job.title}</td>
                <td>${job.hourlyPay}</td>
                <fmt:formatDate var="startDate" value="${job.startDate}" pattern="yyyy-MM-dd hh:mm"/>
                <td>${startDate}</td>
                <fmt:formatDate var="endDate" value="${job.endDate}" pattern="yyyy-MM-dd hh:mm"/>
                <td>${endDate}</td>
                <td>
                    <%-- Send it to a form so that relevant input can be accepted --%>
                    <form action="${pageContext.request.contextPath}/sitter/jobForApplication.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="submit" name="" value="Apply">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
        </body>
        <jsp:include page="/footer.jsp"/>
</html>
