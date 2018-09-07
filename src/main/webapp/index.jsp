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
            <h2>${SUCCESS}</h2>
            <p>${FAILURE}</p>


            <c:out value = "${message}">
            <form action="visitor/login.do" method="post">
                <label>Email</label>
                <input type="text" name="email" placeholder="User Name">
                <label>Email</label>
                <input type="password" name="password" placeholder="Password">

                <input type="submit" name="sb" value="Submit">
            </form>
            New User
            <a href="${pageContext.request.contextPath}/visitor/Register.jsp">Register Here</a>
        </div>
    </body>
</html>
