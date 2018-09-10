<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
    <jsp:include page="/header.jsp"/>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${INVALID}</h2>

        <form action="${pageContext.request.contextPath}/seeker/EditJob.do" method="post">

            <input type="text" name="title" placeholder="Job Title" value="${editJob.title}">
            <c:out value="${errors.title}"/><br>

            <input type="text" name="hourlyPay" placeholder="Hourly Pay" value="${editJob.hourlyPay}">
            <c:out value="${errors.hourlyPay}"/><br>

            <fmt:formatDate var="startDate" value="${editJob.startDate}" pattern="yyyy-MM-dd hh:mm"/>
            <input type="text" name="startDate" placeholder="Start Date" value="${startDate}">
            <c:out value="${errors.startDate}"/><br>

            <fmt:formatDate var="endDate" value="${editJob.endDate}" pattern="yyyy-MM-dd hh:mm"/>
            <input type="text" name="endDate" placeholder="End Date" value="${endDate}">
            <c:out value="${errors.endDate}"/><br>
            <c:out value="${errors.currentDate}"/><br>

            <input type="hidden" name="id" value="${editJob.id}">
            <input type="submit" name="" value="Submit">
        </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
