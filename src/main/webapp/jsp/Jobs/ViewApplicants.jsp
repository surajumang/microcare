<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

</head>
    <body>
        <h2>html table</h2>
        <table>
          <tr>
            <th>Email</th>
            <th>Phone</th>
            <th>ZipCode</th>
          </tr>
          <%--
          This will accept a List of Application(Bean) in the request corresponding
          to a Job (which will belong to some Seeker) and will
          print them as a row of this table.
          use for each to iterate over.
        --%>
        </table>

    </body>
</html>
