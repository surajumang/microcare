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
        <h2>${SUCCESS}</h2>
                <h2>${FAILURE}</h2>
                <h2>${INVALID}</h2>
     <jsp:include page="/header.jsp"/>
     <p>Page for posting a job. It will show a form to the user.</p>

     <form class="" action="${pageContext.request.contextPath}/seeker/PostJob.do" method="post">
        <label>Job Title</label>
         <input type="text" name="title" placeholder="Job Title" value="${formErrors.title}">
            <c:out value="${errors.title}"/>

         <br><br>
         <label>Hourly Pay</label>
         <input type="text" name="hourlyPay" placeholder="Hourly Pay" value="${formErrors.hourlyPay}">
            <c:out value="${errors.hourlyPay}"/>
         <br><br>

         <label>Start Date</label>
         <input type="text" name="startDate" placeholder="Start Date(YYYY-MM-DD HH:MM)" value="${formErrors.startDate}">
            <c:out value="${errors.startDate}"/>

         <br><br>
         <label>End Date</label>
         <input type="text" name="endDate" placeholder="End Date(YYYY-MM-DD HH:MM)" value="${formErrors.endDate}">
         <c:out value="${errors.currentDate}"/>
          <c:out value="${errors.endDate}"/>

         <br><br>


         <input type="submit" name="" value="Submit">

     </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
