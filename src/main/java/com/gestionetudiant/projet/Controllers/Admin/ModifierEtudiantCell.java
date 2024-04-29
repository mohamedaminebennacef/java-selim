package com.gestionetudiant.projet.Controllers.Admin;


import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierEtudiantCell implements Initializable {
    public Label fullName_lbl;
    public Label specialite_lbl;
    public Label moyenne_lbl;
    public Button modifier_btn;
    private Utilisateur utilisateur;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifier_btn.setOnAction(event -> openModifierWindow());
    }
    public ModifierEtudiantCell(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public ModifierEtudiantCell() {}
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        fullName_lbl.setText(utilisateur.nomProperty().get() + " " + utilisateur.prenomProperty().get());
        specialite_lbl.setText(utilisateur.specialiteProperty().get());
        moyenne_lbl.setText(utilisateur.dateNaissProperty().get());
    }
    public void openModifierWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ModifierWindow.fxml"));
            Parent root = loader.load();
            ModifierWindowController controller = loader.getController();
            //controller.initData(utilisateur); // Pass utilisateur to ModifierWindowController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
