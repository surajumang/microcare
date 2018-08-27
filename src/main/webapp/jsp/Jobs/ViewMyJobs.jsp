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
            <%--
            View all jobs created by this seeker.

            The jobs will also have an Edit button and a ViewApplicants button.

            The servlet will pass a List of Jobs inside the scope/request or session.
            object.
         --%>
        <p>Show all the jobs created by the user.</p>
        <table>
            <thead>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            <c:forEach var="job" items="${myjobs}">
            <tr>
                <td>${job}</td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <form action="index.html" method="post">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="submit" name="" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="" value="${job.id}">
                        <input type="submit" name="" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>

        </body>
</html>
