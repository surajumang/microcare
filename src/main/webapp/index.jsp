<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>

        <style type="text/css">
            form {
                text-align: center;
            }
            input {
                width: 100px;
            }
            .right{
                float:right;
            }
        </style>
    </head>
    <body>
        <div class="form1" al>
            <h1>Welcome to Home Job Marketplace</h1>
            <h2>${SUCCESS}</h2>
            <h2>${FAILURE}</h2>
            <h2>${OTHER}</h2>


            <h2>${message}</h2>
            <form action="${pageContext.request.contextPath}/visitor/login.do" method="post">
                <label>Email</label>
                <input type="text" name="email" placeholder="Email" value="${loginDetails.email}">
                <c:out value="${errors.email}"/>
                <label>Password</label>
                <input type="password" name="password" placeholder="Password">
                <input type="submit" name="sb" value="Submit">
            </form>

        </div>

        <div class="right">
        <br>
                    New User
                    <a href="${pageContext.request.contextPath}/visitor/Registration.jsp">Register Here</a>
                    <br>
                    <a href="${pageContext.request.contextPath}/visitor/ResetPassword.jsp">Forgot Passwod</a>
        </div>
    </body>
</html>
