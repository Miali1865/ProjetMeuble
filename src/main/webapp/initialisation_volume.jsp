<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    try {
        List<Taille> listetaille = (List<Taille>) request.getSession().getAttribute("listetaille");
        List<Composition> listecompositions = (List<Composition>) request.getSession().getAttribute("listecompositions");

        for(Taille taille : listetaille) {
%>
<div class="card">
    <div class="card-header">
        Mise a jour des <strong>Volumes</strong>
    </div>
    <div class="card-body card-block">
        <form action="${pageContext.request.contextPath}/InitialisationVolumeServlet" method="post" class="form-horizontal">
            <input type="hidden" name="idtaille" value="<%= taille.getIdtaille()%>">
            <p><%= taille.getTaille()%></p>
            <%
                for(Composition composition : listecompositions) {
            %>
            <input type="hidden" name="idmatiere_composition" value="<%= composition.getIdmatiere_composition()%>">
            <div class="row form-group">
                <div class="col col-sm-5"><label for="input-normal" class=" form-control-label"><%= composition.getMatierePremiere().getMatierePremiere()%></label></div>
                <div class="col col-sm-6"><input type="number" id="input-normal" name="quantite" placeholder="Number" class="form-control"></div>
            </div>
            <%
                }
            %>
            <div>
                <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                    <span id="payment-button-amount">Valider</span>
                    <span id="payment-button-sending" style="display:none;">Sending…</span>
                </button>
            </div>
        </form>
    </div>
</div>
<%
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
%>
</body>
</html>
