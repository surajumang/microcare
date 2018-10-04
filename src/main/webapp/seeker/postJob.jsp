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
     <div class="container" style="max-width:500px">

     <html:form action="/seeker/capturePostJob" method="post">
            <div class="form-group">
                 <label>Job Title* </label>
                 <html:text property="title" styleClass="form-control"/>
                 <font color="red"> <html:errors property="title" /> </font>
            </div>
            <div class="form-group">
                 <label>Hourly Pay* </label>
                 <html:text property="hourlyPay" styleClass="form-control" />
                 <font color="red"> <html:errors property="hourlyPay" /> </font>
            </div>
            <div class="form-group">
                 <label>Start Date(YYYY-MM-DD HH:MM)* </label>
                 <html:text property="startDate"  styleClass="form-control"/>
                 <font color="red"> <html:errors property="startDate" /> </font>
            </div>
            <div class="form-group">
                 <label>End Date(YYYY-MM-DD HH:MM)* </label>
                 <html:text property="endDate" styleClass="form-control"/>
                 <font color="red"> <html:errors property="endDate" /> </font>
            </div>
         <html:submit property="submit" styleClass="form-control" value="Submit"/>
     </html:form>
     </div>
    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
