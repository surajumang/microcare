<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
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

        <h2>Welcome ${currentUser.email}</h2>
        <div class="left">
            <html:form class="" action="/home" method="post">
                 <html:submit value="Home">
            </form:form>
        </div>

        <div class="right">
            <html:form class="" action="/member/logout.do" method="post">
                <html:submit value="Log Out" onclick="confirmation(this.form, 'log out')">
            </html:form>
            <form class="" action="${pageContext.request.contextPath}/member/UpdatePassword.jsp" method="post">
                <input type="submit" name="Update Password" value="Update Password">
            </form>
        </div>
