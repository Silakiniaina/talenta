<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.TypeFinContrat" %>

        
        <%@ include file="../../shared/head.jsp" %>
        <body>
            <div class="container-scroller">
                <%@ include file="../../shared/navbar.jsp" %>
                <div class="container-fluid page-body-wrapper">
                    <%@ include file="../../shared/sidebarEmploye.jsp" %>
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="row">
                                <div class="col-md-6 grid-margin stretch-card">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title"> Faire une demande de demission </h4>
                                           
                                    <form class="forms-sample" action="demission-add" method="post">
                                        <div class="form-group">
                                            <label for="motif">Motif :</label>
                                            <input type="text" class="form-control" id="motif" name="motif" placeholder="Motif de la demission" required>
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
