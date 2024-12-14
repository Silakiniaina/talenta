<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
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
                                    <h4 class="card-title">Liste des Employés</h4>
                                    <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                                <th>CSP</th>
                                                <th>Salaire Base</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                if (employes != null && !employes.isEmpty()) {
                                                    for (Employe employe : employes) {
                                            %>
                                            <tr>
                                                <td><%= employe.getIdEmploye() %></td>
                                                <td><%= employe.getCandidat().getNomCandidat() %></td>
                                                <td><%= employe.getCandidat().getPrenomCandidat() %></td>
                                                <td><%= employe.getCsp().getCodeCsp() %></td>
                                                <td><%= employe.getSalaireBase() %></td>
                                                <td>
                                                    <a type="button" class="btn btn-sm btn-outline-primary" href="fichePaie?idEmploye=<%= employe.getIdEmploye() %>">Voir fiche</a>
                                                </td>
                                            </tr>
                                            <%
                                                    }
                                                } else {
                                            %>
                                            <tr>
                                                <td colspan="7">Aucun employé trouvé.</td>
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
