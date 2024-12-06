<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.NotificationCandidat" %>
<%
    Candidat candidat = (Candidat)request.getSession().getAttribute("candidat");
    List<NotificationCandidat> notifications = NotificationCandidat.getAllNotVueByCandidat(candidat.getIdCandidat());
%>
   
<li class="nav-item dropdown">
    <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
        <i class="ti-bell mx-0"></i>
        <span class="count"></span>
    </a>
    <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
        <p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>

    <%
        for(NotificationCandidat notification : notifications){
    %>
        <a class="dropdown-item preview-item" href="notifCandidat?notification=<%= notification.getIdNotification() %>">
            <div class="preview-thumbnail">
                <div class="preview-icon bg-warning">
                <i class="ti-info mx-0"></i>
                </div>
            </div>
            <div class="preview-item-content">
                <h6 class="preview-subject font-weight-normal"><%= notification.getContenuNotification() %></h6>
                <p class="font-weight-light small-text mb-0 text-muted">
                </p>
            </div>
        </a>
    <% 
        }
    %>
    </div>
</li>