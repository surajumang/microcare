<style media="screen">
        .logout{
            float: right;
        }
</style>


        <h2>Welcome ${currentUser.email}</h2>
        <div class="logout">
            <form class="" action="${pageContext.request.contextPath}/member/Logout.do" method="post">
                <input type="submit" name="logout" value="Log Out">
            </form>
        </div>
