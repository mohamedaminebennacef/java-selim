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
    public TextField prog_fld;
    public TextField analyse_fld;
    public TextField archi_fld;
    public TextField poo_fld;
    public TextField sys_fld;
    public TextField java_fld;
    public TextField reseaux_fld;
    public TextField web_fld;
    public TextField anglais_fld;
    public TextField francais_fld;
    public TextField droits_fld;
    public TextField conception_fld;
    public TextField id_utilisateur_fld;
    public Button envoyer_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        envoyer_btn.setOnAction(event -> {
            calculateAndShowAverage();
        });
    }

    private void calculateAndShowAverage() {
        try {
            double prog = Double.parseDouble(prog_fld.getText());
            double analyse = Double.parseDouble(analyse_fld.getText());
            double archi = Double.parseDouble(archi_fld.getText());
            double poo = Double.parseDouble(poo_fld.getText());
            double sys = Double.parseDouble(sys_fld.getText());
            double java = Double.parseDouble(java_fld.getText());
            double reseaux = Double.parseDouble(reseaux_fld.getText());
            double web = Double.parseDouble(web_fld.getText());
            double anglais = Double.parseDouble(anglais_fld.getText());
            double francais = Double.parseDouble(francais_fld.getText());
            double droits = Double.parseDouble(droits_fld.getText());
            double conception = Double.parseDouble(conception_fld.getText());


            double moyenne = (prog+analyse+archi+poo+sys+java+reseaux+web+anglais+francais+droits+conception) / 12;
            String formattedMoyenne = String.format("%.2f", moyenne);

            String idUtilisateur = id_utilisateur_fld.getText();

            Model model = Model.getInstance();
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
