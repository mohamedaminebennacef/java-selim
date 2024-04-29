package com.gestionetudiant.projet.Controllers.Admin;


import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class EtudiantsCellController extends ListCell<Utilisateur> {

    @Override
    protected void updateItem(Utilisateur utilisateur, boolean empty) {
        super.updateItem(utilisateur, empty);
        if (empty || utilisateur == null) {
            setText(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/EtudiantsCell.fxml"));
            try {
                AnchorPane anchorPane = loader.load();
                EtudiantCellController controller = loader.getController();
                controller.setUtilisateur(utilisateur);
                setGraphic(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize() {}

}