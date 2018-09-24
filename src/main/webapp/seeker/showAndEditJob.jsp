<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

        <html:form action="/seeker/captureEditJob" method="post">
            <table align="center">

                <tr>
                     <td><label>Job Title</label></td>
                     <td><html:text property="title" /></td>
                     <td><font color="red"> <html:errors property="title" /> </font></td>
                </tr>
                <tr>
                     <td><label>Hourly Pay</label></td>
                     <td><html:text property="hourlyPay" /></td>
                     <td><font color="red"> <html:errors property="hourlyPay" /> </font></td>
                </tr>
                <tr>
                     <td><label>Start Date(Time)</label></td>
                     <td><html:text property="startDate"  /></td>

                     <td><font color="red"> <html:errors property="startDate" /> </font></td>
                </tr>
                <tr>
                     <td><label>End Date(Time)</label></td>
                     <td><html:text property="endDate" /></td>
                     <td><font color="red"> <html:errors property="endDate" /> </font></td>
                </tr>
            </table>
            <html:hidden property="id" />
            <center> <html:submit property="submit" value="Submit"/></center>
        </html:form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
