<%@ page import="java.util.List" %>
<%@ page import="model.Conge" %>
<%
    List<Conge> planningConges=  (List<Conge>) request.getAttribute("planningConges");
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
                                <h4 class="card-title">Planning des Congés</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID Congé</th>
                                                <th>Employé</th>
                                                <th>Date de Début</th>
                                                <th>Date de Fin</th>
                                                <th>Type de Congé</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% 
                                                if (planningConges != null && !planningConges.isEmpty()) {
                                                    for (Conge conge : planningConges) {
                                            %>
                                            <tr>
                                                <td><%= conge.getIdConge() %></td>
                                                <td><%= conge.getEmploye().getCandidat().getNom() %></td>
                                                <td><%= conge.getDateDebut() %></td>
                                                <td><%= conge.getDateFin() %></td>
                                                <td><%= conge.getTypeConge().getNomTypeConge() %></td>
                                            </tr>
                                            <% 
                                                    }
                                                } else {
                                            %>
                                            <tr>
                                                <td colspan="5">Aucun congé trouvé.</td>
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
