package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case ETUDIANTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getListeEtudiantsView());
                case RECHERCHE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getRechercheView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAjouterEtudiantView());
            }

        });
    }
}
