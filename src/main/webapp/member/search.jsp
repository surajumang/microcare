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
        <meta charset="utf-8">
        <title></title>
        <style>
                        table {
                            font-family: arial, sans-serif;
                            border-collapse: collapse;
                            width: 100%;
                        }

                        td, th {
                            border: 1px solid #dddddd;
                            text-align: left;
                            padding: 8px;
                        }

                        tr:nth-child(even) {
                            background-color: #dddddd;
                        }
                        </style>

    </head>
    <body>
    <jsp:include page="/header.jsp"/>
    <h2>${SUCCESS}</h2>
    <h2>${FAILURE}</h2>

    <h2>Enter Email(partial accepted) to search </h2>

    <html:form action="/member/captureSearch" method="post">
        <label>Email</label>
        <html:text property = "email"/>
        <font color="red" ><html:errors property="email" /></font>
        <html:submit value = "GO"/>
    </html:form>

<div class="data">
<c:if test="${fn:length(members) > 0}">
    <table>
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
    <jsp:include page="/footer.jsp"/>
</html>
