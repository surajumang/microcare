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
        <form  action="SitterRegistration.do" method="post">
            <input type="text" name="email" placeholder="Email" >
            <c:out value="${errors.email}"/><br>

            <input type="text" name="firstName" placeholder="First Name" >
            <c:out value="${errors.firstName}"/><br>

            <input type="text" name="lastName" placeholder="Last Name">
            <c:out value="${errors.lastName}"/><br>

            <input type="text" name="address" placeholder="Address">
            <c:out value="${errors.address}"/><br>

            <input type="text" name="phone" placeholder="Phone">
            <c:out value="${errors.phone}"/><br>

            <input type="text" name="zipCode" placeholder="Zip Code(Six digits)">
            <c:out value="${errors.zipCode}"/><br>

            <input type="text" name="experience" placeholder="Experience(Years)">
            <c:out value="${errors.experience}"><br>

            <input type="text" name="expectedPay" placeholder="Expected Pay">
            <c:out value="${errors.expectedPay}"><br>

            <input type="password" name="password" placeholder="Password" >
            <c:out value="${errors.password}"/><br>

            <input type="password" name="password2" placeholder="Password" >
            <c:out value="${errors.password2}"/><br>

            <input type="submit" name="" value="Submit">

        </form>

    </body>
</html>
