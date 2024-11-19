<%
    String idPoste = (String)request.getAttribute("idPoste");
%>
<%@include file="../shared/header.jsp" %>
    <h1>Ajouter un Recrutement</h1>
    <form action="recrutement" method="post">
        <input type="hidden" name="poste" value="<%= idPoste %>">
        <label for="dateDebut">Date de début :</label>
        <input type="date" id="dateDebut" name="debut" required><br><br>

        <label for="dateFin">Date de fin :</label>
        <input type="date" id="dateFin" name="fin" required><br><br>

        <label for="nombre">Nombre :</label>
        <input type="number" id="nombre" name="nombre" required><br><br>
        <input type="submit" value="Ajouter">
    </form>