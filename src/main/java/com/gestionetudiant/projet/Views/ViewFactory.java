package com.gestionetudiant.projet.Views;

import com.gestionetudiant.projet.Controllers.Admin.AdminController;
import com.gestionetudiant.projet.Controllers.Utilisateur.UtilisateurController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;
    // Utilisateur Views
    private final ObjectProperty<UtilisateurMenuOptions> utilisateurSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane listeEtudiantsView;
    private AnchorPane ClassementView;

    // Admin Views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane ajouterEtudiantView;
    private AnchorPane utilisateurView;
    private AnchorPane rechercheView;

    public ViewFactory() {
        this.loginAccountType = AccountType.UTILISATEUR;
        this.utilisateurSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
     * Utilisateur Views Section
     * */
    public ObjectProperty<UtilisateurMenuOptions> getUtilisateurSelectedMenuItem() {
        return utilisateurSelectedMenuItem;
    }
    public AnchorPane getDashboardView() {
        if (dashboardView == null ) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Utilisateur/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    public AnchorPane getListeEtudiantsView() {
        if (listeEtudiantsView == null) {
            try {
                listeEtudiantsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/ListeEtudiants.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listeEtudiantsView;
    }
    public AnchorPane getClassementView() {
        if (ClassementView == null) {
            try {
                listeEtudiantsView = new FXMLLoader(getClass().getResource("/Fxml/Utilisateur/Classement.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listeEtudiantsView;
    }
    public void showUtilisateurWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Utilisateur/Utilisateur.fxml"));
        UtilisateurController utilisateurController = new UtilisateurController();
        loader.setController(utilisateurController);
        createStage(loader);
    }
    /*
     * Admin Views Section
     * */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }
    public AnchorPane getAjouterEtudiantView() {
        if (ajouterEtudiantView == null) {
            try {
                ajouterEtudiantView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AjouterEtudiant.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ajouterEtudiantView;
    }
    public AnchorPane getUtilisateurView() {
        if (utilisateurView == null) {

            try {
                utilisateurView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AjouterEtudiant.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return utilisateurView;
    }
    public AnchorPane getRechercheView() {
        if (rechercheView == null) {
            try {
                rechercheView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Recherche.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rechercheView;
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Gestion des étudiants");
        stage.show();
    }
    public void closeStage(Stage stage) {
        stage.close();
    }
}