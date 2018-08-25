<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
        <%--
        Home page for Seeker. Will contain a welcome Message and all the
        available options.
      --%>
      <input type="button" name="PostJob" value="Post Job"/>
      <input type="button" name="ViewJobs" value="My Jobs"/>
      <input type="button" name="SearchSitters" value="Search Sitters"/>
      <input type="button" name="EditProfile" value="Edit Profile"/>
      <input type="button" name="DeleteAccount" value="Delete Account"/>

      <p>Login successful your details are</p>
      <table>
          <tr>
              <td>${member.email}</td>
              <td>${member.firstname}</td>
              <td>${member.lastname}</td>
              <td>${member.zipcode}</td>
          </tr>
          <tr>
              <td>${login.email}</td>
              <td>${login.password}</td>
              <td>${login.email}</td>
              <td>${login.password}</td>
          </tr>
      </table>



    </body>
</html>
