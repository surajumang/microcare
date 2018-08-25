<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style type="text/css">
            form {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Enter Your Details</h1>
        <form class="Seeker" action="RegistrationServlet.do" method="post">
            <input type="text" name="email" placeholder="Email" value="">
            <br><br>
            <input type="text" name="firstname" placeholder="First Name" value="">
            <br><br>
            <input type="text" name="lastname" placeholder="Last Name" value="">
            <br><br>
            <input type="text" name="zipcode" placeholder="Zip Code" value="">
            <input type="password" name="password" placeholder="Password" value="">
            <input type="submit" name="" value="Submit">

        </form>

    </body>
</html>
