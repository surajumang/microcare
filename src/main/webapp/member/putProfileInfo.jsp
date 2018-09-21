<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>

</style>
    </head>

    <body>

    <jsp:include page="./header.jsp"/>
    <h2>${SUCCESS}</h2>
    <h2>${FAILURE}</h2>
    <h2>${INVALID}</h2>
    <h1>Enter Your Details</h1>

        <div class="">
            <font color="red"> <html:errors property="memberType" /> </font>
            <html:form  action="/member/editProfile" method="post">
                <table align="center">
                <tr>
                    <td><label for="fname">First Name</label></td>
                    <td><html:text property="firstName"  /></td>
                    <td><font color="red"> <html:errors property="firstName" /> </font></td>
                </tr>
                <tr>
                    <td><label for="lname">Last Name</label></td>
                    <td><html:text property="lastName"  /></td>
                    <td><font color="red"> <html:errors property="lastName" /> </font></td>
                </tr>
                <tr>
                    <td><label >Address</label></td>
                    <td><html:text property="address"  /></td>
                    <td><font color="red"> <html:errors property="address" /> </font></td>
                </tr>
                <tr>
                    <td><label> Phone </label></td>
                    <td><html:text property="phone"   /></td>
                    <td><font color="red"> <html:errors property="phone" /> </font></td>
                </tr>
                <tr>
                    <td><label>Zip Code</label></td>
                    <td><html:text property="zipCode" /></td>
                    <td><font color="red"> <html:errors property="zipCode" /> </font></td>
                </tr>

                <c:if test="${profileInfo.memberType == 'SEEKER'}">
                    <tr>
                        <td><label> Spouse Name</label></td>
                        <td><html:text property="spouseName" /></td>
                        <td><font color="red"> <html:errors property="spouseName" /> </font></td>
                    </tr>
                    <tr>
                        <td><label>Number of Children</label></td>
                        <td><html:text property="numberOfChildren"  /></td>
                        <td><font color="red"> <html:errors property="numberOfChildren" /> </font></td>
                    </tr>
                </c:if>
                <c:if test="${profileInfo.memberType == 'SITTER'}">
                    <tr>
                     <td><label> Expected Pay</label></td>
                     <td><html:text property="expectedPay"  /></td>
                     <td><font color="red"> <html:errors property="expectedPay" /> </font></td>
                    </tr>
                    <tr>
                     <td><label>Years of experience</label></td>
                     <td><html:text property="experience"  /></td>
                     <td><font color="red"> <html:errors property="experience" /> </font></td>
                    </tr>
                </c:if>
                </table>

                <input type="hidden" name="memberType" value="SEEKER">
                <center><html:submit property="" value="Submit"/> </center>

            </html:form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
