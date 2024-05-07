package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterEtudiantController implements Initializable {
    @FXML
    private Button addBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField dateNaiss;
    @FXML
    private TableColumn<Utilisateur,String> dateNaissCol;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane form;
    @FXML
    private TextField id;
    @FXML
    private TableColumn<Utilisateur,String> idCol;
    @FXML
    private TextField mdp;
    @FXML
    private TableColumn<Utilisateur,String> mdpCol;
    @FXML
    private TableColumn<Utilisateur,String> moyenneCol;
    @FXML
    private TextField nom;
    @FXML
    private TableColumn<Utilisateur,String> nomCol;
    @FXML
    private TextField prenom;
    @FXML
    private TableColumn<Utilisateur,String> prenomCol;
    @FXML
    private TextField search;
    @FXML
    private TextField specialite;
    @FXML
    private TableColumn<Utilisateur,String> specialiteCol;
    @FXML
    private TableView<Utilisateur> tableEtudiant;
    @FXML
    private Button updateBtn;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public ObservableList<Utilisateur> addUtilisateurData() {
        ObservableList<Utilisateur> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Utilisateur";
        try {
            Connection connect = DriverManager.getConnection("jdbc:sqlite:gestionetudiant.db");
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Utilisateur utilisateurD;

            while (result.next()) {
                utilisateurD = new Utilisateur(result.getString("Nom"),
                        result.getString("Prenom"),
                        result.getString("DateNaiss"),
                        result.getString("Specialite"),
                        result.getString("MotPass"),
                        result.getString("IdUtilisateur"));
                listData.add(utilisateurD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<Utilisateur> addUtilisateurList;

    public void addUtilisateurShowListData() {
        addUtilisateurList = addUtilisateurData();

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissCol.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        mdpCol.setCellValueFactory(new PropertyValueFactory<>("motPass"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        tableEtudiant.setItems(addUtilisateurList);
    }

    public void addUtilisateurSelect() {
        Utilisateur utilisateurD = tableEtudiant.getSelectionModel().getSelectedItem();
        int num = tableEtudiant.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        nom.setText(utilisateurD.getNom());
        prenom.setText(utilisateurD.getPrenom());
        dateNaiss.setText(utilisateurD.getDateNaiss());
        specialite.setText(utilisateurD.getSpecialite());
        mdp.setText(utilisateurD.getMotPass());
        id.setText(utilisateurD.getIdUtilisateur());
    }

    public void addUtilisateurReset() {
        nom.setText("");
        prenom.setText("");
        id.setText("");
        mdp.setText("");
        dateNaiss.setText("");
        specialite.setText("");
    }

    public void addUtilistauerAdd() {
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String DateNaissance = dateNaiss.getText();
        String Specialite = specialite.getText();
        String MotPass = mdp.getText();
        String IdUtilisateur = id.getText();
        Alert alert;
        if (Nom.isEmpty() || Prenom.isEmpty() || DateNaissance.isEmpty() || Specialite.isEmpty() || MotPass.isEmpty() || IdUtilisateur.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs vides");
            alert.showAndWait();
            return;
        }
        Model model = Model.getInstance();
        model.getdatabaseDriver().addStudent(Nom, Prenom, DateNaissance, Specialite, MotPass,IdUtilisateur);
        showSuccessMessage();
        addUtilisateurShowListData();
        addUtilisateurReset();
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Étudiant ajouté avec succès!");
        alert.showAndWait();
    }

    public void updateUtilisateur() {
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String DateNaissance = dateNaiss.getText();
        String Specialite = specialite.getText();
        String MotPass = mdp.getText();
        String IdUtilisateur = id.getText();
        Alert alert;
        if (Nom.isEmpty() || Prenom.isEmpty() || DateNaissance.isEmpty() || Specialite.isEmpty() || MotPass.isEmpty() || IdUtilisateur.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs vides");
            alert.showAndWait();
            return;
        }

        Model model = Model.getInstance();
        model.getdatabaseDriver().updateStudent(Nom, Prenom, DateNaissance, Specialite,MotPass, IdUtilisateur);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Mise à jour réussie!");
        alert.showAndWait();
        addUtilisateurShowListData();
        addUtilisateurReset();
    }

    public void DeleteUtilisateur() {
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String DateNaissance = dateNaiss.getText();
        String Specialite = specialite.getText();
        String MotPass = mdp.getText();
        String IdUtilisateur = id.getText();
        Alert alert;
        if (Nom.isEmpty() || Prenom.isEmpty() || DateNaissance.isEmpty() || Specialite.isEmpty() || MotPass.isEmpty() || IdUtilisateur.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs vides");
            alert.showAndWait();
            return;
        }
        Model model = Model.getInstance();
        model.getdatabaseDriver().deleteStudent(IdUtilisateur);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("suppression réussie!");
        alert.showAndWait();


        addUtilisateurShowListData();
        addUtilisateurReset();
    }

    public void addUtilisateurSearch() {
        FilteredList<Utilisateur> filter = new FilteredList<>(addUtilisateurList, e -> true);
        search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateUtilisateurData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateUtilisateurData.getIdUtilisateur().contains(searchKey)) {
                    return true;
                } else if (predicateUtilisateurData.getNom().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUtilisateurData.getPrenom().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUtilisateurData.getSpecialite().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUtilisateurData.getDateNaiss().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUtilisateurData.getIdUtilisateur().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Utilisateur> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(tableEtudiant.comparatorProperty());
        tableEtudiant.setItems(sortList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUtilisateurShowListData();
        addUtilisateurSearch();
    }
}
