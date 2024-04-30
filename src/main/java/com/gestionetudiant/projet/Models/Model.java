package com.gestionetudiant.projet.Models;

import com.gestionetudiant.projet.Views.AccountType;
import com.gestionetudiant.projet.Views.ViewFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.UTILISATEUR;

    // utilisateur data section
    private final Utilisateur utilisateur;
    private boolean utilisateurLoginSuccessFlag;

    // Admin data section
    private boolean adminLoginSuccessFlag;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // utilisateur data section
        this.utilisateurLoginSuccessFlag = false;
        this.utilisateur = new Utilisateur("","","","","","");
        // Admin data section
        this.adminLoginSuccessFlag = false;
    }
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    public ViewFactory getViewFactory() {return viewFactory;}
    public DatabaseDriver getdatabaseDriver() {return databaseDriver;}
    public AccountType getLoginAccountType() {return loginAccountType;}
    public void setLoginAccountType(AccountType loginAccountType) {this.loginAccountType = loginAccountType;}

    // utilisateur method section
    public boolean getUtilisateurLoginSuccessFlag() {return this.utilisateurLoginSuccessFlag;}
    public void setUtilisateurLoginSuccessFlag(boolean flag) {this.utilisateurLoginSuccessFlag = flag;}
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void evaluateUtilisateurCred(String idUtilisateur,String motPass) {
        ResultSet resultSet = databaseDriver.getUtilisateurData(idUtilisateur,motPass);
        try {
            if (resultSet.isBeforeFirst()) {
                this.utilisateur.nomProperty().set(resultSet.getString("Nom"));
                this.utilisateur.prenomProperty().set(resultSet.getString("Prenom"));
                this.utilisateur.idUtilisateurProperty().set(resultSet.getString("IdUtilisateur"));
                this.utilisateur.dateNaissProperty().set(resultSet.getString("DateNaiss"));
                this.utilisateurLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllStudents() {return databaseDriver.getAllStudents();}
    public ResultSet getAllSortedStudents() {return databaseDriver.getAllSortedStudents();}
    public Utilisateur getUtilisateurById(String idUtilisateur) {
        ResultSet resultSet = databaseDriver.getUtilisateurById(idUtilisateur);
        try {
            if (resultSet.isBeforeFirst()) {
                return new Utilisateur(
                        resultSet.getString("Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getString("DateNaiss"),
                        resultSet.getString("Specialite"),
                        resultSet.getString("MotPass"),
                        resultSet.getString("IdUtilisateur")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // admin method section
    public boolean getAdminLoginSuccessFlag() {return this.adminLoginSuccessFlag;}
    public void setAdminLoginSuccessFlag(boolean flag) {this.adminLoginSuccessFlag = flag;}
    public void evaluateAdminCred(String idAdmin,String motPass) {
        ResultSet resultSet = databaseDriver.getAdminData(idAdmin,motPass);
        try {
            if (resultSet.isBeforeFirst()) {
                this.adminLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
