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
        <h2>${SUCCESS}</h2>
                <h2>${FAILURE}</h2>
                <h2>${INVALID}</h2>

      <form  action="ShowSitters.do" method="post">
          <input type="text" name="emailPattern" value="" placeholder="Enter Email">
          <input type="text" name="zipCode" value="" placeholder="Zip Code">
          <input type="submit" name="" value="Apply Filters">
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
