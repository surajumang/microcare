<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
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
    </head>

    <jsp:include page="../header.jsp"/>
    <body>
        <h2>Successfully Done</h2>
        <p>Your details</p>
        <table>
            <tr>
                <td>${member.email}</td>
                <td>${member.firstname}</td>
                <td>${member.lastname}</td>
                <td>${member.zipcode}</td>
            </tr>
        </table>
        <br><br>
            <table>
                <c:forEach var = "entry" items = "${errors}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                        </tr>
                </c:forEach>
            </table>
            <p>Take the user to the login page if it is successful</p>



    </body>
    <jsp:include page="../footer.jsp"/>
</html>
