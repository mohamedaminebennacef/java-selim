package com.gestionetudiant.projet.Models;

import javafx.beans.property.*;

public class Utilisateur {
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty dateNaiss;
    private final StringProperty specialite;
    private final StringProperty motPass;
    private final StringProperty idUtilisateur;

    public Utilisateur(String nom, String prenom, String dateNaiss, String specialite, String motPass, String idUtilisateur) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.dateNaiss = new SimpleStringProperty(dateNaiss);
        this.motPass = new SimpleStringProperty(motPass);
        this.specialite = new SimpleStringProperty(specialite);
        this.idUtilisateur = new SimpleStringProperty(idUtilisateur);
    }

    public StringProperty nomProperty() {return this.nom;}
    public StringProperty prenomProperty() {return this.prenom;}
    public StringProperty dateNaissProperty() {return this.dateNaiss;}
    public StringProperty motPassProperty() {return this.motPass;}
    public StringProperty specialiteProperty() {return this.specialite;}
    public StringProperty idUtilisateurProperty() {return this.idUtilisateur;}
    public String getNom() {return nom.get();}

    public String getPrenom() {return prenom.get();}
    public String getDateNaiss() {return dateNaiss.get();}
    public String getSpecialite() {return specialite.get();}
    public String getMotPass() {return motPass.get();}
    public String getIdUtilisateur() {return idUtilisateur.get();}
}
