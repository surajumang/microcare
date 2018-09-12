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
        <title></title>
    </head>
    <body>

    <jsp:include page="/header.jsp"/>
    <h2>${SUCCESS}</h2>
                <h2>${FAILURE}</h2>
                <h2>${INVALID}</h2>
        <p>First show the current information related to the Job in a
        filled form which the user can edit.</p>

        <form action="${pageContext.request.contextPath}/seeker/EditJob.do" method="post">

            <input type="text" name="title" placeholder="Job Title" value="${editJob.title}">
            <c:out value="${errors.title}"/><br>

            <input type="text" name="hourlyPay" placeholder="Hourly Pay" value="${editJob.hourlyPay}">
            <c:out value="${errors.hourlyPay}"/><br>

            <input type="text" name="startDate" placeholder="Start Date" value="${editJob.startDate}">
            <c:out value="${errors.startDate}"/><br>

            <input type="text" name="endDate" placeholder="End Date" value="${editJob.endDate}">
            <c:out value="${errors.endDate}"/><br>

            <input type="hidden" name="id" value="${editJob.id}">

            <input type="submit" name="" value="Submit">
        </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
