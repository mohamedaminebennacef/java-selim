package com.gestionetudiant.projet;

import com.gestionetudiant.projet.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;


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
                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage();
                    document.addPage(page);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    float margin = 50;
                    float yStart = page.getMediaBox().getHeight() - margin;
                    float tableWidth = 1200; // Expanded width
                    float yPosition = yStart;
                    float rowHeight = 20;
                    float cellMargin = 5;
                    String[] headers = {"Nom", "Prenom", "Date", "Specialite", "Mdp", "ID", "Moyenne"};
                    int numberOfColumns = headers.length;
                    float[] columnWidths = {80, 80, 100, 80, 60, 100, 80}; // Adjusted column widths
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
                        yPosition -= rowHeight + 5; // Add some spacing between rows
                        if (yPosition < margin) {
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            yPosition = yStart;
                            drawTableHeader(contentStream, columnWidths, rowHeight, yPosition, margin, headers);
                        }
                    }
                    contentStream.close(); // Close the content stream after adding all users
                    document.save("Table.pdf");
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