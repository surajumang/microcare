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
    <jsp:include page="/header.jsp"/>
        <p>First show the current information related to the Job in a
        filled form which the user can edit.</p>



            <table>
                        <thead>
                            <tr>
                                <td>Title</td>
                                <td>Pay per Hour</td>
                                <td>Start Date</td>
                                <td>Expected Pay</td>
                            </tr>
                        </thead>
                        <tr>
                            <td>${job.title}</td>
                            <td>${job.hourlyPay}</td>
                            <td>${job.startDate}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/sitter/ApplyToJob.do" method="get">
                                    <input type="text" name="expectedPay" value="">
                                    <c:out value="${errors.expectedPay}"/>
                                    <input type="hidden" name="id" value="${job.id}">
                                    <input type="submit" name="" value="Apply">
                                </form>
                            </td>
                        </tr>

              </table>



    </body>
    <jsp:include page="/footer.jsp"/>
</html>
