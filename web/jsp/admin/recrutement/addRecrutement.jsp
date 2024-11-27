<%
    String idPoste = (String)request.getAttribute("idPoste");
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
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Default form</h4>
                                        <p class="card-description"> Basic form layout </p>
                                        <form class="forms-sample" action="recrutement" method="post">
                                            <input type="hidden" name="poste" value="<%= idPoste %>">
                                            <div class="form-group">
                                                <label for="date_debut">Date debut :</label>
                                                <input type="date" class="form-control" id="date_debut" name="debut" placeholder="Date debut">
                                            </div>
                                            <div class="form-group">
                                                <label for="date_fin">Date Fin :</label>
                                                <input type="date" class="form-control" id="date_fin" name="fin" placeholder="Date debut">
                                            </div>
                                            <div class="form-group">
                                                <label for="nombre">Nombre : </label>
                                                <input type="number" name="nombre" class="form-control" id="nombre" placeholder="Nombre poste">
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Valider</button>
                                        </form>
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
