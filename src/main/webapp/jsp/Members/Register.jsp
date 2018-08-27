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
        <style type="text/css">
            form {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Enter Your Details</h1>
        <table>
            <c:forEach var = "entry" items = "${errors}">
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                    </tr>
            </c:forEach>
        </table>
        <form class="Seeker" action="RegistrationServlet.do" method="post">
            <input type="text" name="email" placeholder="Email" >
            <c:out value="${errors.email}"/>
            <br><br>
            <input type="text" name="firstname" placeholder="First Name" >
            <c:out value="${errors.name}"/>
            <br><br>
            <input type="text" name="lastname" placeholder="Last Name">
            <c:out value="${errors.name}"/>
            <br><br>
            <input type="text" name="address" placeholder="Address">
            <c:out value="${errors.name}"/>
            <br><br>
                <input type="text" name="phone" placeholder="Phone">
                <c:out value="${errors.phone}"/>
                <br><br>
            <input type="text" name="zipcode" placeholder="Zip Code(Six digits)">
            <c:out value="${errors.number}"/>
            <br><br>
            <input type="password" name="password" placeholder="Password" >
            <c:out value="${errors.password}"/>
            <input type="submit" name="" value="Submit">

        </form>

    </body>
</html>
