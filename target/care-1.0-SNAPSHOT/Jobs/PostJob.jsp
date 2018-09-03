<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
        <%--
        Will provide a form to the user and pass it to the proper servlet.
     --%>
     <p>Page for posting a job. It will show a form to the user.</p>
     <form class="Seeker" action="PostJobServlet.do" method="post">
         <input type="text" name="email" placeholder="Email" value="">
         <c:out value="${errors.email}"/>
         <br><br>
         <input type="text" name="firstname" placeholder="First Name" value="">
         <c:out value="${errors.name}"/>
         <br><br>
         <input type="text" name="lastname" placeholder="Last Name" value="">
         <c:out value="${errors.name}"/>
         <br><br>
         <input type="text" name="zipcode" placeholder="Zip Code(Six digits)" value="">
         <c:out value="${errors.number}"/>
         <br><br>
         <input type="password" name="password" placeholder="Password" value="">
         <c:out value="${errors.password}"/>
         <input type="submit" name="" value="Submit">

     </form>

    </body>
</html>
