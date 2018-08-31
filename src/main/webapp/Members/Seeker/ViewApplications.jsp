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
                    <td>Title</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Status</td>
                    <td>Expected Pay</td>
                </tr>
            </thead>
            <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.title}</td>
                <td>${app.firstName}</td>
                <td>${app.lastName}</td>
                <td>${app.status}</td>
                <td>${app.expectedPay}</td>
            </tr>
        </c:forEach>
        </table>

    </body>
</html>
