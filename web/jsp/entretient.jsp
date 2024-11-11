<%@page import="java.util.List"%>
<%@page import="model.Candidat"%>
<%@page import="model.SoftSkill"%>
<%
    List<Candidat> listeCandidats = (List<Candidat>) request.getAttribute("candidats");
    List<SoftSkill> listeSoftSkills = (List<SoftSkill>) request.getAttribute("softSkills");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sélection Candidat et Soft Skills</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/form.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <div class="form-container">
        <div class="form-box">
            <h1>Sélection Candidat et Soft Skills</h1>

            <form method="post" action="CandidatSoftSkillServlet">
                <!-- Candidat Selection Section -->
                <div class="section mb-3">
                    <h2>Choisir un Candidat</h2>
                    <select class="form-select" name="candidatId" required>
                        <option value="" disabled selected>-- Sélectionner un Candidat --</option>
                        <%
                            if (listeCandidats != null) {
                                for (Candidat candidat : listeCandidats) {
                        %>
                        <option value="<%=candidat.getId()%>"><%=candidat.getNom()%> <%=candidat.getPrenom()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Soft Skills Section with Checkboxes -->
                <div class="section mb-3">
                    <h2>Soft Skills</h2>
                    <h6>Sélectionner les soft skills pertinents</h6>
                    <%
                        if (listeSoftSkills != null) {
                            for (SoftSkill skill : listeSoftSkills) {
                    %>
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" name="skill<%=skill.getId()%>" id="skill<%=skill.getId()%>" value="<%=skill.getId()%>">
                        <label class="form-check-label" for="skill<%=skill.getId()%>"><%=skill.getNom()%></label>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>

                <input type="hidden" name="method" value="insert">

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary">Soumettre</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
