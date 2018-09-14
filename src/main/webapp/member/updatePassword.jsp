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
        <jsp:include page="./header.jsp"/>
        <h3></h3>
        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${OTHER}</h2>

        <html:form action="/member/captureResetPassword" method="get">
            <table>
                <tr>
                    <td><label>Current Password</label></td>
                    <td><html:password property="currentPassword" /></td>
                </tr>
                <tr>
                    <td><label>New Password</label></td>
                    <td><html:password property="password" /></td>
                </tr>
                <tr>
                    <td><label>Re-Enter Password</label></td>
                    <td><html:password property="password2" /></td>
                </tr>
            </table>
            <html:submit value="Submit"/>
        </html:form>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
