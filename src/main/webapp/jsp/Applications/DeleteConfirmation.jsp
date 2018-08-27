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
        <%--Ask for a confirmation to the user that the account will be deleted --%>
        <%--Show User's detail and then   --%>
        <p>Are you Sure you want to Delete your Account.</p>

        <input type="button" name="delete" value="Delete" onclick=""/>
        <input type="button" name="cancel" value="Cancel" onclick=""/>

    </body>
</html>
