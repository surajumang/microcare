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

        <h2>Welcome ${CURRENT_USER.email}</h2>
        <div class="left">
            <form class="" action="${pageContext.request.contextPath}/member/home.do" method="post">
                 <input type="submit" name="home" value="Home"/>
            </form>
        </div>

        <div class="right">

            <form class="" action="${pageContext.request.contextPath}/member/logout.do" method="post">
                <input type="submit" value="Log Out" onclick="confirmation(this.form, 'log out')"/>
            </form>

            <form class="" action="${pageContext.request.contextPath}/member/updatePassword.jsp" method="post">
                <input type="submit" name="Update Password" value="Update Password">
            </form>
        </div>
