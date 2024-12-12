<%@ page import="java.util.List" %>
<%@ page import="model.employe.Absence" %>
<%
    List<Absence> absences = (List<Absence>)request.getAttribute("absences");
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
                                            <h2>Liste des absences</h2>
                                            <a href="addAbsence" type="button" class="btn btn-md btn-outline-success ">Ajouter Absence</a>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Employe</th>
                                                        <th>Debut</th>
                                                        <th>Fin</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Absence absence : absences) {
                                                    %>
                                                    <tr>
                                                        <td><%= absence.getIdAbsence() %></td>
                                                        <td><%= absence.getEmploye().getNomComplet() %></td>
                                                        <td><%= absence.getDateDebut() %></td>
                                                        <td><%= absence.getDateFin() %></td>
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
