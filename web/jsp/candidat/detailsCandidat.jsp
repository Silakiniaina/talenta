<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.Competence" %>
<%@ page import="model.Experience" %>
<%@ page import="model.Education" %>
<%@ page import="java.util.Date" %>
<%
    Candidat candidat = (Candidat)request.getAttribute("candidat");

    if (candidat == null) {
        out.println("<h1>Candidat non trouvé</h1>");
        return;
    }

    List<Competence> competences = candidat.getListCompetence(); 
    List<Experience> experiences = candidat.getListExperience();
    List<Education> educations = candidat.getListEducation();
%>

<%@include file="../header.jsp" %>
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
        <h2>Compétences</h2>
        <%
            if (competences != null && !competences.isEmpty()) {
        %>
            <ul>
                <%
                    for (Competence competenceCandidat : competences) {
                %>
                <li>
                    <strong><%= competenceCandidat.getNomCompetence() %></strong> - 
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
    <div class="cv-section">
        <h2>Experiences</h2>
        <%
            if (experiences != null && !experiences.isEmpty()) {
        %>
            <ul>
                <%
                    for (Experience experienceCandidat : experiences) {
                %>
                <li>
                    <%= experienceCandidat.getDateDebut() %> - <%= experienceCandidat.getDateFin() %> : <strong><%= experienceCandidat.getDescription() %></strong> 
                </li>
                <%
                    }
                %>
            </ul>
        <%
            } else {
        %>
            <p>Aucune experience enregistrée pour ce candidat.</p>
        <%
            }
        %>
    </div>

     <div class="cv-section">
        <h2>Educations</h2>
        <%
            if (educations != null && !educations.isEmpty()) {
        %>
            <ul>
                <%
                    for (Education educationsCandidat : educations) {
                %>
                <li>
                    <%= educationsCandidat.getDateDebut() %> - <%= educationsCandidat.getDateFin() %> [<%= educationsCandidat.getTypeDiplome().getLabel() %>] : <strong><%= educationsCandidat.getNomEcole() %></strong> 
                </li>
                <%
                    }
                %>
            </ul>
        <%
            } else {
        %>
            <p>Aucune education enregistrée pour ce candidat.</p>
        <%
            }
        %>
    </div>
</body>
</html>
