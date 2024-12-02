<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.NotificationAdmin" %>
<%
    List<NotificationAdmin> notifications = (List<NotificationAdmin>)request.getAttribute(("notifications"));
    for(NotificationAdmin notification : notifications){
%>
    <a href="notifAdmin?notification=<%= notification.getIdNotification() %>"> <%= notification.getContenuNotification() %></a>
<% 
    }
%>