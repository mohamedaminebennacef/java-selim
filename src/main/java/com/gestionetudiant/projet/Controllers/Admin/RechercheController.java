package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RechercheController implements Initializable {
    public TextField idUtilisateur_fld;
    public Button search_btn;
    public ListView result_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void handleSearchButtonClicked(ActionEvent event) {
        String idUtilisateur = idUtilisateur_fld.getText();
        Utilisateur utilisateur = Model.getInstance().getUtilisateurById(idUtilisateur);
        if (utilisateur != null) {
            System.out.println(utilisateur.getIdUtilisateur());
            populateResultListView(utilisateur);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("échec");
            alert.setHeaderText(null);
            alert.setContentText("Étudiant non trouvé!");
            alert.showAndWait();
        }
    }

    private void populateResultListView(Utilisateur utilisateur) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ModifierEtudiantCell.fxml"));
        try {
            AnchorPane cell = loader.load();
            ModifierEtudiantCell controller = loader.getController();
            controller.setUtilisateur(utilisateur);
            result_listview.getItems().add(cell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
