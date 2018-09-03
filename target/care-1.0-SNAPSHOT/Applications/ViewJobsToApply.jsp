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
            Similar to the view Applications jsp it will show all the
            available jobs to the User

            All the jobs will have a button so that the user can apply to it.
            Clicking the button should take the Sitter to a confirmation page.
            The confirmation page will show all the details of the job to
            the Sitter and allow the user to either cancel or to apply for
            the Job.

            The servlet will pass a List of Jobs inside the scope/request
            object.
         --%>
         <table>
             <thead>
                 <tr>
                     <td>Title</td>
                     <td>Hourly Pay</td>
                     <td>Start Date</td>
                     <td>End Date</td>
                     <td>Apply Field</td>
                 </tr>
             </thead>
             <c:forEach var="job" items="${allJobs}">
             <tr>
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
