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

     <jsp:include page="/header.jsp"/>
     <h2>${SUCCESS}</h2>
                 <h2>${FAILURE}</h2>
                 <h2>${INVALID}</h2>
        <h1>Enter Your Details</h1>
        <div class="">
            <form  action="${pageContext.request.contextPath}/member/EditProfile.do" method="post">

                <label for="fname">First Name</label>
                <input type="text" name="firstName"  value="${profileInfo.firstName}">
                <c:out value="${errors.firstName}"/><br>

                <label for="lname">Last Name</label>
                <input type="text" name="lastName"  value="${profileInfo.lastName}">
                <c:out value="${errors.lastName}"/><br>

                <label >Address</label>
                <input type="text" name="address" value="${profileInfo.address}">
                <c:out value="${errors.address}"/><br>

                <label> Phone </label>
                <input type="text" name="phone"  value="${profileInfo.phone}">
                <c:out value="${errors.phone}"/><br>

                <label>Zip Code</label>
                <input type="text" name="zipCode"  value="${profileInfo.zipCode}">
                <c:out value="${errors.zipCode}"/><br>

                <label>Experience</label>
                <input type="text" name="experience"  value="${profileInfo.experience}">
                <c:out value="${errors.experience}"/><br>

                <label>ExpectedPay</label>
                <input type="text" name="expectedPay"  value="${profileInfo.expectedPay}">
                <c:out value="${errors.expectedPay}"/><br>

                <input type="hidden" name="memberType" value="SITTER">

                <input type="submit" name="" value="Submit">

            </form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
