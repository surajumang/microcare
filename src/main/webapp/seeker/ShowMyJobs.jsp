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
                    if(window.confirm("Are you sure you want to Delete this Job")){
                        form.submit();
                    }
                }
            </script>
        </head>
        <body>
            <h2>${SUCCESS}</h2>
                    <h2>${FAILURE}</h2>
                    <h2>${INVALID}</h2>
         <jsp:include page="/header.jsp"/>
        <p>Show all the jobs created by the user.</p>


        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Status</td>
                    <td>Start Date</td>
                    <td>End Date</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            <c:forEach var="job" items="${myJobs}">
            <tr>
                <td>${job.title}</td>
                <td>${job.status}</td>
                <td>${job.startDate}</td>
                <td>${job.endDate}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/seeker/ShowJobToEdit.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="submit" name="" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/seeker/DeleteJob.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">
                        <input type="button" name="" value="Delete" onclick="confirmDeletion(this.form)">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/seeker/ViewApplicants.do" method="get">
                        <input type="hidden" name="id" value="${job.id}">

                        <input type="submit" name="" value="View Applications">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
        </body>
        <jsp:include page="/footer.jsp"/>
</html>
