package com.gestionetudiant.projet.Views;

import com.gestionetudiant.projet.Controllers.Admin.EtudiantCellController;
import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class EtudiantsCellFactory extends ListCell<Utilisateur> {
    @Override
    protected void updateItem(Utilisateur utilisateur, boolean empty) {
        super.updateItem(utilisateur, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/Fxml/Admin/EtudiantsCell.fxml")));
            EtudiantCellController controller = new EtudiantCellController(utilisateur);
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
