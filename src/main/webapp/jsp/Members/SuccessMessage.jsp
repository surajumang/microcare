<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
    </head>
    <body>
        <h2>Successfully Done</h2>
        <p>Your details</p>
        <%
		List<String> str = new ArrayList<>();
		str.add("Hello");
		str.add("World");
		str.add("Retire");
		getServletContext().setAttribute("str", str);
	    %>
	<table>

        <c:forEach var ="i" items="${str}">
        <tr>
            <td>${i}</td>
            <td>${i}</td>
            <td>${i}</td>
        </tr>
        </c:forEach>
    </table>

	<c:out value= "${pageContext.request.contextPath}"/>
        <table>

            <tr>
                <td>${member.email}</td>
                <td>${member.firstname}</td>
                <td>${member.lastname}</td>
                <td>${member.zipcode}</td>
            </tr>
        </table>
    </body>
</html>
