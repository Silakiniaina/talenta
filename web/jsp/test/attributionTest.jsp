<%@ page import="java.util.List" %>
<%@ page import="model.Test" %> 

<%
    List<Test> tests = (List<Test>)request.getAttribute("tests");
    String idCandidat = (String)request.getAttribute("idCandidat");
%>
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
                                        <h3 class="card-title">Attribution Test</h3>
                                        <form class="forms-sample" action="attribuerTest" method="post">
                                            <input type="hidden" name="idCandidat" value="<%= idCandidat %>">
                                            <div class="form-group">
                                                <label for="test">Test</label>
                                                <select class="form-control" id="test" name="idTest">
                                                    <%
                                                        for (Test test : tests) {
                                                            out.println("<option value=\"" + test.getIdTest() + "\">" + test.getTitre() + "</option>");
                                                        }
                                                    %>
                                                </select> 
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Attribuer</button>
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
