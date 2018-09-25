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
            <div class="container" style="max-width:500px">
                <html:form action="/visitor/captureRegistration" method="post" >

    					<div class="form-group">
    						<label for="mail">Email*</label>
    						<html:text property="email" styleClass="form-control" />
    						<font color="red"> <html:errors property="email" /> </font>
    					</div>

    					<div class="form-group">
    						<label for="fproperty">First Name*</label>
    						<html:text property="firstName" styleClass="form-control"/>
    						<font color="red"> <html:errors property="firstName" /> </font>
    					</div>

    					<div class="form-group">
                            <label for="lproperty">Last Name*</label>
                            <html:text property="lastName" styleClass="form-control"/>
                            <font color="red"> <html:errors property="lastName" /> </font>
    					</div>

    					<div class="form-group">
                            <label >Address*</label>
                            <html:textarea property="address" styleClass="form-control" rows="7" cols="17" />
                            <font color="red"> <html:errors property="address" /> </font>
    					</div>

    					<div class="form-group">
                            <label> Phone (Ten Digits)* </label>
                            <html:text property="phone" styleClass="form-control" />
                            <font color="red"> <html:errors property="phone" /> </font>
    					</div>

    					<div class="form-group">
                            <label>Zip Code (Six Digits)*</label>
                            <html:text property="zipCode" styleClass="form-control"/>
                            <font color="red"> <html:errors property="zipCode" /> </font>
    					</div>

    					<div class="form-group">
                            <label>Register as a</label>
                            <c:set var="memberType" value="SEEKER"/>
                       	    <c:if test="${formErrors.memberType=='SITTER'}">
                       	        <c:set var="memberType" value="SITTER"/>
                       	    </c:if>

                            Seeker<html:radio styleClass="radio-inline"  property="memberType" value="SEEKER"  onchange="display('SEEKER');"/>
                            Sitter<html:radio styleClass="radio-inline"  property="memberType" value="SITTER"  onchange="display('SITTER');"/>
                        </div>



    				<div id="seeker" style="display:block;">

						<div class="form-group">
                            <label>Spouse Name</label>
                            <html:text property="spouseName" styleClass="form-control"/>
                            <font color="red"> <html:errors property="spouseName" /> </font>
						</div>

						<div class="form-group">
                             <label>Number of Children   </label>
                             <html:text property="numberOfChildren" styleClass="form-control"/>
                             <font color="red"> <html:errors property="numberOfChildren" /> </font>
						</div>

                    </div>


                    <div id="sitter" style="display:none;">

    					<div class="form-group">
                            <label>Years of Experience*</label>
                            <html:text property="experience" styleClass="form-control"/>
                            <font color="red"> <html:errors property="experience" /> </font>
    					</div>

    					<div class="form-group">
                             <label>Expected Pay* (ddd.dd)</label>
                             <html:text property="expectedPay" styleClass="form-control"/>
                             <font color="red"> <html:errors property="expectedPay" /> </font>
    					 </div>
                    </div>

    				<div class="form-group">
                        <label>Password*</label>
                        <html:password property="password" styleClass="form-control"/>
                        <font color="red"> <html:errors property="password" /> </font>
    				</div>

    				<div class="form-group">
                        <label>Re enter the Password*</label>
                        <html:password property="password2" styleClass="form-control"/>
                        <font color="red"> <html:errors property="password2" /> </font>
    				</div>



                    <center><html:submit property="" value="Submit" styleClass="form-control"/></center>

                </html:form>
            </div>
    </body>
    
</html>
