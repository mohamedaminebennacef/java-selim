package com.gestionetudiant.projet.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Classement {
    private final StringProperty idUtilisateur;
    private final StringProperty fullName;
    private final StringProperty specialite;
    private final DoubleProperty moyenne;

    public Classement(String idUtilisateur,String fullName,String specialite,double moyenne) {
        this.idUtilisateur = new SimpleStringProperty(this,"idUtilisateur",idUtilisateur);
        this.fullName = new SimpleStringProperty(this,"fullName",fullName);
        this.specialite = new SimpleStringProperty(this,"specialite",specialite);
        this.moyenne = new SimpleDoubleProperty(this,"moyenne",moyenne);
    }
    public StringProperty idUtilisateurProperty() {return this.idUtilisateur;}
    public StringProperty fullNameProperty() {return this.fullName;}
    public StringProperty specialiteProperty() {return this.specialite;}
    public DoubleProperty moyenneProperty() {return this.moyenne;}
}
