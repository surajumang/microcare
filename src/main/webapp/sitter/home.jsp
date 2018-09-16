<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>

    </head>
    <body>
     <jsp:include page="/member/header.jsp"/>
        <h2>${SUCCESS}</h2>
                <h2>${FAILURE}</h2>
                <h2>${INVALID}</h2>
                <h2>${ACCOUNT_STATUS}</h2>


      <form action="${pageContext.request.contextPath}/sitter/showJobs.do" method="post">
            <input type="submit" name="Show Job" value="Show Jobs"/>
      </form>
      <form action="${pageContext.request.contextPath}/sitter/showApplications.do" method="post">
            <input type="submit" name="ViewApplications" value="My Applications"/>
      </form>

      <form action="${pageContext.request.contextPath}/member/putProfileInfo.do" method="post">
            <input type="submit" name="EditProfile" value="Edit Profile"/>
      </form>

      <div class="sitter">
          <form class="" action="${pageContext.request.contextPath}/member/search.jsp" method="post">
              <input type="submit" name="search" value="Search Seekers"/>
          </form>
      </div>
      <jsp:include page="/footer.jsp"/>
    </body>
</html>
