<div class="card-body">
    <h4 class="card-title">Employee Leave Request</h4>

    <p class="card-description">
        Fill out the form to create a leave request.
    </p>

    <form class="forms-sample" action="/submitLeaveRequest" method="POST">
        <div class="form-group">
            <label for="idConge">Leave ID</label>
            <input type="number" class="form-control" id="idConge" name="idConge" placeholder="Enter Leave ID">
        </div>

        <div class="form-group">
            <label for="idEmploye">Employee ID</label>
            <input type="number" class="form-control" id="idEmploye" name="idEmploye" placeholder="Enter Employee ID">
        </div>

        <div class="form-group">
            <label for="idContrat">Contract ID</label>
            <input type="number" class="form-control" id="idContrat" name="idContrat" placeholder="Enter Contract ID">
        </div>

        <div class="form-group">
            <label for="idTypeConge">Leave Type ID</label>
            <input type="number" class="form-control" id="idTypeConge" name="idTypeConge"
                placeholder="Enter Leave Type ID">
        </div>

        <div class="form-group">
            <label for="dateDebut">Start Date</label>
            <input type="date" class="form-control" id="dateDebut" name="dateDebut">
        </div>

        <div class="form-group">
            <label for="dateFin">End Date</label>
            <input type="date" class="form-control" id="dateFin" name="dateFin">
        </div>

        <button type="submit" class="btn btn-primary mr-2">Submit</button>
        <button type="reset" class="btn btn-light">Cancel</button>
    </form>
</div>