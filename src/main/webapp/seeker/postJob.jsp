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
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>

     <jsp:include page="/member/header.jsp"/>
     <h2>${SUCCESS}</h2>
     <h2>${FAILURE}</h2>
     <h2>${INVALID}</h2>


     <html:form action="/seeker/capturePostJob" method="post">

        <table align="center">
            <tr>
                 <td><label>Job Title* </label></td>
                 <td><html:text property="title" /></td>
                 <td><font color="red"> <html:errors property="title" /> </font></td>
            </tr>
            <tr>
                 <td><label>Hourly Pay* </label></td>
                 <td><html:text property="hourlyPay" /></td>
                 <td><font color="red"> <html:errors property="hourlyPay" /> </font></td>
            </tr>
            <tr>
                 <td><label>Start Date(YYYY-MM-DD HH:MM)* </label></td>
                 <td><html:text property="startDate"  /></td>
                 <td><font color="red"> <html:errors property="startDate" /> </font></td>
            </tr>
            <tr>
                 <td><label>End Date(YYYY-MM-DD HH:MM)* </label></td>
                 <td><html:text property="endDate" /></td>
                 <td><font color="red"> <html:errors property="endDate" /> </font></td>
            </tr>
        </table>

        <center> <html:submit property="submit" value="Submit"/> </center>

     </html:form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
