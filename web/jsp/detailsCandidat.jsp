<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.CompetenceCandidat" %>
<%@ page import="java.util.Date" %>
<%
    Candidat candidat = (Candidat)request.getAttribute("candidat");

    if (candidat == null) {
        out.println("<h1>Candidat non trouvé</h1>");
        return;
    }

    List<CompetenceCandidat> competences = candidat.getListCompetence(); 
%>

<%@include file="header.jsp" %>
<body>
    <h1>CV de <%= candidat.getNomCandidat() + " " + candidat.getPrenomCandidat() %></h1>

    <div class="cv-section">
        <h2>Informations personnelles</h2>
        <p><strong>Nom :</strong> <%= candidat.getNomCandidat() %></p>
        <p><strong>Prénom :</strong> <%= candidat.getPrenomCandidat() %></p>
        <p><strong>Date de naissance :</strong> <%= candidat.getDateNaissance() %></p>
        <p><strong>Adresse :</strong> <%= candidat.getAdresse() %></p>
        <p><strong>Genre :</strong> <%= candidat.getGenre().getLabel() %></p>
    </div>

    <div class="cv-section">
        <h2>Compétences et Expériences</h2>
        <%
            if (competences != null && !competences.isEmpty()) {
        %>
            <ul>
                <%
                    for (CompetenceCandidat competenceCandidat : competences) {
                %>
                <li>
                    <strong><%= competenceCandidat.getCompetence().getNomCompetence() %></strong> - 
                    <%= competenceCandidat.getExperience() %> ans d'expérience
                </li>
                <%
                    }
                %>
            </ul>
        <%
            } else {
        %>
            <p>Aucune compétence enregistrée pour ce candidat.</p>
        <%
            }
        %>
    </div>
</body>
</html>
