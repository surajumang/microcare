<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
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

    <jsp:include page="./header.jsp"/>
    <h2>${SUCCESS}</h2>
                <h2>${FAILURE}</h2>
                <h2>${INVALID}</h2>


    <c:if test="${fn:length(getApplications) > 0}">
        <center><h2>${getApplications[0].job.title}</h2></center>

        <table>
            <thead>
                <tr>                    
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Status</td>
                    <td>Expected Pay</td>
                </tr>
            </thead>
            <c:forEach var="app" items="${getApplications}">
            <tr>
                <td>${app.sitter.firstName}</td>
                <td>${app.sitter.lastName}</td>
                <td>${app.status}</td>
                <td>${app.expectedPay}</td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
