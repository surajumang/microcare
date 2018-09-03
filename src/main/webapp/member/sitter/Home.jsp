<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
        <%--
            The HomePage for the sitter will display all the available options
            and a Welcome Message.
      --%>
      <jsp:include page="../../header.jsp"/>

      <form class="" action="Members/Sitter/ShowAllJobs.do" method="post">
            <input type="submit" name="PostJob" value="Show Jobs"/>
      </form>
      <form class="" action="Members/Sitter/ShowMyApplications.do" method="post">
            <input type="submit" name="ViewJobs" value="My Applications"/>
      </form>
      
      <form class="" action="${pageContext.request.contextPath}/Members/EditProfile.jsp" method="post">
            <input type="submit" name="EditProfile" value="Edit Profile"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/Members/DeleteAccount.jsp" method="post">
            <input type="submit" name="DeleteAccount" value="Delete Account"/>
      </form>


      <jsp:include page="../../footer.jsp"/>
    </body>
</html>
