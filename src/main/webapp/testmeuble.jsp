<%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 19/12/2023
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="card-body">
    <div class="card-title">
        <h3 class="text-center">Tous nos meubles</h3>
    </div>
    <hr>
    <div class="col-md-4">
        <div class="card">
            <img class="card-img-top" src="${pageContext.request.contextPath}/images/meubletv.jpg" alt="Card image cap">
            <div class="card-body">
                <h4 class="card-title mb-3">Meuble TV</h4>
                <p class="card-text">Tsara be 500.000Ar</p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <img class="card-img-top" src="${pageContext.request.contextPath}/images/meubletv.jpg" alt="Card image cap">
            <div class="card-body">
                <h4 class="card-title mb-3">Table Royale </h4>
                <p class="card-text">Magnifique 1.000.000Ar</p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <img class="card-img-top" src="${pageContext.request.contextPath}/images/meubletv.jpg" alt="Card image cap">
            <div class="card-body">
                <h4 class="card-title mb-3">Chaise</h4>
                <p class="card-text">By Mialivola 200.000Ar</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
