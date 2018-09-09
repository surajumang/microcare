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
<script type="text/javascript">
    function display(radioValue){
        if("${formErrors.memberType}" == "SEEKER" || radioValue.value == "SEEKER"){
            document.getElementById("seeker").style.display ="block";
            document.getElementById("sitter").style.display ="none";
        }
        else{
            document.getElementById("sitter").style.display ="block";
            document.getElementById("seeker").style.display ="none";
        }
    }
</script>
    </head>

    <body>
    <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${INVALID}</h2>
        <h1>Enter Your Details</h1>
        <div class="">
            <form name="regform" action="${pageContext.request.contextPath}/visitor/Registration.do" method="post">
                <label for="mail">Email</label>
                <input type="text" name="email" placeholder="Email" value="${formErrors.email}">
                <c:out value="${errors.email}"/><br>

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

                <label>Register as a</label>
               <input type="radio" name="memberType" value="SEEKER" checked="checked" onclick="display(this);">Seeker
               <input type="radio" name="memberType" value="SITTER" onclick="display(this);">Sitter

                <div id="seeker">

                    <label>Spouse Name</label>
                     <input type="text" name="spouseName" placeholder="Spouse Name (optional)" value="${formErrors.spouseName}">
                     <c:out value="${errors.spouseName}"/><br>
                     <label>Number of Children</label>
                     <input type="text" name="children" placeholder="Number of Children(optional)" value="${formErrors.numberOfChildren}">
                     <c:out value="${errors.numberOfChildren}"/><br>
                </div>
                <div id="sitter" style="display:none;">
                    <label>Years of Experience</label>
                     <input type="text" name="experience" placeholder="Experience(Years)" value="${formErrors.experience}">
                     <c:out value="${errors.experience}"/><br>
                     <label>Expected Pay</label>
                     <input type="text" name="expectedPay" placeholder="Expected Pay" value="${formErrors.expectedPay}">
                     <c:out value="${errors.expectedPay}"/><br>
                </div>

                <label>Password</label>
                <input type="password" name="password" placeholder="Password">
                <c:out value="${errors.password}"/><br>

                <label>Re enter the Password</label>
                <input type="password" name="password2" placeholder="Password" >
                <c:out value="${errors.password2}"/><br>

                <input type="hidden" name="memberType" value="SEEKER">
                <input type="hidden" name="currentPage" value="/visitor/SeekerRegistration.jsp">



                <input type="submit" name="" value="Submit">

            </form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
