<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import=" model.PreselectionCandidat" %>
<%@ page import=" model.Candidat" %>
<%@ page import=" model.Recrutement" %>
<%
    HashMap<String,List<PreselectionCandidat>> mapSelections = (HashMap<String,List<PreselectionCandidat>>) request.getAttribute("preselections");
    List<PreselectionCandidat> preselectionne = mapSelections.get("preselectionne");
    List<PreselectionCandidat> normal = mapSelections.get("normal");
%>

    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
            <%@include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Candidat preselectionnes</h4>
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
                                                    if (preselectionne != null && !preselectionne.isEmpty()) {
                                                        for (PreselectionCandidat preselection : preselectionne) {
                                                            Candidat candidat = preselection.getCandidat();
                                                            Recrutement recrutement = preselection.getRecrutement();
                                                %>
                                                    <tr>
                                                        <td><%= candidat.getIdCandidat() %></td>
                                                        <td><%= candidat.getNomCandidat() %> <%= candidat.getPrenomCandidat() %></td>
                                                        <td><%= recrutement.getPoste().getNomPoste() %></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(1) %>"> <%= preselection.getPourcentageCompetence() %> <i class="ti-arrow-<%= preselection.getTypeArrow(1) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(2) %>"> <%= preselection.getPourcentageDiplome() %> <i class="ti-arrow-<%= preselection.getTypeArrow(2) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(3) %>"> <%= preselection.getPourcentageExperience() %> <i class="ti-arrow-<%= preselection.getTypeArrow(3) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(4) %>"> <%= preselection.getScoreGlobale() %> <i class="ti-arrow-<%= preselection.getTypeArrow(4) %>"></i></td>
                                                        <td colspan="2">
                                                            <a type="button" class="btn btn-sm btn-outline-primary" href="#">Simuler</a>
                                                            <a type="button" class="btn btn-sm btn-outline-danger" href="preselectionManagement?candidat=<%= candidat.getIdCandidat() %>&recrutement=<%= recrutement.getIdRecrutement() %>&mode=d">Depreselectionner</a>
                                                        </td>
                                                    </tr>
                                                <%
                                                        }
                                                    } else {
                                                %>
                                                <tr>
                                                    <td colspan="7">Aucun candidat présélectionné.</td>
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
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Triage Normal</h4>
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
                                                    if (normal != null && !normal.isEmpty()) {
                                                        for (PreselectionCandidat preselection : normal) {
                                                            Candidat candidat = preselection.getCandidat();
                                                            Recrutement recrutement = preselection.getRecrutement();
                                                %>
                                                    <tr>
                                                        <td><%= candidat.getIdCandidat() %></td>
                                                        <td><%= candidat.getNomCandidat() %> <%= candidat.getPrenomCandidat() %></td>
                                                        <td><%= recrutement.getPoste().getNomPoste() %></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(1) %>"> <%= preselection.getPourcentageCompetence() %> <i class="ti-arrow-<%= preselection.getTypeArrow(1) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(2) %>"> <%= preselection.getPourcentageDiplome() %> <i class="ti-arrow-<%= preselection.getTypeArrow(2) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(3) %>"> <%= preselection.getPourcentageExperience() %> <i class="ti-arrow-<%= preselection.getTypeArrow(3) %>"></i></td>
                                                        <td class="text-<%= preselection.getTypeNiveau(4) %>"> <%= preselection.getScoreGlobale() %> <i class="ti-arrow-<%= preselection.getTypeArrow(4) %>"></i></td>
                                                        <td colspan="2">
                                                            <a type="button" class="btn btn-sm btn-outline-primary" href="preselectionManagement?candidat=<%= candidat.getIdCandidat() %>&recrutement=<%= recrutement.getIdRecrutement() %>&mode=p">Preselectionner</a>
                                                            <a type="button" class="btn btn-sm btn-outline-danger" href="preselectionManagement?candidat=<%= candidat.getIdCandidat() %>&recrutement=<%= recrutement.getIdRecrutement() %>&mode=e">Eliminer</a></td>
                                                        </td>
                                                    </tr>
                                                <%
                                                        }
                                                    } else {
                                                %>
                                                <tr>
                                                    <td colspan="7">Aucun candidat trouvé.</td>
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
