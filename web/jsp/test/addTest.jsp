    <%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Creation Test</h4>
                                        <form class="forms-sample" action="addTest" method="post">
                                            <div class="form-group">
                                                <label for="titre">Titre :</label>
                                                <input type="text" class="form-control" id="titre" name="titre" placeholder="Nom">
                                            </div>
                                            <div class="form-group">
                                                <label for="dateCreation">Date creation :</label>
                                                <input type="datetime-local" class="form-control" id="dateCreation" name="dateCreation" placeholder="Date Creation">
                                            </div>
                                            <div class="form-group">
                                                <label for="desc">Description :</label>
                                                <textarea name="desc" id="desc" placeholder="Description"  class="form-control"></textarea>
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html>
