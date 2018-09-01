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

        <p>Show all the active jobs available.</p>


        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Pay per Hour</td>
                    <td>Start Date</td>
                    <td>Apply</td>
                </tr>
            </thead>
            <c:forEach var="job" items="${allJobs}">
            <tr>
                <td>${job.title}</td>
                <td>${job.hourlyPay}</td>
                <td>${job.startDate}</td>

                <td>
                    <form action="ApplyToJob.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">

                        <input type="submit" name="" value="Apply">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>

        </body>
</html>