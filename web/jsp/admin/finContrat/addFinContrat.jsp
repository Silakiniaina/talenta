<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.TypeFinContrat" %>

<%
List<Employe> employes = (List<Employe>) request.getAttribute("employes");
     
List<TypeFinContrat> typesContrat = (List<TypeFinContrat>) request.getAttribute("typesContrat"); 
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
                                            <h4 class="card-title">Ajouter une fin de contrat </h4>
                                           
                                    <% for(Employe e : employes) 
                                    { 
                                        out.println(e.getCandidat().getNomCandidat());
                                        
                                    } 
                                    out.println("lalala"); %>
                                    <form class="forms-sample" action="/retraite-add" method="post">
                                        <div class="form-group">
                                            <label for="employe">Employe :</label>
                                            <select class="form-control" id="employe" name="employe">
                                                <option value="">Selectionner un employe</option>
                                                <% for (Employe employe : employes) { %>
                                                    <option value="<%= employe.getIdEmploye() %>"><%= employe.getCandidat().getNomCandidat() %> - <%= employe.getCandidat().getPrenomCandidat() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="type_contrat">Type de Fin de Contrat :</label>
                                            <select class="form-control" id="type_contrat" name="typeContrat">
                                                <option value="">Selectionner un type de contrat</option>
                                                <% for (TypeFinContrat type : typesContrat) { %>
                                                    <option value="<%= type.getId() %>"><%= type.getLabel() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="motif">Motif :</label>
                                            <input type="text" class="form-control" id="motif" name="motif" placeholder="Motif de la retraite" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="depot">Date depot :</label>
                                            <input type="date" class="form-control" id="depot" name="depot" required>
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
