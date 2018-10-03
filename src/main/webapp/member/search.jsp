<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" >
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
            <h2>Enter Email(partial accepted) to search </h2>

            <html:form action="/member/captureSearch" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <html:text property = "email" styleClass="form-control"/>
                    <font color="red" ><html:errors property="email" /></font>
                </div>
                <html:submit value = "GO" styleClass="form-control"/>
            </html:form>
        </div>

        <div class="container">
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <c:if test="${fn:length(members) > 0}">
            <table class="table table-striped">
                <c:if test="${members[0].memberType eq 'SITTER'}">
                <thead>
                     <tr>
                     <td>First Name</td>
                     <td>Last Name</td>
                     <td>Email</td>
                     <td>Experience</td>
                     <td>Expected Pay</td>
                     <td>Phone</td>
                     </tr>
                </thead>
                <c:forEach var ="member" items="${members}">
                    <tr>
                        <td>${member.firstName}</td>
                        <td>${member.lastName}</td>
                        <td>${member.email}</td>
                        <td>${member.experience}</td>
                        <td>${member.expectedPay}</td>
                        <td>${member.phone}</td>
                    </tr>
                </c:forEach>
                </c:if>

                <c:if test="${members[0].memberType eq 'SEEKER'}">
                    <thead>
                         <tr>
                         <td>First Name</td>
                         <td>Last Name</td>
                         <td>Email</td>
                         <td>Spouse Name</td>
                         <td>Number of Children</td>
                         <td>Phone</td>
                         </tr>
                    </thead>
                    <c:forEach var ="member" items="${members}">
                        <tr>
                            <td>${member.firstName}</td>
                            <td>${member.lastName}</td>
                            <td>${member.email}</td>
                            <td>${member.spouseName}</td>
                            <td>${member.numberOfChildren}</td>
                            <td>${member.phone}</td>
                        </tr>
                    </c:forEach>
                 </c:if>
            </table>
        </c:if>
        </div>


    </body>

</html>
