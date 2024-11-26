<%@ page import="java.util.List" %>
<%@ page import=" model.PreselectionCandidat" %>
<%@ page import=" model.Candidat" %>
<%@ page import=" model.Recrutement" %>

    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/sidebarAdmin.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/navbar.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Preselection candidat</h4>
                                    <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Nom</th>
                                                <th>Poste</th>
                                                <th>% Competence</th>
                                                <th>% diplome</th>
                                                <th>% experience</th>
                                                <th>% globale</th>
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
                                                    <td class="text-<%= preselection.getTypeNiveau(1) %>>"> <%= candidat.getPourcentageCompetence() %> <i class="mdi mdi-arrow-<%= preselection.getTypeArrow(1) %>"></i></td>
                                                    <td class="text-<%= preselection.getTypeNiveau(2) %>>"> <%= candidat.getPourcentageDiplome() %> <i class="mdi mdi-arrow-<%= preselection.getTypeArrow(2) %>"></i></td>
                                                    <td class="text-<%= preselection.getTypeNiveau(3) %>>"> <%= candidat.getPourcentageExperience() %> <i class="mdi mdi-arrow-<%= preselection.getTypeArrow(3) %>"></i></td>
                                                    <td class="text-<%= preselection.getTypeNiveau(4) %>>"> <%= candidat.getScoreGlobale() %> <i class="mdi mdi-arrow-<%= preselection.getTypeArrow(4) %>"></i></td>
                                                    <td><a href="#" type="button">Test</a></td>
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
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="../../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../../shared/script.jsp" %>
    </body>
</html>
