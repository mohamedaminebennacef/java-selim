package com.gestionetudiant.projet.Controllers.Admin;

import com.gestionetudiant.projet.Models.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ListeEtudiantsController implements Initializable {
    public Button exporter;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField dateNaiss;
    @FXML
    private TableColumn<Utilisateur,String> dateNaissCol;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane form;
    @FXML
    private TextField id;
    @FXML
    private TableColumn<Utilisateur,String> idCol;
    @FXML
    private TextField mdp;
    @FXML
    private TextField moyenne;
    @FXML
    private TableColumn<Utilisateur,String> mdpCol;
    @FXML
    private TableColumn<Utilisateur,String> moyenneCol;
    @FXML
    private TextField nom;
    @FXML
    private TableColumn<Utilisateur,String> nomCol;
    @FXML
    private TextField prenom;
    @FXML
    private TableColumn<Utilisateur,String> prenomCol;
    @FXML
    private TextField search;
    @FXML
    private TextField specialite;
    @FXML
    private TableColumn<Utilisateur,String> specialiteCol;
    @FXML
    private TableView<Utilisateur> tableEtudiant;
    @FXML
    private Button updateBtn;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    // creation de liste observable des utilisateurs
    public ObservableList<Utilisateur> addUtilisateurData() {
        ObservableList<Utilisateur> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Utilisateur";
        try {
            Connection connect = DriverManager.getConnection("jdbc:sqlite:gestionetudiant.db");
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Utilisateur utilisateurD;
            while (result.next()) {
                utilisateurD = new Utilisateur(result.getString("Nom"),
                        result.getString("Prenom"),
                        result.getString("DateNaiss"),
                        result.getString("Specialite"),
                        result.getString("MotPass"),
                        result.getString("IdUtilisateur"),
                        result.getDouble("Moyenne")
                );
                listData.add(utilisateurD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Utilisateur> addUtilisateurList;

    // afficher les données des utilisateurs dans la table.
    public void addUtilisateurShowListData() {
        // configuration de table javafx pour afficher les donnees pour chaque ligne
        addUtilisateurList = addUtilisateurData();
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissCol.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        mdpCol.setCellValueFactory(new PropertyValueFactory<>("motPass"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        moyenneCol.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
        tableEtudiant.setItems(addUtilisateurList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUtilisateurShowListData();
    }

    public void exporterPdf() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:gestionetudiant.db")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Utilisateur")) {
                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage();
                    document.addPage(page);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    float margin = 50;
                    float yStart = page.getMediaBox().getHeight() - margin;
                    float tableWidth = 1200;
                    float yPosition = yStart;
                    float rowHeight = 20;
                    float cellMargin = 5;
                    String[] headers = {"Nom", "Prenom", "Date", "Specialite", "Mdp", "ID", "Moyenne"};
                    int numberOfColumns = headers.length;
                    float[] columnWidths = {80, 80, 100, 80, 60, 100, 80};
                    drawTableHeader(contentStream, columnWidths, rowHeight, yPosition, margin, headers);
                    while (resultSet.next()) {
                        String data1 = resultSet.getString("Nom");
                        String data2 = resultSet.getString("Prenom");
                        String data3 = resultSet.getString("DateNaiss");
                        String data4 = resultSet.getString("Specialite");
                        String data5 = resultSet.getString("MotPass");
                        String data6 = resultSet.getString("IdUtilisateur");
                        Double data7 = resultSet.getDouble("Moyenne");
                        String[] rowData = {data1, data2, data3, data4, data5, data6, data7.toString()};
                        drawTableRow(contentStream, columnWidths, rowHeight, yPosition, margin, rowData);
                        yPosition -= rowHeight + 5;
                        if (yPosition < margin) {
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            yPosition = yStart;
                            drawTableHeader(contentStream, columnWidths, rowHeight, yPosition, margin, headers);
                        }
                    }
                    contentStream.close();
                    document.save("Données.pdf");
                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Exporté avec succès en pdf");
                    alert.showAndWait();
                    document.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    private static void drawTableHeader(PDPageContentStream contentStream, float[] columnWidths, float rowHeight, float yPosition, float margin, String[] headers) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        float nextX = margin;
        for (int i = 0; i < headers.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(nextX, yPosition);
            contentStream.showText(headers[i]);
            contentStream.endText();
            nextX += columnWidths[i];
        }
    }
    private static void drawTableRow(PDPageContentStream contentStream, float[] columnWidths, float rowHeight, float yPosition, float margin, String[] rowData) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        float nextY = yPosition - rowHeight;
        float nextX = margin;
        for (int i = 0; i < rowData.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(nextX, nextY);
            contentStream.showText(rowData[i]);
            contentStream.endText();
            nextX += columnWidths[i];
        }
    }
}