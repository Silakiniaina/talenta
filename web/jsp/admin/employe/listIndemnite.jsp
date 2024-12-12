<%@ page import="java.util.List" %>
<%@ page import="model.employe.Indemnite" %>
<%
    List<Indemnite> indemnites = (List<Indemnite>)request.getAttribute("indemnites");
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
                                            <h2>Liste des indemnites</h2>
                                            <a href="addIndemnite" type="button" class="btn btn-md btn-outline-success ">Ajouter Indemnite</a>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Employe</th>
                                                        <th>Type</th>
                                                        <th>montant</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Indemnite indemnite : indemnites) {
                                                    %>
                                                    <tr>
                                                        <td><%= indemnite.getIdIndemnite() %></td>
                                                        <td><%= indemnite.getEmploye().getNomComplet() %></td>
                                                        <td><%= indemnite.getTypeIndemnite().getNomTypeIndemnite() %></td>
                                                        <td><%= indemnite.getMontantIndemnite() %></td>
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
