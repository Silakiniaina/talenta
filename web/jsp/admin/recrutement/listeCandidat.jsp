<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%
    List<Candidat> candidats = (List<Candidat>)request.getAttribute("candidats");
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
                                    <h4 class="card-title">List des candidatures</h4>
                                    <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nom</th>
                                                <th>Prenom</th>
                                                <th>Date Naissance</th>
                                                <th>Genre</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                if (candidats != null && !candidats.isEmpty()) {
                                                    for (Candidat candidat : candidats) {
                                            %>
                                            <tr>
                                                <td><%= candidat.getIdCandidat() %></td>
                                                <td><%= candidat.getNomCandidat() %></td>
                                                <td><%= candidat.getPrenomCandidat() %></td>
                                                <td><%= candidat.getDateNaissance() %></td>
                                                <td><%= candidat.getAdresse() %></td>
                                                <td><%= candidat.getGenre().getLabel() %></td>
                                                <td><a type="button" class="btn btn-sm btn-outline-primary" href="detailsCandidat?idCandidat=<%= candidat.getIdCandidat() %>">Voir CV</a></td>
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
