<%@ page import="model.InformationEmploye" %>
<%
    InformationEmploye employe = (InformationEmploye) request.getAttribute("employe");
%>
    <%@ include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@ include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@ include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Informations de l'Employé</h4>
                                    <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                                <th>Email</th>
                                                <th>Genre</th>
                                                <th>Congés Restants</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                if (employe != null) {
                                            %>
                                            <tr>
                                                <td><%= employe.getIdEmploye() %></td>
                                                <td><%= employe.getNom() %></td>
                                                <td><%= employe.getPrenom() %></td>
                                                <td><%= employe.getEmail() %></td>
                                                <td><%= employe.getGenre() %></td>
                                                <td><%= employe.getJoursAcquis() %></td>
                                                <td><%= employe.getJoursPris() %></td>
                                                <td><%= employe.getJoursRestants() %></td>
                                            </tr>
                                            <%
                                                } else {
                                            %>
                                            <tr>
                                                <td colspan="6">Aucune information disponible. Vous n'êtes pas connecté.</td>
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
