package com.gestionetudiant.projet.Models;

import com.gestionetudiant.projet.Views.AccountType;
import com.gestionetudiant.projet.Views.ViewFactory;
import java.sql.ResultSet;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.UTILISATEUR;

    /*
        section données utilisateur
     */
    private final Utilisateur utilisateur;
    private boolean utilisateurLoginSuccessFlag;

    /*
        section données Admin
     */
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

    /*
        section méthode utilisateur
     */

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

    /*
        section méthode utilisateur
     */
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
