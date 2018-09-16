<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>

     <jsp:include page="./header.jsp"/>
     <h2>${SUCCESS}</h2>
                     <h2>${FAILURE}</h2>
                     <h2>${INVALID}</h2>

    <html:errors/>
     <html:form action="/seeker/capturePostJob" method="post">
        <label>Job Title</label>
         <html:text property="title"/>
         <br><br>

         <label>Hourly Pay</label>
         <html:text property="hourlyPay"/>
         <br><br>

         <label>Start Date</label>
         <html:text property="startDate"/>
           <br><br>

         <label>End Date</label>
         <html:text property="endDate"/>
       <br><br>
         <html:submit property="submit" value="Submit"/>

     </html:form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
