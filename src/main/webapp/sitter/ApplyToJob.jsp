<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>

</style>
    </head>

    <body>
    <jsp:include page="/header.jsp"/>

        <div class="">
            <form  action="${pageContext.request.contextPath}/sitter/ApplyToJob.do" method="post">

                <input type="submit" name="" value="Submit">
            </form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
