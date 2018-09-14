<%@ page contentType="text/html;charset=UTF-8" errorPage = "../ErrorPage.jsp" language="java" %>
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
        <style media="screen">
            .seeker{
                width: 25%;
                display: block;
                float: left;
            }
        </style>
    </head>
    <body>


      <jsp:include page="./header.jsp"/>
      <h2>${SUCCESS}</h2>
              <h2>${FAILURE}</h2>
              <h2>${UNAUTHORIZED}</h2>
              <h2>${INVALID}</h2>
              <h2>${ACCOUNT_STATUS}</h2>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/postJob.do" method="post">
                <input type="submit" name="PostJob" value="Post Job"/>
          </form>
      </div>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/showJobs.do" method="post">
                <input type="submit" name="ViewJobs" value="My Jobs"/>
          </form>
      </div>

      <div class="seeker">
                <form action="${pageContext.request.contextPath}/member/search.do" method="post">
                      <input type="submit" name="search" value="Search Sitters"/>
                </form>
      </div>
      <jsp:include page="/footer.jsp"/>
    </body>
</html>
