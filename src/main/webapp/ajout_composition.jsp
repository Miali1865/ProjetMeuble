<%@ page import="java.util.List" %>
<%@ page import="model.*" %><%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 12/12/2023
  Time: 16:25
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
                    <h3 class="text-center">Créer une composition</h3>
                </div>
                <hr>

                <form action="${pageContext.request.contextPath}/AjoutCompositionServlet" method="post">
                    <div class="form-group">
                        <label for="cc-payment" class="control-label mb-1">Nom composition: </label>
                        <input id="cc-pament" type="text" class="form-control" aria-required="true" aria-invalid="false" name="composition">
                    </div>

                    <br>

                    <div class="row form-group">
                        <div class="col col-md-3"><label for="select" class=" form-control-label">Choisissez une style :</label></div>
                        <div class="col-12 col-md-9">
                            <select id="select" name="style" class="form-control">
                                <%
                                    try {
                                        List<Style> listestyle = (List<Style>) session.getAttribute("listestyle");
                                        System.out.println(listestyle.size());
                                        if (listestyle != null) {
                                            for (Style style : listestyle) { %>
                                <option value="<%= style.getIdstyle()%>"><%= style.getStyle()%></option>
                                <% }}} catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }%>
                            </select>
                        </div>
                    </div>

                    <br>

                    <div class="row form-group">
                        <div class="col col-md-3"><label for="categorie" class=" form-control-label">Choisissez une categorie :</label></div>
                        <div class="col-12 col-md-9">
                            <select id="categorie" name="categorie" class="form-control">

                                <%
                                    try {
                                        List<Categorie> listecategorie = (List<Categorie>) session.getAttribute("listecategorie");
                                        if (listecategorie != null) {
                                            for (Categorie categorie : listecategorie) { %>
                                <option value="<%= categorie.getIdcategorie()%>"><%= categorie.getCategorie()%></option>

                                <% }}} catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }%>
                            </select>
                        </div>
                    </div>

                    <br>

                    <!-- Checkbox -->
                    <div class="row form-group">
                        <div class="col col-md-3"><label class=" form-control-label">Choisissez une ou plusieurs options </label></div>
                        <br>
                        <%
                            try {
                                List<MatierePremiere> listematierePremiere = (List<MatierePremiere>) session.getAttribute("listematierePremiere");
                                if (listematierePremiere != null) {
                                    for (MatierePremiere matierePremiere : listematierePremiere) { %>
                                        <input type="checkbox" id="optionA" name="matierePremiere[]" value="<%= matierePremiere.getIdmatierePremiere()%>">
                                        <label for="optionA"><%= matierePremiere.getMatierePremiere()%></label>

                        <% }}} catch (Exception e) {
                            System.out.println(e.getMessage());
                        }%>
                    </div>

                    <br>

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
