package com.gestionetudiant.projet.Controllers.Utilisateur;

import com.gestionetudiant.projet.Models.Classement;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassementCellController implements Initializable {

    public Label fullName_lbl;
    public Label specialite_lbl;
    public Label moyenne_lbl;
    public Label idUtilisateur_lbl;

    private final Classement classement;
    public ClassementCellController(Classement classement) {
        this.classement = classement;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
