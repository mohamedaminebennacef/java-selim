package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button ajouter_etudiant_btn;
    public Button liste_etudiants_btn;
    public Button recherche_etudiant_btn;
    public Button deconnecter_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners(){
        ajouter_etudiant_btn.setOnAction(event -> onAjouterEtudiant());
        liste_etudiants_btn.setOnAction(event -> onEtudiants());
        recherche_etudiant_btn.setOnAction(event -> onRecherche());
        deconnecter_btn.setOnAction(event -> {
            Model.getInstance().getViewFactory().showLoginWindow();
            onClose();
        });
    }
    private void onAjouterEtudiant() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.AJOUTERETUDIANT);
    }
    private void onEtudiants() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ETUDIANTS);
    }
    private void onRecherche() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.RECHERCHE);
    }
    private void onClose() {
        Stage stage = (Stage) deconnecter_btn.getScene().getWindow();
        stage.close();
    }
}
