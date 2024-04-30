package com.gestionetudiant.projet.Controllers;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public TextField id_fld;
    public TextField mot_pass_fld;
    public Button login_btn;
    public Label id_lbl;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.UTILISATEUR));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
        acc_selector.valueProperty().addListener((observable, oldValue, newValue) -> {
            Model.getInstance().getViewFactory().setLoginAccountType(newValue);
            if (newValue == AccountType.ADMIN) {id_lbl.setText("Id Admin :");}
            else {id_lbl.setText("Id Utilisateur :");}
        });
    }
    private void onLogin() {
        // authentification Utilisateur
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.UTILISATEUR) {
            Model.getInstance().evaluateUtilisateurCred(id_fld.getText(),mot_pass_fld.getText());
//            if (Model.getInstance().getUtilisateurLoginSuccessFlag()) {
            if (true) {
                Model.getInstance().getViewFactory().showUtilisateurWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                id_fld.setText("");
                mot_pass_fld.setText("");
                error_lbl.setText("authentification invalide.");
            }
        }
        // authentification Admin
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
            Model.getInstance().evaluateAdminCred(id_fld.getText(),mot_pass_fld.getText());
//            if (Model.getInstance().getAdminLoginSuccessFlag()) {
            if(true) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                id_fld.setText("");
                mot_pass_fld.setText("");
                error_lbl.setText("authentification invalide.");
            }
        }
    }
}
