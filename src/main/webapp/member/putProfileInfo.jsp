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
      <title></title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>

    <body>

    <jsp:include page="/member/header.jsp"/>

    <div class="container" style="max-width:500px">
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${INVALID}</h2>
        <h1>Enter Your Details</h1>

            <html:form  action="/member/editProfile" method="post">
                <div class="form-group">
                    <label for="fname">First Name</label>
                    <html:text property="firstName" styleClass="form-control" />
                    <font color="red"> <html:errors property="firstName" /> </font>
                </div>
                <div class="form-group">
                    <label for="lname">Last Name</label>
                    <html:text property="lastName" styleClass="form-control" />
                    <font color="red"> <html:errors property="lastName" /> </font>
                </div>
                <div class="form-group">
                    <label >Address</label>
                    <html:text property="address"  styleClass="form-control"/>
                    <font color="red"> <html:errors property="address" /> </font>
                </div>
                <div class="form-group">
                    <label> Phone </label>
                    <html:text property="phone"  styleClass="form-control" />
                    <font color="red"> <html:errors property="phone" /> </font>
                </div>
                <div class="form-group">
                    <label>Zip Code</label>
                    <html:text property="zipCode" styleClass="form-control"/>
                    <font color="red"> <html:errors property="zipCode" /> </font>
                </div>

                <c:if test="${profileInfo.memberType == 'SEEKER'}">
                    <div class="form-group">
                        <label> Spouse Name</label>
                        <html:text property="spouseName" styleClass="form-control" />
                        <font color="red"> <html:errors property="spouseName" /> </font>
                    </div>
                    <div class="form-group">
                        <label>Number of Children</label>
                        <html:text property="numberOfChildren" styleClass="form-control" />
                        <font color="red"> <html:errors property="numberOfChildren" /> </font>
                    </div>
                </c:if>
                <c:if test="${profileInfo.memberType == 'SITTER'}">
                    <div class="form-group">
                     <label> Expected Pay</label>
                     <html:text property="expectedPay" styleClass="form-control" />
                     <font color="red"> <html:errors property="expectedPay" /> </font>
                    </div>
                    <div class="form-group">
                     <label>Years of experience</label>
                     <html:text property="experience" styleClass="form-control" />
                     <font color="red"> <html:errors property="experience" /> </font>
                    </div>
                </c:if>

                <input type="hidden" name="memberType" value="SEEKER">
                <html:submit property="" styleClass="form-control" value="Submit"/>

            </html:form>
        </div>
    </body>
    <%-- <jsp:include page="/footer.jsp"/> --%>
</html>
