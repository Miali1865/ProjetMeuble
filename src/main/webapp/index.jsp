<%@ page import="model.Style" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Categorie" %>
<%@ page import="model.Composition" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <title>Selection meuble</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

<!-- <button><a href="${pageContext.request.contextPath}/AjoutStyleServlet">Insertion Style</a></button>
<button><a href="${pageContext.request.contextPath}/AjoutCategorieServlet">Insertion Categorie</a></button>
<button><a href="${pageContext.request.contextPath}/AjoutmatierePremiereServlet">INsertion Matiere Premiere</a></button>
<button><a href="${pageContext.request.contextPath}/AjoutMeubleServlet">INsertion Meuble</a></button>
<button><a href="${pageContext.request.contextPath}/AjoutCompositionServlet">INsertion Composition</a></button>
<button><a href="${pageContext.request.contextPath}/SelectListeServlet">Liste par style par categorie</a></button> -->

<div class="content mt-3">
    <form action="${pageContext.request.contextPath}/SelectListeServlet" method="post">
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

        <input type="submit" value="Soumettre">
</div>

<br>

<div class="content mt-3">
    <div class="card">
        <div class="card-header">
            <strong class="card-title">Liste style par matiere premiere</strong>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Matière Première</th>
                    <th scope="col">Style</th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        List<Composition> listecomposition = (List<Composition>) session.getAttribute("listeproduit");
                        if (listecomposition != null) {
                            for (Composition composition : listecomposition) { %>
                <tr>
                    <td><%= composition.getMatierePremiere().getMatierePremiere()%></td>
                    <td><%= composition.getStyle().getStyle()%></td>
                </tr>

                <% }}} catch (Exception e) {
                    System.out.println(e.getMessage());
                }%>
                </tbody>
            </table>
        </div>
    </div>
</div>
</form>
</body>
</html>