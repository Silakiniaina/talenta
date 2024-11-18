<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Poste" %>
<%@ page import="model.TypeContrat" %> 
<%
    List<TypeContrat> typeContrats = (List<TypeContrat>)request.getAttribute("typeContrats");
    String idCandidat = (String)request.getAttribute("idCandidat");
    String idRecrutement = (String)request.getAttribute("idRecrutement");
%>
<%@include file="header.jsp" %>
<body>
    <h1>Ajouter un Contrat</h1>
    <form action="contrat" method="post">
        <input type="hidden" name="idCandidat" value="<%= idCandidat %>">
        <input type="hidden" name="idRecrutement" value="<%= idRecrutement %>">
        <label for="dateDebut">Date de début :</label>
        <input type="date" id="dateDebut" name="debut" required><br><br>

        <label for="nombre">Salaire :</label>
        <input type="number" id="nombre" name="salaire" required><br><br>

        <label for="poste">TypeContrat :</label>
        <select id="poste" name="type" required>
            <%
                for (TypeContrat typeContrat : typeContrats) {
                    out.println("<option value=\"" + typeContrat.getId_type_contrat() + "\">" + typeContrat.getLabel() + "</option>");
                }
            %>
        </select><br><br>
        <label for="dateFin">Date de fin :</label>
        <input type="date" id="dateFin" name="fin" ><br><br>

        <input type="submit" value="Signer">
    </form>
</body>
</html>
