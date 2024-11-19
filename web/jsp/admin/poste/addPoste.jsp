<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="model.Departement" %> 

<%
    List<Departement> departements = (List<Departement>)request.getAttribute("departements");
    List<Competence> competences = (List<Competence>)request.getAttribute("competences");


%>
<%@include file="../shared/header.jsp" %>
    <h1>Ajouter une poste</h1>
    <form action="poste" method="post">
        <label for="nom">nom :</label>
        <input type="text" id="nom" name="nom" required><br><br>

        <label for="dept">Departement :</label>
        <select id="dept" name="dept" required>
            <%
                for (Departement dept : departements) {
                    out.println("<option value=\"" + dept.getIdDepartement() + "\">" + dept.getNomDepartement() + "</option>");
                }
            %>
        </select><br><br>
 
        <h3>Compétences requises</h3>
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
            <label for="experience">Expérience (années) :</label>
            <input type="number" name="experiences" required><br><br>
            <%
                out.println("</div>");
            %>
        </div>

        <button type="button" onclick="addCompetence()">Ajouter une autre compétence</button><br><br>

        <input type="submit" value="Ajouter">
    </form>
    <script>
        // JavaScript pour ajouter dynamiquement une nouvelle ligne de compétence
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
                <label for="experience">Expérience (années) :</label>
                <input type="number" name="experiences" required><br><br>
            `;
            container.appendChild(newRow);
        }
    </script>
</body>
</html>