<%@ page import="java.util.List" %>
<%@ page import="model.Conge" %>
<%
    List<Conge> planningConges = (List<Conge>) request.getAttribute("planningConges");
%>
<%@ include file="../../shared/head.jsp" %>
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<body>
    <div class="container-scroller">
        <%@ include file="../../shared/navbar.jsp" %>
        <div class="container-fluid page-body-wrapper">
            <%@ include file="../../shared/sidebarAdmin.jsp" %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h2>Planning des Conges</h2>
                                    <div id="calendar"></div> 
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

    <script>
        $(document).ready(function () {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'fr', // Pour afficher le calendrier en français
                events: [
                    <%
                        for (Conge conge : planningConges) {
                            String startDate = conge.getDateDebut().toString(); 
                            String endDate = conge.getDateFin().toString();
                    %>
                    {
                        title: '<%= conge.getEmploye().getCandidat().getNomCandidat() %> - <%= conge.getTypeConge().getNomType() %>',
                        start: '<%= startDate %>',
                        end: '<%= endDate %>',
                        allDay: true
                    },
                    <% } %>
                ]
            });
            calendar.render();
        });
    </script>

</body>
</html>
