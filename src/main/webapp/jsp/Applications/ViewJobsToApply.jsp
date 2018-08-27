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
             <c:forEach var="job" items="jobsAvilable">
                 <thead>

                 </thead>
                 <tbody>
                     <td></td>
                     <td></td>
                     <td></td>
                     <td></td>
                     <td>
                         <form action="ApplyToJob" method="post">
                             <input type="hidden" name="" value="${job.id}">
                             <input type="submit" name="" value="Apply">
                         </form>
                     </td>
                 </tbody>
             </c:forEach>
         </table>

        </body>
</html>
