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



         <a href="${pageContext.request.contextPath}/visitor/login.do" role="button">Home</a>
        <h1>Enter Your Details</h1>
        <div class="">
            <html:form action="/visitor/captureRegistration" method="post" >
            <table>
                <tr>
                    <td><label for="mail">Email</label></td>
                    <td><html:text property="email" /></td>
                </tr>
                <tr>
                    <td><label for="fproperty">First Name</label></td>
                    <td><html:text property="firstName" /></td>
                </tr>
                <tr>
                    <td><label for="lproperty">Last Name</label></td>
                    <td><html:text property="lastName" /></td>
                </tr>
                <tr>
                    <td><label >Address</label></td>
                    <td><html:text property="address" /></td>
                </tr>
                <tr>
                    <td><label> Phone </label></td>
                    <td><html:text property="phone" /></td>
                </tr>
                <tr>
                    <td><label>Zip Code</label></td>
                    <td><html:text property="zipCode" /></td>
                </tr>
                <tr>
                    <td><label>Register as a</label></td>
                    <c:set var="memberType" value="SEEKER"/>
               	    <c:if test="${formErrors.memberType=='SITTER'}">
               	        <c:set var="memberType" value="SITTER"/>
               	    </c:if>
                    <td>
                    Seeker<html:radio styleClass="memberType"  property="memberType" value="SEEKER"  onchange="display('SEEKER');"/>
                    Sitter<html:radio styleClass="memberType"  property="memberType" value="SITTER"  onchange="display('SITTER');"/>
                    </td>
                </tr>

                <tr><td colspan="2">
				<div id="seeker" style="display:block;">
				<table><tr>
                    <td><label>Spouse Name</label></td>
                    <td><html:text property="spouseName"/></td>
                </tr>
                <tr>
                     <td><label>Number of Children</label></td>
                     <td><html:text property="numberOfChildren" /></td>
                </tr>
				</table>
                </div>
				</td></tr>
				<tr><td colspan="2">
                <div id="sitter" style="display:none;">
				<table>
                <tr>
                    <td><label>Years of Experience</label></td>
                    <td><html:text property="experience" /></td>
                </tr>
                <tr>
                     <td> <label>Expected Pay</label></td>
                     <td><html:text property="expectedPay" /></td>
				 </tr>
				</table>
                </div>
				</td></tr>
                <tr>
                    <td><label>Password</label></td>
                    <td><html:password property="password" /></td>
                </tr>

                <tr>
                    <td><label>Re enter the Password</label></td>
                    <td><html:password property="password2"/></td>
                </tr>

                </table>

                <html:submit property="" value="Submit"/>

            </html:form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
