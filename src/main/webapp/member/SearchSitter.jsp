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

        <%--Show all the sitters in a table, also provide a way to
    Search sitter by some criteria(zip code) or (EmailId) --%>

    <p>Will show all the options to
    filter them based on some criteria.</p>
    <form class="" action="SearchSitterServlet.do" method="post">
        <input type="text" name="zipCode" placeholder="Zip Code" value="">
        <input type="text" name="email" placeholder="Email" value="">
        <input type="submit" name="" value="Filter">
    </form>

    <table>
        <c:forEach var ="sitter" items="${sitters}">
        <tr>
            <td>${sitter.firstName}</td>
            <td>${sitter.lastName}</td>
            <td>${sitter.email}</td>
            <td>${sitter.experience}</td>
            <td>${sitter.expectedPay}</td>
            <td>${sitter.phone}</td>
        </tr>
        </c:forEach>
    </table>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
