package classification_personnelle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificationPersonnelle {

    private String firstName;
    private String lastName;
    private String categorie;

    /* ------------------------------ Constructors ------------------------------ */
    public ClassificationPersonnelle() {
    }

    public ClassificationPersonnelle(String firstName, String lastName, String categorie) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCategorie(categorie);
    }

    /* --------------------------- Getters and setters -------------------------- */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /* --------------------------------- Methods -------------------------------- */
    public List<ClassificationPersonnelle> getAll(Connection conn) throws SQLException {
        List<ClassificationPersonnelle> data = new ArrayList<>();
        String query = "SELECT * FROM v_classification_personelle";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClassificationPersonnelle cp = new ClassificationPersonnelle();
                cp.setFirstName(resultSet.getString("prenom"));
                cp.setLastName(resultSet.getString("nom"));
                cp.setCategorie("categorie");

                data.add(cp);
            }
        }

        return data;
    }

    public List<ClassificationPersonnelle> getByCategorie(int categorieId, Connection conn) throws SQLException {
        List<ClassificationPersonnelle> data = new ArrayList<>();
        String query = "SELECT * FROM v_classification_personnelle WHERE id_categorie = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, categorieId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClassificationPersonnelle cp = new ClassificationPersonnelle();
                cp.setFirstName(resultSet.getString("prenom"));
                cp.setLastName(resultSet.getString("nom"));
                cp.setCategorie("categorie");

                data.add(cp);
            }
        }

        return data;
    }

}
