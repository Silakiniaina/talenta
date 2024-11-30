<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.Candidat" %>
<%@ page import="java.sql.Date" %>

<%
    List<Employe> employesRetraite = (List<Employe>) request.getAttribute("employesRetraite");
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
                                    <h2>Liste des employes prets pour la retraite</h2>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>ID Employe</th>
                                                    <th>Nom</th>
                                                    <th>Date de Naissance</th>
                                                    <th>Age</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    for (Employe employe : employesRetraite) {
                                                %>
                                                    <tr>
                                                        <td><%= employe.getIdEmploye() %></td>
                                                        <td><%= employe.getCandidat().getNomCandidat() + " " + employe.getCandidat().getPrenomCandidat() %></td>
                                                        <td><%= employe.getCandidat().getDateNaissance() %></td>
                                                        <td><%= employe.getAge() %></td>
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
