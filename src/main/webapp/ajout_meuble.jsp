<%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 12/12/2023
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Insertion meuble</h2>
<form action="${pageContext.request.contextPath}/AjoutMeubleServlet" method="post">
  <label for="meuble">Meuble :</label>
  <input type="text" name="meuble" id="meuble" required>
  <button type="submit">Valider</button>
</form>

</body>
</html>
