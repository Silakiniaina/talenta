<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.Questionaire" %>

<%
    Candidat c = (Candidat)request.getAttribute("candidat");
%>

<%@include file="../shared/header.jsp" %>

<h1>Test pour le Candidat : <%= c.getNomCandidat() +" "+ c.getPrenomCandidat() %></h1>

<form action="testCandidat" method="post">
    <input type="hidden" name="idCandidat" value="<%= c.getIdCandidat() %>">
    <table>
        <thead>
            <tr>
                <th>Question</th>
                <th>Réponse (1 à 5)</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Questionaire> questions = (List<Questionaire>) request.getAttribute("questionaires");
                for (Questionaire question : questions) {
            %>
            <tr>
                <td><%= question.getQuestion() %></td>
                <td>
                    <select name="quest<%= question.getIdQuestionaire() %>">
                        <option value="1">1 - Faible</option>
                        <option value="2">2 - Moyen</option>
                        <option value="3">3 - Bon</option>
                        <option value="4">4 - Élevé</option>
                        <option value="5">5 - Très élevé</option>
                    </select>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <button type="submit">Soumettre les réponses</button>
</form>

</body>
</html>
