<%@ page contentType="text/html;charset=UTF-8" errorPage = "../ErrorPage.jsp" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

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
        <script type="text/javascript">
                        function confirmDeletion(form) {
                            if(window.confirm("Are you sure you want to Delete this Job")){
                                form.submit();
                            }
                        }
        </script>
    </head>
    <body>

        <h2>${SUCCESS}</h2>
        <h2>${FAILURE}</h2>
        <h2>${UNAUTHORIZED}</h2>
        <h2>${INVALID}</h2>
        <h2>${ACCOUNT_STATUS}</h2>
      <jsp:include page="/header.jsp"/>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/PostJob.jsp" method="post">
                <input type="submit" name="PostJob" value="Post Job"/>
          </form>
      </div>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/ShowMyJobs.do" method="post">
                <input type="submit" name="ViewJobs" value="My Jobs"/>
          </form>
      </div>

      <a href="${pageContext.request.contextPath}/member/Search.jsp"> Search Seekers</a>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/member/PutProfileInfo.do" method="post">
                <input type="submit" name="EditProfile" value="Edit Profile"/>
          </form>
      </div>


      <div class="right">
                   <form class="" action="${pageContext.request.contextPath}/seeker/CloseAccount.do" method="post">
                       <input type="button" name="closeAccount" value="Close Account" onclick="confirmDeletion(this.form, 'close your account')">
                   </form>
      </div>


      <jsp:include page="/footer.jsp"/>
    </body>
</html>
