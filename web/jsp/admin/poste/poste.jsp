<%@ page import="java.util.List" %>
<%@ page import="model.Poste" %>
<%
    List<Poste> postes = (List<Poste>)request.getAttribute("postes");
%>
<%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/sidebarAdmin.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/navbar.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-lg-12 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h2>Liste des postes</h2>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Nom</th>
                                                        <th>Departement</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Poste poste : postes) {
                                                    %>
                                                    <tr>
                                                        <td><%= poste.getIdPoste() %></td>
                                                        <td><%= poste.getNomPoste() %></td>
                                                        <td><%= poste.getDepartement().getNomDepartement() %></td>
                                                        <td><a href="recrutement?mode=i&idPoste=<%= poste.getIdPoste() %>">Recruter</a></td>
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
