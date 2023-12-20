<%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 12/12/2023
  Time: 15:52
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
    <div class="card-body">
        <!-- Credit Card -->
        <div id="pay-invoice">
            <div class="card-body">
                <div class="card-title">
                    <h3 class="text-center">Insertion style</h3>
                </div>
                <hr>
                <form action="${pageContext.request.contextPath}/AjoutCategorieServlet" method="post">
                    <div class="form-group">
                        <label for="cc-payment" class="control-label mb-1">Categorie: </label>
                        <input id="cc-pament" type="text" class="form-control" aria-required="true" aria-invalid="false"name="categorie">
                    </div>
                    <div>
                        <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                            <span id="payment-button-amount">Valider</span>
                            <span id="payment-button-sending" style="display:none;">Sending…</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
