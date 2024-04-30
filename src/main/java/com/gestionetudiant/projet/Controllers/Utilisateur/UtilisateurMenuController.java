package com.gestionetudiant.projet.Controllers.Utilisateur;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Views.UtilisateurMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UtilisateurMenuController implements Initializable {
    public Button tableauDeBord_btn;
    public Button classement_btn;
    public Button deconnecter_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners() {
        tableauDeBord_btn.setOnAction(event -> onTableauDeBord() );
        classement_btn.setOnAction(event -> onClassement() );
        deconnecter_btn.setOnAction(event -> {
            Model.getInstance().getViewFactory().showLoginWindow();
            onClose();
        });
    }
    private void onTableauDeBord() {
        Model.getInstance().getViewFactory().getUtilisateurSelectedMenuItem().set(UtilisateurMenuOptions.TABLEAUDEBORD);
    }
    private void onClassement() {
        Model.getInstance().getViewFactory().getUtilisateurSelectedMenuItem().set(UtilisateurMenuOptions.CLASSEMENT);
    }
    private void onClose() {
        // Get the reference to the stage and close it
        Stage stage = (Stage) deconnecter_btn.getScene().getWindow();
        stage.close();
    }
}
