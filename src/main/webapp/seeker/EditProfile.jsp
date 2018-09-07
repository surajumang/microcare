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
        <style>

</style>
    </head>

    <body>
    <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${INVALID}</h2>
    <jsp:include page="/header.jsp"/>
        <h1>Enter Your Details</h1>
        <div class="">
            <form  action="${pageContext.request.contextPath}/seeker/EditProfile.do" method="post">

                <label for="fname">First Name</label>
                <input type="text" name="firstName" placeholder="First Name" value="${formErrors.firstName}">
                <c:out value="${errors.firstName}"/><br>

                <label for="lname">Last Name</label>
                <input type="text" name="lastName" placeholder="Last Name" value="${formErrors.lastName}">
                <c:out value="${errors.lastName}"/><br>

                <label >Address</label>
                <input type="text" name="address" placeholder="Address" value="${formErrors.address}">
                <c:out value="${errors.address}"/><br>

                <label> Phone </label>
                <input type="text" name="phone" placeholder="Phone" value="${formErrors.phone}">
                <c:out value="${errors.phone}"/><br>

                <label>Zip Code</label>
                <input type="text" name="zipCode" placeholder="Zip Code(Six digits)" value="${formErrors.zipCode}">
                <c:out value="${errors.zipCode}"/><br>

                <label> Spouse Name</label>
                <input type="text" name="spouseName" placeholder="Spouse Name (optional)" value="${formErrors.spouseName}">
                <c:out value="${errors.spouseName}"/><br>

                <label>Number of Children</label>
                <input type="text" name="children" placeholder="Number of Children(optional)" value="${formErrors.noOfChildren}">
                <c:out value="${errors.noOfChildren}"/><br>

                <input type="hidden" name="memberType" value="SEEKER">

                <input type="submit" name="" value="Submit">

            </form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
