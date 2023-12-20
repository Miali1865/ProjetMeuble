<%@ page import="model.Composition" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 19/12/2023
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="card">
    <div class="card-header">
        <strong>Selection Composition </strong>
    </div>
    <div class="card-body">
        <%
            try {
                List<Composition> compositionList = (List<Composition>) request.getSession().getAttribute("listcompositions");
                for(Composition composition : compositionList) {
        %>
        <button type="button" class="btn btn-outline-success"><a href="${pageContext.request.contextPath}/InitialisationVolumeServlet?idcomposition=<%= composition.getIdcomposition()%>"><%= composition.getNomcomposition()%></a></button>
        <% } } catch (Exception e) {
            System.out.println(e.getMessage());
            } %>
    </div>
</div>
</body>
</html>
