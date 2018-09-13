<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
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

        <jsp:include page="./header.jsp"/>


       <h2>${DELSUCCESS}</h2>
       <h2>${SUCCESS}</h2>
       <h2>${FAILURE}</h2>
        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Pay per Hour</td>
                    <td>Expected Pay</td>
                    <td>Status</td>
                    <td></td>
                </tr>
            </thead>
            <c:forEach var="app" items="${allMyApplications}">
            <tr>
                <td>${app.job.title}</td>
                <td>${app.job.hourlyPay}</td>
                <td>${app.expectedPay}</td>
                <td>${app.status}</td>

                <td>

                    <form action="${pageContext.request.contextPath}/sitter/deleteApplication.do" method="get">
                        <input type="hidden" name="id" value="${app.id}">
                        <input type="button" name="" value="Delete" onclick="confirmDeletion(this.form)">
                    </form>

                </td>
            </tr>
        </c:forEach>
        </table>
        </body>
        <jsp:include page="/footer.jsp"/>
</html>
