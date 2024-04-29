package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ListeEtudiantsController implements Initializable {
    public ListView<Utilisateur> etudiants_listview;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        ResultSet resultSet = Model.getInstance().getAllStudents();
        try {
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getString("Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getString("Moyenne"),
                        resultSet.getString("Specialite"),
                        resultSet.getString("MotPass"),
                        resultSet.getString("IdUtilisateur")
                );
                utilisateurs.add(utilisateur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        etudiants_listview.setItems(utilisateurs);
        etudiants_listview.setCellFactory(param -> new EtudiantsCellController());
    }
}