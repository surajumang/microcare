<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
        <p>First show the current information related to the Job in a
        filled form which the user can edit.</p>

        <form action="PostJobServlet.do" method="post">
            <input type="text" name="title" placeholder="Job Title" value="${editJob.title}">
            <c:out value="${errors.title}"/><br>

            <input type="text" name="hourlyPay" placeholder="Hourly Pay" value="${editJob.hourlyPay}">
            <c:out value="${errors.hourlyPay}"/><br>

            <input type="text" name="startDate" placeholder="Start Date" value="${editJob.startDate}">
            <c:out value="${errors.startDate}"/><br>

            <input type="text" name="endDate" placeholder="End Date" value="${editJob.endDate}">
            <c:out value="${errors.endDate}"/><br>

            <input type="submit" name="" value="Submit">
        </form>

    </body>
</html>
