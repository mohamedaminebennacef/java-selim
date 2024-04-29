package com.gestionetudiant.projet.Controllers.Utilisateur;

import com.gestionetudiant.projet.Models.DatabaseDriver;
import com.gestionetudiant.projet.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ListView classement_listview;
    public TextField java_fld;
    public TextField poo_fld;
    public TextField bd_fld;
    public Button envoyer_btn;
    public TextField web_fld;
    public TextField idUtilisateur_fld;
    public TextField francais_fld;
    public TextField Droits_fld;
    public TextField anglais_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        envoyer_btn.setOnAction(event -> {
            calculateAndShowAverage();
        });
    }

    private void calculateAndShowAverage() {
        try {
            double java = Double.parseDouble(java_fld.getText());
            double poo = Double.parseDouble(poo_fld.getText());
            double bd = Double.parseDouble(bd_fld.getText());
            double web = Double.parseDouble(web_fld.getText());
            double francais = Double.parseDouble(francais_fld.getText());
            double Droits = Double.parseDouble(Droits_fld.getText());
            double anglais = Double.parseDouble(anglais_fld.getText());

            double moyenne = (java + poo + bd + web + francais + Droits + anglais) / 7;
            String formattedMoyenne = String.format("%.2f", moyenne);

            String idUtilisateur = idUtilisateur_fld.getText();

            Model model = Model.getInstance();
            System.out.println(idUtilisateur);
            model.getdatabaseDriver().updateMoyenne(idUtilisateur,moyenne);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Moyenne");
            alert.setHeaderText(null);
            alert.setContentText("La moyenne est : " + formattedMoyenne);
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des nombres valides.");
            alert.showAndWait();
        }
    }
}
