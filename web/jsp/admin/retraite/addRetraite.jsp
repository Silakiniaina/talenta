<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.TypeContrat" %>

<%
    List<Employe> employes = (List<Employe>) request.getAttribute("employes"); 
    List<TypeContrat> typesContrat = (List<TypeContrat>) request.getAttribute("typesContrat"); 
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
                        <div class="col-md-6 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Ajouter une Retraite</h4>
                                    <p class="card-description"> Formulaire de retraite </p>
                                    <form class="forms-sample" action="/retraite-add" method="post">
                                        <div class="form-group">
                                            <label for="employe">Employé :</label>
                                            <select class="form-control" id="employe" name="employe">
                                                <option value="">Sélectionner un employé</option>
                                                <% for (Employe employe : employes) { %>
                                                    <option value="<%= employe.getId() %>"><%= employe.getNom() %> - <%= employe.getPrenom() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="type_contrat">Type de Contrat :</label>
                                            <select class="form-control" id="type_contrat" name="typeContrat">
                                                <option value="">Sélectionner un type de contrat</option>
                                                <% for (TypeContrat type : typesContrat) { %>
                                                    <option value="<%= type.getId() %>"><%= type.getLabel() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="motif">Motif :</label>
                                            <input type="text" class="form-control" id="motif" name="motif" placeholder="Motif de la retraite" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="preavis">Date de préavis :</label>
                                            <input type="date" class="form-control" id="preavis" name="preavis" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="date_fin">Date de fin :</label>
                                            <input type="date" class="form-control" id="date_fin" name="dateFin" required>
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
