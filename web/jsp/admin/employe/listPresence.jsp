<%@ page import="java.util.List" %>
<%@ page import="model.employe.Presence" %>
<%
    List<Presence> presences = (List<Presence>)request.getAttribute("presences");
%>
<%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-lg-12 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex mb-3" style="justify-content: space-between; align-items: center;">
                                            <h2>Liste des presences</h2>
                                            <a href="addPresence" type="button" class="btn btn-md btn-outline-success ">Ajouter Presence</a>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Employe</th>
                                                        <th>Entree</th>
                                                        <th>Sortie</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Presence presence : presences) {
                                                    %>
                                                    <tr>
                                                        <td><%= presence.getIdPresence() %></td>
                                                        <td><%= presence.getEmploye().getCandidat().getPrenomCandidat() %> <%= presence.getEmploye().getCandidat().getNomCandidat() %></td>
                                                        <td><%= presence.getDateHeureEntree() %></td>
                                                        <td><%= presence.getDateHeureSortie() %></td>
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
