<%@ page import="java.util.List" %>
<%@ page import="model.ResultatEvalution" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.Recrutement" %>

<%
    String idRecrutement = (String)request.getAttribute("idRecrutement");
%>
<%@include file="header.jsp" %>
    <h1>Résultats d'Évaluation des Candidats</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Candidat</th>
                <th>Id Recrutement</th>
                <th>Score Moyen</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<ResultatEvalution> resultatsEvaluation = (List<ResultatEvalution>) request.getAttribute("resultats");
                for (ResultatEvalution result : resultatsEvaluation) {
            %>
                <tr>
                    <td><%= result.getCandidat().getNomCandidat() + " " + result.getCandidat().getPrenomCandidat() %></td>
                    <td><%= result.getRecrutement().getIdRecrutement() %></td>
                    <td><%= result.getScore() %></td>
                    <td><a href="contrat?idCandidat=<%= result.getCandidat().getIdCandidat() %>&idRecrutement=<%= idRecrutement %>">Signer contrat</a></td>
                </tr>
            <% 
                } 
            %>
        </tbody>
    </table>
</body>
</html>
