<%@ page contentType="text/html;charset=UTF-8" errorPage = "../ErrorPage.jsp" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>

<style media="screen">
        .right{
            float: right;
        }
</style>
<script type="text/javascript">
    function confirmation(form, msg) {
        if(window.confirm("Are you sure you want to " + msg)){
            form.submit();
        }
    }
</script>

        <h2>Welcome ${CURRENT_USER.firstName}</h2>
            <c:if test="${CURRENT_USER.memberType eq 'SEEKER'}">
                  <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <div class="btn btn-info" role="button">
                              <html:link action="/member/home">Home</html:link>
                            </div>

                              <div class="btn btn-info" role="button">
                                  <html:link action="/seeker/postJob.do">Post Job</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/seeker/showJobs.do">View Jobs</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/member/search">Search Sitters</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/member/putProfileInfo">Edit Profile</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/member/closeAccount" onclick="return confirm('Do you really want to Close Your Account')">Close Account</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/member/resetPassword">Update Password</html:link>
                              </div>

                              <div class="btn btn-info" role="button">
                                <html:link action="/member/logout" onclick="return confirm('Do you want to Logout')" >Log Out</html:link>
                              </div>

                        </div>
                  </nav>
             </c:if>

            <c:if test="${CURRENT_USER.memberType eq 'SITTER'}">
                 <nav class="navbar navbar-default">
                       <div class="container-fluid">
                           <div class="btn btn-info" role="button">
                             <html:link action="/member/home">Home</html:link>
                           </div>

                             <div class="btn btn-info" role="button">
                                 <html:link action="/sitter/showApplications">Show Applications</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/sitter/showJobs">View Jobs</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/member/search">Search Seekers</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/member/putProfileInfo">Edit Profile</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/member/closeAccount" onclick=" return confirm('Do you really want to Close Your Account')">Close Account</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/member/resetPassword">Update Password</html:link>
                             </div>

                             <div class="btn btn-info" role="button">
                               <html:link action="/member/logout" onclick="return confirm('Do you want to Logout')">Log Out</html:link>
                             </div>

                       </div>
                 </nav>
             </c:if>
