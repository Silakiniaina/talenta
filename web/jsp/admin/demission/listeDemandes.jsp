<%@ page import="java.util.List" %>
<%@ page import="model.DemandeDemission" %>
<%@ page import="model.Candidat" %>
<%
    List<DemandeDemission> demandes = (List<DemandeDemission>) request.getAttribute("demandes");
%>
<%@ include file="../../shared/head.jsp" %>
<body>
    <div class="container-scroller">
        <%@ include file="../../shared/navbar.jsp" %>
        <div class="container-fluid page-body-wrapper">
            <%@ include file="../../shared/sidebarAdmin.jsp" %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h2>Liste des demandes de demission</h2>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Candidat</th>
                                                    <th>Date de depot</th>
                                                    <th>Motif</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    for (DemandeDemission demande : demandes) {
                                                %>
                                                <tr>
                                                    <td><%= demande.getId() %></td>
                                                    <td><%= demande.getCandidat().getNomCandidat() %> <%= demande.getCandidat().getPrenomCandidat() %></td>
                                                    <td><%= demande.getDepot() %></td>
                                                    <td><%= demande.getMotif() %></td>
                                                    <td>
                                                        <a href="demission-traitement?etat=approuvee&demandeId=<%= demande.getId() %>&idCandidat=<%= demande.getCandidat().getIdCandidat() %>">Approuver</a> | 
                                                        <a href="demission-traitement?etat=refusee&demandeId=<%= demande.getId() %>&idCandidat=<%= demande.getCandidat().getIdCandidat() %>">Refuser</a>
                                                    </td>
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
                </div>
                <%@ include file="../../shared/footer.jsp" %>
            </div>
        </div>
    </div>
    <%@ include file="../../shared/script.jsp" %>
</body>
</html>
