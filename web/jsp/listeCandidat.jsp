<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%
    List<Candidat> candidats = (List<Candidat>)request.getAttribute("candidats");
%>

<%@include file="header.jsp" %>
    <h1>Liste des Candidats</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date de Naissance</th>
                <th>Adresse</th>
                <th>Genre</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (candidats != null && !candidats.isEmpty()) {
                    for (Candidat candidat : candidats) {
            %>
            <tr>
                <td><%= candidat.getNomCandidat() %></td>
                <td><%= candidat.getPrenomCandidat() %></td>
                <td><%= candidat.getDateNaissance() %></td>
                <td><%= candidat.getAdresse() %></td>
                <td><%= candidat.getGenre().getLabel() %></td>
                <td><a href="detailsCandidat?idCandidat=<%= candidat.getIdCandidat() %>">Voir CV</a></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">Aucun candidat présélectionné trouvé.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
