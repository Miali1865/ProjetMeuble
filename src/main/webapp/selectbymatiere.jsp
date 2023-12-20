<%@ page import="model.MatierePremiere" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Volume_Composition" %><%--
  Created by IntelliJ IDEA.
  User: mialivola
  Date: 19/12/2023
  Time: 21:01
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
    <h3 class="text-center">Composition volume selon matiere premiere</h3>
  </div>

  <form action="${pageContext.request.contextPath}/SelectParMatiereServlet" method="post">
      <div class="row form-group">
        <div class="col col-md-3"><label for="select" class=" form-control-label">Choisissez une matiere premiere :</label></div>
        <div class="col-12 col-md-9">
          <select id="select" name="idmatierepremiere" class="form-control">
            <%
              try {
                List<MatierePremiere> listematierepremiere = (List<MatierePremiere>) session.getAttribute("listematierepremiere");
                if (listematierepremiere != null) {
                  for (MatierePremiere matierePremiere : listematierepremiere) { %>
            <option value="<%= matierePremiere.getIdmatierePremiere()%>"><%=matierePremiere.getMatierePremiere()%></option>
            <% }}} catch (Exception e) {
              System.out.println(e.getMessage());
            }%>
          </select>
        </div>
      </div>

      <input class="btn btn-lg btn-info btn-block" type="submit" value="Soumettre">
  </form>


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
          <th scope="col">Nom composition</th>
          <th scope="col">Categorie</th>
          <th scope="col">Matière Première</th>
          <th scope="col">Taille</th>
          <th scope="col">Quantite</th>

        </tr>
        </thead>
        <tbody>
        <%
          try {
            List<Volume_Composition> listecomposition = (List<Volume_Composition>) session.getAttribute("listevolumes");
            if (listecomposition != null) {
              for (Volume_Composition volume_composition : listecomposition) { %>
        <tr>
          <td><%= volume_composition.getComposition().getNomcomposition()%></td>
          <td><%= volume_composition.getCategorie().getCategorie()%></td>
          <td><%= volume_composition.getMatierePremiere().getMatierePremiere()%></td>
          <td><%= volume_composition.getTaille().getTaille()%></td>
          <td><%= volume_composition.getQuantite()%></td>
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
