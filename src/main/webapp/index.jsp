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
        </style>
    </head>
    <body>
        <div class="form1" al>
            <h1>welcome</h1>
            <c:out value = "${requestScope.message}">
            <form action="login.do" method="post">
                <input type="text" name="email" placeholder="User Name">
                <br><br>
                <input type="password" name="password" placeholder="Password">
                <br><br>
                <input type="submit" name="sb" value="Submit">
            </form>
            New User
            <a href="${pageContext.request.contextPath}/Members/Register.jsp">Register Here</a>
        </div>
    </body>
</html>
