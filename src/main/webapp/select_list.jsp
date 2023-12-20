<%@ page import="model.Style" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Categorie" %>
<%@ page import="model.Composition" %>
<%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 12/12/2023
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="content mt-3">
        <div class="card-title">
            <h3 class="text-center">Affichage composition</h3>
        </div>

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
            <input class="btn btn-lg btn-info btn-block" type="submit" value="Soumettre">
        </form>
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
                        <th scope="col">Style</th>
                        <th scope="col">Matière Première</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                List<Composition> listecomposition = (List<Composition>) session.getAttribute("listeproduit");
                                if (listecomposition != null) {
                                    for (Composition composition : listecomposition) { %>
                        <tr>
                            <td><%= composition.getStyle().getStyle()%></td>
                            <td><%= composition.getMatierePremiere().getMatierePremiere()%></td>
                        </tr>

                        <% }}} catch (Exception e) {
                            System.out.println(e.getMessage());
                        }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
