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
        <style>

</style>
<script type="text/javascript">
    function display(radioValue){
            if(radioValue == "SEEKER"){
                document.getElementById("seeker").style.display ="block";
                document.getElementById("sitter").style.display ="none";
            }
            else{
                document.getElementById("sitter").style.display ="block";
                document.getElementById("seeker").style.display ="none";
            }
        }

    	function displayMemberType(){
    		var memberType = document.getElementsByClassName("memberType");
    		var i;
    		for (i = 0; i < memberType.length; i++) {
    			if(memberType[i].checked){
    				display(memberType[i].value);
    					break;
    			}
    		}
    	}
</script>
    </head>

    <body onload="displayMemberType()">
    <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${INVALID}</h2>



         <a href="${pageContext.request.contextPath}/member/Search.jsp" role="button">Home</a>
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
                <input type="address" name="address" placeholder="Address" value="${formErrors.address}">
                <c:out value="${errors.address}"/><br>

                <label> Phone </label>
                <input type="text" name="phone" placeholder="Phone" value="${formErrors.phone}">
                <c:out value="${errors.phone}"/><br>

                <label>Zip Code</label>
                <input type="text" name="zipCode" placeholder="Zip Code(Six digits)" value="${formErrors.zipCode}">
                <c:out value="${errors.zipCode}"/><br>

                <label>Register as a</label>
                <c:set var="memberType" value="SEEKER"/>
               	<c:if test="${formErrors.memberType=='SITTER'}">
               	    <c:set var="memberType" value="SITTER"/>
               	</c:if>

                <input type="radio" class="memberType" name="memberType" value="SEEKER" <c:if test="${memberType=='SEEKER'}">checked="checked"</c:if> onclick="display('SEEKER');">Seeker
                <input type="radio" class="memberType" name="memberType" value="SITTER" <c:if test="${memberType=='SITTER'}">checked="checked"</c:if> onclick="display('SITTER');">Sitter

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
