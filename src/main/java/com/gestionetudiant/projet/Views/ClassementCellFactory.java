package com.gestionetudiant.projet.Views;

import com.gestionetudiant.projet.Controllers.Utilisateur.ClassementCellController;
import com.gestionetudiant.projet.Models.Classement;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClassementCellFactory extends ListCell<Classement> {
    @Override
    protected void updateItem(Classement classement, boolean empty) {
        super.updateItem(classement, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/Fxml/Utilisateur/ClassementCell.fxml")));
            ClassementCellController controller = new ClassementCellController(classement);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
