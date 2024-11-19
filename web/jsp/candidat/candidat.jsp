<%@ page import="java.util.Date" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.Genre" %>
<%@ page import="model.Competence" %>
<%@ page import="java.util.List" %>
<%
    List<Genre> genres = (List<Genre>)request.getAttribute("genres");
    List<Competence> competences = (List<Competence> )request.getAttribute("competences");
    String recr = (String)request.getAttribute("recr");
%>

<%@include file="../shared/header.jsp" %>

<body>
    <h1>Ajouter un Candidat</h1>
    <form action="candidat" method="post">
        <label for="nomCandidat">Nom :</label>
        <input type="text" id="nomCandidat" name="nom" required><br><br>

        <label for="prenomCandidat">Prénom :</label>
        <input type="text" id="prenomCandidat" name="prenom" required><br><br>

        <label for="dateNaissance">Date de naissance :</label>
        <input type="date" id="dateNaissance" name="dtn" required><br><br>

        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" required><br><br>

        <label for="genre">Genre :</label>
        <select id="genre" name="genre" required>
            <%
                for (Genre genre : genres) {
                    out.println("<option value=\"" + genre.getIdGenre() + "\">" + genre.getLabel() + "</option>");
                }
            %>
        </select><br><br>

        
        <!-- Section des compétences -->
        <h3>Compétences</h3>
        <div id="competence-container">
            <%
                out.println("<div class='competence-row'>");
            %>
            <label for="competence">Compétence :</label>
            <select name="competences" required>
                <%
                    for (Competence competence : competences) {
                        out.println("<option value=\"" + competence.getIdCompetence() + "\">" + competence.getNomCompetence() + "</option>");
                    }
                %>
            </select>
            <label for="experience">Années d'expérience :</label>
            <input type="number" name="experiences" required><br><br>
            <%
                out.println("</div>");
            %>
        </div>
        <input type="hidden" name="recr" value="<%= recr %>">
        <button type="button" onclick="addCompetence()">Ajouter une autre compétence</button><br><br>
        <input type="submit" value="Ajouter">
    </form>

    <script>
        function addCompetence() {
            var container = document.getElementById("competence-container");
            var newRow = document.createElement("div");
            newRow.classList.add("competence-row");
            newRow.innerHTML = `
                <label for="competence">Compétence :</label>
                <select name="competences" required>
                    <% 
                        for (Competence competence : competences) {
                            out.println("<option value=\"" + competence.getIdCompetence() + "\">" + competence.getNomCompetence() + "</option>");
                        } 
                    %>
                </select>
                <label for="experience">Années d'expérience :</label>
                <input type="number" name="experiences" required><br><br>
            `;
            container.appendChild(newRow);
        }
    </script>
</body>
</html>
