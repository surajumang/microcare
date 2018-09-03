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
        <%--
        Home page for Seeker. Will contain a welcome Message and all the
        available options.
      --%>

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

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/SearchSitters.jsp" method="post">
                <input type="submit" name="SearchSitters" value="Search Sitters"/>
          </form>
      </div>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/seeker/EditProfile.jsp" method="post">
                <input type="submit" name="EditProfile" value="Edit Profile"/>
          </form>
      </div>

      <div class="seeker">
          <form class="" action="${pageContext.request.contextPath}/member/DeleteAccount.jsp" method="post">
                <input type="submit" name="DeleteAccount" value="Delete Account" onclick="confirmDeletion(this.form)">
          </form>
      </div>


      <p>Login successful your details are</p>
      <table>
          <tr>
              <td>${currentUser.email}</td>
              <td>${currentUser.firstName}</td>
              <td>${currentUser.lastName}</td>
              <td>${currentUser.zipCode}</td>
          </tr>
      </table>


      <jsp:include page="/footer.jsp"/>
    </body>
</html>
