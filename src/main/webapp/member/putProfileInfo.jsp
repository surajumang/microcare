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
            <html:form  action="/member/editProfile.do" method="post">
                <table>
                <tr>
                    <td><label for="fname">First Name</label></td>
                    <td><html:text property="firstName" value="${profileInfo.firstName}" /></td>
                </tr>
                <tr>
                    <td><label for="lname">Last Name</label></td>
                    <td><html:text property="lastName"  value="${profileInfo.lastName}"/></td>
                </tr>
                <tr>
                    <td><label >Address</label></td>
                    <td><html:text property="address" value="${profileInfo.address}" /></td>
                </tr>
                <tr>
                    <td><label> Phone </label></td>
                    <td><html:text property="phone" value="${profileInfo.phone}"  /></td>
                </tr>
                <tr>
                    <td><label>Zip Code</label></td>
                    <td><html:text property="zipCode" value="${profileInfo.zipCode}"/></td>
                </tr>
                <c:if test="${profileInfo.memberType == 'SEEKER'}">
                    <tr>
                        <td><label> Spouse Name</label></td>
                        <td><html:text property="spouseName" value="${profileInfo.spouseName}"/></td>
                    </tr>
                    <tr>
                        <td><label>Number of Children</label></td>
                        <td><html:text property="numberOfChildren" value="${profileInfo.numberOfChildren}" /></td>
                    </tr>
                </c:if>
                <c:if test="${profileInfo.memberType == 'SITTER'}">
                    <tr>
                     <td><label> Expected Pay</label></td>
                     <td><html:text property="expectedPay" value="${profileInfo.expectedPay}" /></td>
                    </tr>
                    <tr>
                     <td><label>Years of experience</label></td>
                     <td><html:text property="experience" value="${profileInfo.experience}" /></td>
                    </tr>
                </c:if>
                </table>
                <html:hidden property="memberType" value="${profileInfo.memberType}"/>

                <html:submit property="" value="Submit"/>

            </html:form>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
