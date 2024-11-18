<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Poste" %>
<%@ page import="model.Recrutement" %> 
<%@ page import="model.Competence" %> 
<%@ page import="model.CompetenceRequise" %> 
<%
    List<Recrutement> recrutements = (List<Recrutement>)request.getAttribute("recrutements");
    List<Competence> competences = (List<Competence>)request.getAttribute("competences");
%>
<%@include file="header.jsp" %>
    <h2>Liste des Recrutements</h2>
    <table border="1" class="recruitment-table">
        <tr>
            <th>ID</th>
            <th>Date de début</th>
            <th>Date de fin</th>
            <th>Nombre</th>
            <th>Poste</th>
            <th>Competence Requise</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            for (Recrutement recrutement : recrutements) {
                out.println("<tr>");
                out.println("<td>" + recrutement.getIdRecrutement() + "</td>");
                out.println("<td>" + recrutement.getDateDebut() + "</td>");
                out.println("<td>" + recrutement.getDateFin() + "</td>");
                out.println("<td>" + recrutement.getNombre() + "</td>");
                out.println("<td>" + recrutement.getPoste().getNomPoste() + "</td>");

                out.println("<td>");
                List<CompetenceRequise> competencesRequises = recrutement.getPoste().getListCompetence();
                for (CompetenceRequise compRec : competencesRequises) {
                    out.println(compRec.getCompetence().getNomCompetence() + " (" + compRec.getExperience() + " ans)<br>");
                }
                out.println("<td>" + recrutement.getStatus().getLabel() + "</td>");
                out.println("</td>");
                out.println("<td><a href=listeCandidat?idRecrutement="+recrutement.getIdRecrutement()+" >Liste Candidat</a></td>");
                out.println("<td><a href=preselection?idRecrutement="+recrutement.getIdRecrutement()+" >Liste Preselection</a></td>");
                out.println("<td><a href=resultatTest?idRecrutement="+recrutement.getIdRecrutement()+" >Resultat Test</a></td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>
