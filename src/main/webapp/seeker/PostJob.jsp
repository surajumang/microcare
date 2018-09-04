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
        Will provide a form to the user and pass it to the proper servlet.
     --%>
     <jsp:include page="/header.jsp"/>
     <p>Page for posting a job. It will show a form to the user.</p>

     <form class="" action="PostJobServlet.do" method="post">
         <input type="text" name="title" placeholder="Job Title" value="">
         <c:out value="${errors.title}"/>
         <br><br>
         <input type="text" name="hourlyPay" placeholder="Hourly Pay" value="">
         <c:out value="${errors.hourlyPay}"/>
         <br><br>
         <input type="text" name="startDate" placeholder="Start Date" value="">
         <c:out value="${errors.startDate}"/>
         <br><br>
         <input type="text" name="endDate" placeholder="End Date" value="">
         <c:out value="${errors.endDate}"/>
         <br><br>

         <input type="submit" name="" value="Submit">

     </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
