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
        <center> <h1>Enter Your Details</h1></center>
        <hr>
        <div class="">
            <html:form action="/visitor/captureRegistration" method="post" >
            <table align="center">
                <tr>
                    <td><label for="mail">Email*</label></td>
                    <td><html:text property="email" /></td>
                    <td><font color="red"> <html:errors property="email" /> </font></td>
                </tr>
                <tr>
                    <td><label for="fproperty">First Name*</label></td>
                    <td><html:text property="firstName" /></td>
                    <td><font color="red"> <html:errors property="firstName" /> </font></td>
                </tr>
                <tr>
                    <td><label for="lproperty">Last Name*</label></td>
                    <td><html:text property="lastName" /></td>
                    <td><font color="red"> <html:errors property="lastName" /> </font></td>
                </tr>
                <tr>
                    <td><label >Address*</label></td>
                    <td><html:text property="address" /></td>
                    <td><font color="red"> <html:errors property="address" /> </font></td>
                </tr>
                <tr>
                    <td><label> Phone (Ten Digits)* </label></td>
                    <td><html:text property="phone" /></td>
                    <td><font color="red"> <html:errors property="phone" /> </font></td>
                </tr>
                <tr>
                    <td><label>Zip Code (Six Digits)*</label></td>
                    <td><html:text property="zipCode" /></td>
                    <td><font color="red"> <html:errors property="zipCode" /> </font></td>
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
                    <table>
                        <tr>
                            <td><label>Spouse Name</label></td>
                            <td><html:text property="spouseName"/></td>
                            <td><font color="red"> <html:errors property="spouseName" /> </font></td>
                        </tr>
                        <tr>
                             <td><label>Number of Children   </label></td>
                             <td><html:text property="numberOfChildren" /></td>
                             <td><font color="red"> <html:errors property="numberOfChildren" /> </font></td>
                        </tr>
                    </table>
                </div>
				</td></tr>
				<tr><td colspan="2">
                <div id="sitter" style="display:none;">
				<table>
                <tr>
                    <td><label>Years of Experience*</label></td>
                    <td><html:text property="experience" /></td>
                    <td><font color="red"> <html:errors property="experience" /> </font></td>
                </tr>
                <tr>
                     <td> <label>Expected Pay* (ddd.dd)</label></td>
                     <td><html:text property="expectedPay" /></td>
                     <td><font color="red"> <html:errors property="expectedPay" /> </font></td>
				 </tr>
				</table>
                </div>
				</td></tr>
                <tr>
                    <td><label>Password*</label></td>
                    <td><html:password property="password" /></td>
                    <td><font color="red"> <html:errors property="password" /> </font></td>
                </tr>

                <tr>
                    <td><label>Re enter the Password*</label></td>
                    <td><html:password property="password2"/></td>
                    <td><font color="red"> <html:errors property="password2" /> </font></td>
                </tr>

                </table>

                <center><html:submit property="" value="Submit"/></center>

            </html:form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
