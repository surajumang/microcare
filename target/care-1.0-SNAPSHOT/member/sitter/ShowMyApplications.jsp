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
                <script type="text/javascript">
                    function confirmDeletion(form) {
                        if (window.confirm("Are you sure you want to Delete this application")){
                            form.submit();
                        }
                    }
                </script>
        </head>
        <body>

        <p>Show all the active applications from this sitter.</p>


        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Pay per Hour</td>
                    <td>Expected Pay</td>
                    <td>Status</td>
                    <td>Delete</td>
                </tr>
            </thead>
            <c:forEach var="app" items="${allMyApplications}">
            <tr>
                <td>${app.title}</td>
                <td>${app.hourlyPay}</td>
                <td>${app.expectedPay}</td>
                <td>Status</td>

                <td>
                    <form action="DeleteApplication.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="button" name="" value="Delete" onclick="confirmDeletion(this.form)">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>

        </body>
</html>
