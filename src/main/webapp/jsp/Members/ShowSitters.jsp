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
        <%
		List<String> str = new ArrayList<>();
		str.add("Hello");
		str.add("World");
		str.add("Retire");
		getServletContext().setAttribute("str", str);
	    %>
        <p>Show all the sitters based on some criteria</p>
        <%--
        Shows a List of sitters. A row represents one Sitter.
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
    --%>
    </body>
</html>
