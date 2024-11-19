<%
    String role = (String)request.getAttribute("role");
%>
    <form action="login" method="post">
        <input type="hidden" name="role" value="<%= role %>">
        <label for="email">Email : </label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="dateFin">password :</label>
        <input type="password" id="dateFin" name="mdp" required><br><br>

        <input type="submit" value="Ajouter">
    </form>