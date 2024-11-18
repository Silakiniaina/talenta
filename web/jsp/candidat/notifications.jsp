<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.NotificationCandidat" %>
<%
    List<NotificationCandidat> notifications = (List<NotificationCandidat>)request.getAttribute(("notifications"));
    for(NotificationCandidat notification : notifications){
%>
    <a href="notifCandidat?notification=<%= notification.getIdNotification() %>"> <%= notification.getContenuNotification() %></a>
<% 
    }
%>