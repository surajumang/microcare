<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
           prefix="html" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      
      <script type="text/javascript">
        function confirmDeletion(form) {
            if(window.confirm("Are you sure you want to Delete this Job")){
                form.submit();
            }
        }
      </script>
    </head>
    <body>
    <jsp:include page="/header.jsp"/>

        <p>Are you Sure you want to Delete your Account.</p>

        <input type="button" name="delete" value="Delete" onclick="confirmDeletion"/>
        <input type="button" name="cancel" value="Cancel" onclick=""/>

    </body>
    <jsp:include page="/footer.jsp"/>
</html>
