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
    <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${INVALID}</h2>
    <jsp:include page="/header.jsp"/>
        <p>Page to edit details of the Member</p>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
