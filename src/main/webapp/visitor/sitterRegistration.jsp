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
        <style type="text/css">

        </style>
    </head>
    <body>
        <h1>Enter Your Details</h1>
        <form  action="${pageContext.request.contextPath}/visitor/SeekerRegistration.do" method="post">
            <input type="text" name="email" placeholder="Email" value="${formErrors.email}">
            <c:out value="${errors.email}"/><br>

            <input type="text" name="firstName" placeholder="First Name" value="${formErrors.firstName}" >
            <c:out value="${errors.firstName}"/><br>

            <input type="text" name="lastName" placeholder="Last Name" value="${formErrors.lastName}">
            <c:out value="${errors.lastName}"/><br>

            <input type="text" name="address" placeholder="Address" value="${formErrors.address}">
            <c:out value="${errors.address}"/><br>

            <input type="text" name="phone" placeholder="Phone" value="${formErrors.phone}">
            <c:out value="${errors.phone}"/><br>

            <input type="text" name="zipCode" placeholder="Zip Code(Six digits)" value="${formErrors.zipCode}">
            <c:out value="${errors.zipCode}"/><br>

            <input type="text" name="experience" placeholder="Experience(Years)" value="${formErrors.experience}">
            <c:out value="${errors.experience}"/><br>

            <input type="text" name="expectedPay" placeholder="Expected Pay" value="${formErrors.expectedPay}">
            <c:out value="${errors.expectedPay}"/><br>

            <input type="password" name="password" placeholder="Password" >
            <c:out value="${errors.password}"/><br>

            <input type="password" name="password2" placeholder="Password" >
            <c:out value="${errors.password2}"/><br>

            <input type="hidden" name="memberType" value="SITTER">
            <input type="hidden" name="currentPage" value="/visitor/SitterRegistration.jsp">


            <input type="submit" name="" value="Submit">

        </form>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
