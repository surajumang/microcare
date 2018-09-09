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
            <form class="" action="${pageContext.request.contextPath}/${memberType}/Home.jsp" method="post">
                 <input type="submit" name="home" value="Home">
            </form>
        </div>

        <div class="right">
            <form class="" action="${pageContext.request.contextPath}/member/Logout.do" method="post">
                <input type="button" name="logout" value="Log Out" onclick="confirmation(this.form, 'log out')">
            </form>
        </div>
