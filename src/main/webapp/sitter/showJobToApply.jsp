<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function confirmDeletion(form) {
                if(window.confirm("Are you sure you want to Delete this Job")){
                    form.submit();
                }
            }
        </script>
    </head>

    <body>

    <jsp:include page="/member/header.jsp"/>
    <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>Title</td>
                        <td>Pay per Hour</td>
                        <td>Start Date</td>
                        <td>End Date</td>
                        <td>Expected Pay</td>
                    </tr>
                </thead>
                <tr>
                    <td>${job.title}</td>
                    <td>${job.hourlyPay}</td>

                    <fmt:formatDate var="startDate" value="${job.startDate}" pattern="yyyy-MM-dd hh:mm"/>
                    <td>${startDate}</td>

                    <fmt:formatDate var="endDate" value="${job.endDate}" pattern="yyyy-MM-dd hh:mm"/>
                    <td>${endDate}</td>

                    <td>
                        <html:form action="/sitter/captureApplication" method="get">
                            <html:text property="expectedPay" />
                            <font color="red"> <html:errors property="expectedPay" /> </font>

                            <html:hidden property="jobId" />
                            <html:hidden  property="sitterId"/>
                            <html:submit value="Apply"/>
                        </html:form>
                    </td>
                </tr>

              </table>
        </div>

    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
