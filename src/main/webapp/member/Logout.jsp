<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <h2>Successfully logged out</h2><br>
    <h2>Your last site visit</h2>
    <ul>
      <c:forEach var="st" items="${stats}">
        <li>${st}</li>
    </ul>
  </body>
</html>
