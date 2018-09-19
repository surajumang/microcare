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

        <h2>Welcome ${currentUser.firstName}</h2>
        <div class="left">
            <form class="" action="${pageContext.request.contextPath}/member/home.do" method="post">
                 <input type="submit" name="home" value="Home">
            </form>
        </div>
        <div class="left">
           <form action="${pageContext.request.contextPath}/member/putProfileInfo.do" method="post">
                <input type="submit" name="EditProfile" value="Edit Profile"/>
           </form>
        </div>

        <div class="right">
            <form class="" action="${pageContext.request.contextPath}/member/logout.do" method="post">
                <input type="button" name="logout" value="Log Out" onclick="confirmation(this.form, 'log out')">
            </form>
            <form class="" action="${pageContext.request.contextPath}/member/resetPassword.do" method="post">
                <input type="submit" name="Update Password" value="Update Password">
            </form>

            <div class="right">
                <form class="" action="${pageContext.request.contextPath}/member/closeAccount.do" method="post">
                    <input type="button" name="closeAccount" value="Close Account" onclick="confirmation(this.form, 'close your account');">
                </form>
            </div>
        </div>
