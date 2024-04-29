package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantCellController implements Initializable {
    public Label fullName_lbl;
    public Label specialite_lbl;
    public Label moyenne_lbl;
    public Button delete_btn;
    private Utilisateur utilisateur;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delete_btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Supprimer l'étudiant");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet étudiant ?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    deleteStudent();
                }
            });
        });
    }
    public EtudiantCellController(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public EtudiantCellController() {}
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        fullName_lbl.setText(utilisateur.nomProperty().get() + " " + utilisateur.prenomProperty().get());
        specialite_lbl.setText(utilisateur.specialiteProperty().get());
        moyenne_lbl.setText(utilisateur.dateNaissProperty().get());
    }
    private void deleteStudent() {
        Model.getInstance().getdatabaseDriver().deleteStudent(utilisateur.getIdUtilisateur());
        ListView<Utilisateur> listView = (ListView<Utilisateur>) fullName_lbl.getScene().lookup("#etudiants_listview");
        listView.getItems().remove(utilisateur);
    }
}
