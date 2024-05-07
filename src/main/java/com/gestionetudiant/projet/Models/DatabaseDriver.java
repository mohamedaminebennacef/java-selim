package com.gestionetudiant.projet.Models;

import java.sql.*;

public class DatabaseDriver {
    private Connection conn;
    // établissement du connexion à la base du données,
    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:gestionetudiant.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
        Session Utilisateur
     */
    public ResultSet getUtilisateurData(String idUtilisateur,String motPass) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("select * from Utilisateur where IdUtilisateur = '"+idUtilisateur+"'and MotPass='"+motPass+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void addStudent(String nom, String prenom, String dateNaissance, String specialite, String motPass,String idUtilisateur) {
        String query = "INSERT INTO Utilisateur (Nom, Prenom, DateNaiss, Specialite, MotPass,IdUtilisateur,Moyenne) VALUES (?, ?, ?, ?, ?,?,0)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, dateNaissance);
            pstmt.setString(4, specialite);
            pstmt.setString(5, motPass);
            pstmt.setString(6, idUtilisateur);
            System.out.println(idUtilisateur);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStudent(String nom, String prenom, String dateNaissance, String specialite, String motPass, String idUtilisateur) {
        String query = "UPDATE Utilisateur SET Nom=?, Prenom=?, DateNaiss=?, Specialite=?, MotPass=? WHERE IdUtilisateur=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, dateNaissance);
            pstmt.setString(4, specialite);
            pstmt.setString(5, motPass);
            pstmt.setString(6, idUtilisateur);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMoyenne(String idUtilisateur, double moyenne) {
        String query = "UPDATE Utilisateur SET Moyenne = ? WHERE IdUtilisateur = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, moyenne);
            pstmt.setString(2, idUtilisateur);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(String idUtilisateur) {
        String query = "DELETE FROM Utilisateur WHERE IdUtilisateur = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idUtilisateur);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
        Session Admin
     */
    public ResultSet getAdminData(String idAdmin,String motPass) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("select * from Admin where IdAdmin = '"+idAdmin+"'and MotPass='"+motPass+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
