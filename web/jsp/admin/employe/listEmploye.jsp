<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.Genre" %>
<%@ page import="model.Poste" %>
<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
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
                                        <h2>Liste des employes</h2>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Prenom</th>
                                                        <th>Nom</th>
                                                        <th>Genre</th>
                                                        <th>Poste</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Employe employe : employes) {
                                                    %>
                                                    <tr>
                                                        <td><%= employe.getIdEmploye() %></td>
                                                        <td><%= employe.getCandidat().getPrenomCandidat() %></td>
                                                        <td><%= employe.getCandidat().getNomCandidat() %></td>
                                                        <td><%= employe.getCandidat().getGenre().getLabel() %></td>
                                                        <td><%= employe.getPoste().getNomPoste() %></td>
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
