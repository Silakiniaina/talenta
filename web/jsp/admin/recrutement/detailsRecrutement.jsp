<%@ page import="java.util.List" %>
<%@ page import="model.Candidat" %>
<%
    List<Candidat> candidats = (List<Candidat>)request.getAttribute("candidats");
%>
    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card" style="flex-direction:column; gap:10px;">
                            <div class="card">
                                <div class="card-body">
                                    <h2>Developpeur backend</h2>
                                    <div class="row">
                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Candidature recu</h5>
                                                    <div class="media">
                                                        <i class="ti-world icon-md text-info d-flex align-self-start mr-3"></i>
                                                        <div class="media-body">
                                                        <h3>34</h3>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Nombre de poste</h5>
                                                    <div class="media">
                                                        <i class="ti-world icon-md text-info d-flex align-self-start mr-3"></i>
                                                        <div class="media-body">
                                                        <h3>14</h3>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <p>
                                        <b>Date debut : </b> 12 Juillet 2005
                                    </p>
                                    <p>
                                        <b>Date fin : </b> 12 Juillet 2005
                                    </p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Description</h3>
                                    <p>
                                        Here goes the description
                                    </p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                <h3 class="card-title">Competence requises</h3>
                                <ul class="list-star">
                                    <li>Lorem ipsum dolor sit amet</li>
                                    <li>Consectetur adipiscing elit</li>
                                    <li>Integer molestie lorem at massa</li>
                                    <li>Facilisis in pretium nisl aliquet</li>
                                    <li>Nulla volutpat aliquam velit&gt;</li>
                                </ul>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                <h3 class="card-title">Education requises</h3>
                                <ul class="list-arrow">
                                    <li>Lorem ipsum dolor sit amet</li>
                                    <li>Consectetur adipiscing elit</li>
                                    <li>Integer molestie lorem at massa</li>
                                    <li>Facilisis in pretium nisl aliquet</li>
                                    <li>Nulla volutpat aliquam velit&gt;</li>
                                </ul>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                <h4 class="card-title">Experience requises</h4>
                                <ul class="list-ticked">
                                    <li>Lorem ipsum dolor sit amet</li>
                                    <li>Consectetur adipiscing elit</li>
                                    <li>Integer molestie lorem at massa</li>
                                    <li>Facilisis in pretium nisl aliquet</li>
                                    <li>Nulla volutpat aliquam velit&gt;</li>
                                </ul>
                                </div>
                            </div>
                            <a href="#" type="button" class="btn btn-success col-md-4">Postuler Maintenant</a>
                        </div>
                    </div>
                    <%@ include file="../../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../../shared/script.jsp" %>
    </body>
</html>
