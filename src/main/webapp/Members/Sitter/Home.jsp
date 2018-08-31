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
      <jsp:include page="../../header.jsp">
      <form class="" action="${pageContext.request.contextPath}/jsp/Jobs/PostJob.jsp" method="post">
            <input type="submit" name="PostJob" value="Post Job"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/jsp/Jobs/ViewMyJobs.jsp" method="post">
            <input type="submit" name="ViewJobs" value="My Jobs"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/jsp/Members/SearchSitters.jsp" method="post">
            <input type="submit" name="SearchSitters" value="Search Sitters"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/jsp/Members/EditProfile.jsp" method="post">
            <input type="submit" name="EditProfile" value="Edit Profile"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/jsp/Members/DeleteAccount.jsp" method="post">
            <input type="submit" name="DeleteAccount" value="Delete Account"/>
      </form>


      <jsp:include page="../../footer.jsp">
    </body>
</html>
