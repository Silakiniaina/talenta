<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Test" %>
<%@ page import="java.util.List" %>

<%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-lg-12 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h2>Liste des tests</h2>
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Titre</th>
                                                        <th>Description</th>
                                                        <th>Responsable</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        List<Test> tests = (List<Test>) request.getAttribute("tests");
                                                        if(tests != null) {
                                                            for(Test test : tests) {
                                                    %>
                                                    <tr>
                                                        <td><%= test.getIdTest() %></td>
                                                        <td><%= test.getTitre() %></td>
                                                        <td><%= test.getDescription() %></td>
                                                        <td><%= test.getResponsable().getNomAdmin() %></td>
                                                        <td><a type="button" class="btn btn-sm btn-outline-info" href="detailsTest?test=<%= test.getIdTest() %>">Details</a></td>
                                                    </tr>
                                                    <%
                                                            }
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
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html>
