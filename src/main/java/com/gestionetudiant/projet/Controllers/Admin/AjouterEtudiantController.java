package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterEtudiantController implements Initializable {
    public TextField nom_fld;
    public TextField prenom_fld;
    public TextField date_naissance_fld;
    public TextField specialite_fld;
    public TextField mot_pass_fld;
    public TextField id_utilisateur_fld;
    public Button ajouter_etudiant_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajouter_etudiant_btn.setOnAction(event -> {
            addStudent();
        });
    }
    private void addStudent() {
        String nom = nom_fld.getText();
        String prenom = prenom_fld.getText();
        String dateNaissance = date_naissance_fld.getText();
        String specialite = specialite_fld.getText();
        String motPass = mot_pass_fld.getText();
        String idUtilisateur = id_utilisateur_fld.getText();
        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || specialite.isEmpty() || motPass.isEmpty()) {
            return;
        }
        Model model = Model.getInstance();
        model.getdatabaseDriver().addStudent(nom, prenom, dateNaissance, specialite, motPass,idUtilisateur);
        showSuccessMessage();
    }
    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Étudiant ajouté avec succès!");
        alert.showAndWait();
    }
}
