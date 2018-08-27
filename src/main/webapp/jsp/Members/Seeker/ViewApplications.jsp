<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
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
        <h2>html table</h2>
        <table>
            <thead>
                <tr>
                    <td>Email</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Title</td>
                    <td>Hourly Pay</td>
                    <td>Apply Field</td>
                </tr>
            </thead>
            <c:forEach var="job" items="${allJobs}">
            <tr>
                <td>${job.email}</td>
                <td>${job.firstName}</td>
                <td>${job.lastName}</td>
                <td>${job.title}</td>
                <td>${job.hourlyPay}</td>
                <td>${job.startDate}</td>
                <td>${job.endDate}</td>
                <td>
                    <form action="index.html" method="post">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="submit" name="" value="Apply">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>

    </body>
</html>
