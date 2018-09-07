<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>

    <h2>Enter Email(partial accepted) to search </h2>
    <form class="" action="${pageContext.request.contextPath}/member/Search.do" method="post">
        <label>Email</label>
        <input type="text" name="email" placeholder="Email" value="${search.email}">
        <c:out value="${errors.email}"/>
        <input type="submit" name="" value="Go">
    </form>

    <table>
        <c:forEach var ="member" items="${members}">
        <c:if test="$member.memberType eq 'SEEKER' ">
        <tr>
            <td>${member.firstName}</td>
            <td>${member.lastName}</td>
            <td>${member.email}</td>
            <td>${member.experience}</td>
            <td>${member.expectedPay}</td>
            <td>${member.phone}</td>
        </tr>
        </c:if>
        <c:if test="$member.memberType eq 'SITTER'">
        <tr>
                    <td>${member.firstName}</td>
                    <td>${member.lastName}</td>
                    <td>${member.email}</td>
                    <td>${member.numberOfChildren}</td>
                    <td>${member.spouseName}</td>
                    <td>${member.phone}</td>
                </tr>
         </c:if>
        </c:forEach>
    </table>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
