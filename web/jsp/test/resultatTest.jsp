<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import=" model.ResultatTestCandidat" %>
<%@ page import=" model.Candidat" %>
<%@ page import=" model.Recrutement" %>
<%
    List<ResultatTestCandidat> resultats = (List<ResultatTestCandidat>)request.getAttribute("resultats");
%>

    <%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
            <%@include file="../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">List Resultat</h4>
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID Recrutement</th>
                                                    <th>Candidat</th>
                                                    <th>Nombre Fausse</th>
                                                    <th>Nombre Correcte</th>
                                                    <th>Total Question</th>
                                                    <th>% Reussite</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (resultats != null && !resultats.isEmpty()) {
                                                        for (ResultatTestCandidat resultat : resultats) {
                                                            Candidat candidat = resultat.getCandidat();
                                                            Recrutement recrutement = resultat.getRecrutement();
                                                %>
                                                    <tr>
                                                        <td><%= recrutement.getIdRecrutement() %></td>
                                                        <td><%= candidat.getNomCandidat() %> </td>
                                                        <td><%= resultat.getNombreReponseFausse() %></td>
                                                        <td><%= resultat.getNombreReponseCorrecte() %></td>
                                                        <td><%= resultat.getNombreTotalQuestion() %></td>
                                                        <td><%= resultat.getPourcentageReussite() %></td>
                                                        <td><%= resultat.getStatus() %></td>
                                                        <td>
                                                            <a type="button" class="btn btn-sm btn-outline-danger" href="#">Embaucher</a></td>
                                                        </td>
                                                    </tr>
                                                <%
                                                        }
                                                    } else {
                                                %>
                                                <tr>
                                                    <td colspan="7">Aucun resultat trouvé.</td>
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
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html>
