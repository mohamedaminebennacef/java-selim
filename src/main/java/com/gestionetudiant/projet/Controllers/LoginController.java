package com.gestionetudiant.projet.Controllers;

import com.gestionetudiant.projet.Models.Model;
import com.gestionetudiant.projet.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public TextField id_utilisateur_fld;
    public TextField mot_pass_fld;
    public Button login_btn;
    public Label error_lbl;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.UTILISATEUR));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }
    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.UTILISATEUR) {
            // Evalute Utilisateur login credentials
            Model.getInstance().evaluateUtilisateurCred(id_utilisateur_fld.getText(),mot_pass_fld.getText());
            System.out.println(id_utilisateur_fld.getText());
            if (Model.getInstance().getUtilisateurLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showUtilisateurWindow();
                // close the login stage
                //Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                //mot_pass_fld.setText("");
                error_lbl.setText("authentification invalide.");
            }
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
        }

    }
}
