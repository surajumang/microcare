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
        <style type="text/css">
            form {
                text-align: center;
            }
        </style>
    </head>
    <body>

        <h2>Password can be reset from here</h2>
        <c:out value="${FAILURE}"/>


        <c:if test="${not empty passwordResetForm.token}">
            <html:form action="/visitor/captureResetPassword" method="post">
                <table align="center">
                    <tr>
                        <td><label>Enter Password</label></td>
                        <td><input type="password" name="password" value=""></td>
                        <td><font color="red"> <html:errors property="password" /> </font></td>
                    </tr>
                    <tr>
                        <td><label>Re-enter Password</label></td>
                        <td><input type="password" name="password2" value="" ></td>
                        <td><font color="red"> <html:errors property="password2" /> </font></td>
                    </tr>

                </table>

                 <html:hidden property="id" />
                <html:hidden property="token" />
                <html:submit value="Submit"/>
            </html:form>
        </c:if>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
