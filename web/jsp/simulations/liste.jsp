<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Simulation" %>
<%@ page import="java.util.List" %>

<%@ include file="../shared/header.jsp" %>
    <h1>Liste des Simulations Disponibles</h1>
    
    <div class="simulations-list">
        <%
        List<Simulation> simulations = (List<Simulation>) request.getAttribute("simulations");
        if(simulations != null) {
            for(Simulation simulation : simulations) {
        %>
            <a href=detailsSimulation?idSimulation=<%= simulation.getIdSimulation() %>>
                <div class="simulation-card">
                    <h3><%=simulation.getTitre()%></h3>
                    <p><%=simulation.getDescription()%></p>
                    <%-- <a href="<%=request.getContextPath()%>/passer-simulation?id=<%=simulation.getIdSimulation()%>" 
                    class="btn btn-primary">
                        Commencer la simulation
                    </a> --%>
                </div>
            </a>
        <%
            }
        }
        %>
    </div>
</body>
</html>