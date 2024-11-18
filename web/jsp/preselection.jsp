<%@ page import="java.util.List" %>
<%@ page import=" model.PreselectionCandidat" %>
<%@ page import=" model.Candidat" %>
<%@ page import=" model.Recrutement" %>

<%@include file="header.jsp" %>

<h1>Liste des Candidats Présélectionnés</h1>

<table>
    <thead>
        <tr>
            <th>ID candidat</th>
            <th>Nom du Candidat</th>
            <th>Poste</th>
            <th>% competence</th>
            <th>% diplome</th>
            <th>% experience</th>
            <th>% global</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <%

            List<PreselectionCandidat> preselectionCandidats = (List<PreselectionCandidat>) request.getAttribute("preselections");
            if (preselectionCandidats != null && !preselectionCandidats.isEmpty()) {
                for (PreselectionCandidat preselection : preselectionCandidats) {
                    Candidat candidat = preselection.getCandidat();
                    Recrutement recrutement = preselection.getRecrutement();
        %>
        <tr>
            <td><%= candidat.getIdCandidat() %></td>
            <td><%= candidat.getNomCandidat() %> <%= candidat.getPrenomCandidat() %></td>
            <td><%= recrutement.getPoste().getNomPoste() %></td>
            <td><%= String.format("%.2f", preselection.getPourcentageCompetence()) %> %</td>
            <td><%= String.format("%.2f", preselection.getPourcentageDiplome()) %> %</td>
            <td><%= String.format("%.2f", preselection.getPourcentageExperience()) %> %</td>
            <td><%= String.format("%.2f", preselection.getScoreGlobale()) %></td>
            <td><a href="testCandidat?idCandidat=<%= candidat.getIdCandidat() %>">Tester Candidat</a></td>
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
