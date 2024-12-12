<%@ page import="java.util.List" %>
<%@ page import="model.employe.Prime" %>
<%
    List<Prime> primes = (List<Prime>)request.getAttribute("primes");
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
                                            <h2>Liste des primes</h2>
                                            <a href="addPrime" type="button" class="btn btn-md btn-outline-success ">Ajouter Prime</a>
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
                                                        for (Prime prime : primes) {
                                                    %>
                                                    <tr>
                                                        <td><%= prime.getIdPrime() %></td>
                                                        <td><%= prime.getEmploye().getNomComplet() %></td>
                                                        <td><%= prime.getTypePrime().getNomTypePrime() %></td>
                                                        <td><%= prime.getMontantPrime() %></td>
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
