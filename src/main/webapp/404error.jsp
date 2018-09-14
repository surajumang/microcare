<%@ page contentType="text/html;charset=UTF-8" isErrorPage = "true" language="java" %>
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
        <h2>${errorMessage}</h2>
        <h1>The rquested page could not be found</h1>
        <a href="${pageContext.request.contextPath}/member/home.do">Click here to go back</a>
    </body>
</html>
