<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%
    List<Candidat> candidats = (List<Candidat>)request.getAttribute("candidats");
%>
    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/sidebarAdmin.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/navbar.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="col-md-4 grid-margin grid-margin-md-0 stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                    <h4 class="card-title">List with icon</h4>
                                    <p class="card-description">Add class <code>.list-star</code> to <code>&lt;ul&gt;</code></p>
                                    <ul class="list-star">
                                        <li>Lorem ipsum dolor sit amet</li>
                                        <li>Consectetur adipiscing elit</li>
                                        <li>Integer molestie lorem at massa</li>
                                        <li>Facilisis in pretium nisl aliquet</li>
                                        <li>Nulla volutpat aliquam velit&gt;</li>
                                    </ul>
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
