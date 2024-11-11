
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700;900&display=swap');

*, body {
    font-family: 'Poppins', sans-serif;
    font-weight: 400;
    -webkit-font-smoothing: antialiased;
}

html, body {
    height: 100%;
    background-color: #152733;
    overflow: hidden;
}


.form-holder {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
      min-height: 100vh;
}

.form-content .form-items {
    border: 3px solid #fff;
    padding: 40px;
    display: inline-block;
    width: 100%;
    min-width: 540px;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    border-radius: 10px;
    text-align: left;
    -webkit-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

.form-content h3 {
    color: #fff;
    text-align: left;
    font-size: 28px;
    font-weight: 600;
    margin-bottom: 5px;
}

.form-content h3.form-title {
    margin-bottom: 30px;
}

.form-content p {
    color: #fff;
    text-align: left;
    font-size: 17px;
    font-weight: 300;
    line-height: 20px;
    margin-bottom: 30px;
}


.form-content label, .was-validated .form-check-input:invalid~.form-check-label, .was-validated .form-check-input:valid~.form-check-label{
    color: #fff;
}

.form-content input[type=text], .form-content input[type=password], .form-content input[type=email], .form-content input[type=date], .form-content input[type=textarea], .form-content select, .form-content input[type=number]{
    width: 100%;
    padding: 9px 20px;
    text-align: left;
    border: 0;
    outline: 0;
    border-radius: 6px;
    background-color: #fff;
    font-size: 15px;
    font-weight: 300;
    color: #8D8D8D;
    -webkit-transition: all 0.3s ease;
    transition: all 0.3s ease;
    margin-top: 16px;
}


.btn-primary{
    width: 100%; 
    height: 45px;
    background-color: #6C757D;
    outline: none;
    border: 0px;
     box-shadow: none;
     border-radius: 6px;
     margin-top: 15px;
}

</style>
<%@page import="model.Poste" %>
<%@page import="java.util.List" %>
<%
    List<Poste> listePoste = (List<Poste>)request.getAttribute("postes");
%>

<div class="form-body">
    <div class="row">
        <div class="form-holder">
            <div class="form-content">
                <div class="form-items">
                    <h3>Informations personnelles</h3>
                    <form method="post" action="ajout-cv">

                        <div class="col-md-12">
                           <input class="form-control" type="text" name="nom" placeholder="Nom" required>
                        </div>

                        <div class="col-md-12">
                            <input class="form-control" type="text" name="prenoms" placeholder="Prenoms" required>
                        </div>

                        <div class="col-md-12">
                            <input class="form-control" type="date" name="dtn" placeholder="Date de naissance" required>
                        </div>

                        <div class="col-md-12">
                            <input class="form-control" type="text" name="adress" placeholder="Adresse" required>
                        </div>

                        <div class="col-md-12">
                          <input class="form-control" type="number" name="xp" placeholder="Experience" required>
                        </div>

                       <div class="col-md-12 mt-3">
                            <label class="mb-3 mr-1" for="gender" style="margin-top: 16px;">Gender: </label>
                            <input type="radio" class="btn-check" name="gender" id="male" value="homme" autocomplete="off" required>
                            <label class="btn btn-sm btn-outline-secondary" for="male">Homme</label>

                            <input type="radio" class="btn-check" name="gender" id="female" value="femme" autocomplete="off" required>
                            <label class="btn btn-sm btn-outline-secondary" for="female">Femme</label>
                        </div>
              
                        <div class="select-container">
                            <select id="poste-select" class="form-select" name="idPoste">
                                <% for (Poste p : listePoste) { %>
                                    <option value="<%= p.getId() %>"><%= p.getNom() %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="form-button mt-3">
                            <button id="submit" type="submit" class="btn btn-primary">Inserer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>  
