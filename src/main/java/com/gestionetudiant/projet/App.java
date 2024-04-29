package com.gestionetudiant.projet;

import com.gestionetudiant.projet.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class App extends Application {
    @Override
    public void start(Stage stage)  {
        readDataFromDatabase();
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    public static void readDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:gestionetudiant.db")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Utilisateur")) {
                    while (resultSet.next()) {
                        String data1 = resultSet.getString("Nom");
                        String data2 = resultSet.getString("Prenom");
                        String data3 = resultSet.getString("DateNaiss");
                        System.out.println("Data: " + data1 + ", " + data2+ ", " + data3);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}