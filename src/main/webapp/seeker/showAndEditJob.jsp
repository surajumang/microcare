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
            <table>

                <tr>
                     <td><label>Job Title</label></td>
                     <td><html:text property="title" value="${editJob.title}"/></td>
                </tr>
                <tr>
                     <td><label>Hourly Pay</label></td>
                     <td><html:text property="hourlyPay" value="${editJob.hourlyPay}"/></td>
                </tr>
                <tr>
                     <td><label>Start Date(Time)</label></td>
                     <td><html:text property="startDate" value="${editJob.startDate}" /></td>
                </tr>
                <tr>
                     <td><label>End Date(Time)</label></td>
                     <td><html:text property="endDate" value="${editJob.endDate}"/></td>
                </tr>
            </table>
            <html:hidden property="id" value="${editJob.id}"/>
            <html:submit property="submit" value="Submit"/>
        </html:form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
