module com.gestionetudiant.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome; // icons
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;


    opens com.gestionetudiant.projet to javafx.fxml;
    exports com.gestionetudiant.projet;
    exports com.gestionetudiant.projet.Controllers;
    exports com.gestionetudiant.projet.Controllers.Admin;
    exports com.gestionetudiant.projet.Controllers.Utilisateur;
    exports com.gestionetudiant.projet.Models;
    exports com.gestionetudiant.projet.Views;
}
