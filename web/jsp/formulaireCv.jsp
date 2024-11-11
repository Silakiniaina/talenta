<%@page import="java.util.List"%>
<%@page import="model.Competence"%>
<%@page import="model.Personne" %>
<%
    List<Competence> listeProg = (List<Competence>) request.getAttribute("prog");
    List<Competence> listeBase = (List<Competence>) request.getAttribute("base");
    List<Competence> listeOutil = (List<Competence>) request.getAttribute("outil");
    List<Competence> listeDiplomes = (List<Competence>) request.getAttribute("diplomes");
    List<Competence> listeLangues = (List<Competence>) request.getAttribute("langues");
    List<Competence> listeAutres = (List<Competence>) request.getAttribute("autres");
    Personne p = (Personne)request.getAttribute("personne");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compétences</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/form.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <div class="form-container">
        <div class="form-box">
            <h1>Competences</h1>
            <h6>Cocher toutes les competences et diplomes acquis</h6>

            <form method="post" action="CompetenceServlet">
                <!-- Techniques Section with Checkboxes -->
                <div class="section">
                    <h2>Techniques</h2>
                    <h5>- Programmation</h5>
                    <%
                        if (listeProg != null) {
                            for (Competence competence : listeProg) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="techniques" id="tech<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="tech<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>

                    <h5>- Base de donnees </h5>
                    <%
                        if (listeBase != null) {
                            for (Competence competence : listeBase) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="techniques" id="tech<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="tech<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>

                    <h5>- Outil</h5>
                    <%
                        if (listeOutil != null) {
                            for (Competence competence : listeOutil) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="techniques" id="tech<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="tech<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>

                <!-- Diplômes Section with Radios -->
                <div class="section">
                    <h2>Diplomes</h2>
                    <%
                        if (listeDiplomes != null) {
                            for (Competence competence : listeDiplomes) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="radio" class="form-check-input" name="diplomes" id="dip<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="dip<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>

                <!-- Langues Section with Checkboxes -->
                <div class="section">
                    <h2>Langues</h2>
                    <%
                        if (listeLangues != null) {
                            for (Competence competence : listeLangues) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="langues" id="lang<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="lang<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>

                <!-- Autres Section with Checkboxes -->
                <div class="section">
                    <h2>Autres Competences</h2>
                    <%
                        if (listeAutres != null) {
                            for (Competence competence : listeAutres) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="autres" id="autre<%=competence.getId()%>" value="<%=competence.getId()%>">
                        <label class="form-check-label" for="autre<%=competence.getId()%>"><%=competence.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <input type="hidden" name="idPersonne" value="<%= p.getId() %>">
                <input type="hidden" name="method" value="insert">

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
