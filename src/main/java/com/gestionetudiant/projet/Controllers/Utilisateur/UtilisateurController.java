package com.gestionetudiant.projet.Controllers.Utilisateur;

import com.gestionetudiant.projet.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class UtilisateurController implements Initializable {
    public BorderPane utilisateur_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUtilisateurSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                default -> utilisateur_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
