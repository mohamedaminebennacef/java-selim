package com.gestionetudiant.projet.Controllers.Utilisateur;

import com.gestionetudiant.projet.Controllers.getData;
import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Views.UtilisateurMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UtilisateurMenuController implements Initializable {
    public Button tableauDeBord_btn;
    public Button deconnecter_btn;
    @FXML
    public Label UtilisateurNomPrenom;
    public void displayUsername() {UtilisateurNomPrenom.setText(getData.nom+" "+getData.prenom);}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners() {
        displayUsername();
        tableauDeBord_btn.setOnAction(event -> onTableauDeBord());
        deconnecter_btn.setOnAction(event -> {
            Model.getInstance().getViewFactory().showLoginWindow();
            onClose();
        });
    }
    private void onTableauDeBord() {
        Model.getInstance().getViewFactory().getUtilisateurSelectedMenuItem().set(UtilisateurMenuOptions.TABLEAUDEBORD);
    }
    private void onClose() {
        Stage stage = (Stage) deconnecter_btn.getScene().getWindow();
        stage.close();
    }
}
