<%@ page import="java.util.List" %>
<%@ page import="model.Conge" %>
<%
    List<Conge> planningConges = (List<Conge>) request.getAttribute("planningConges");
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
                                    <h2>Planning des Conges</h2>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>ID Conge</th>
                                                    <th>Employe</th>
                                                    <th>Date de Debut</th>
                                                    <th>Date de Fin</th>
                                                    <th>Type de Conge</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (Conge conge : planningConges) {
                                                %>
                                                <tr>
                                                    <td><%= conge.getIdConge() %></td>
                                                    <td><%= conge.getEmploye().getCandidat().getNomCandidat() %></td>
                                                    <td><%= conge.getDateDebut() %></td>
                                                    <td><%= conge.getDateFin() %></td>
                                                    <td><%= conge.getTypeConge().getNomType() %></td>
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
