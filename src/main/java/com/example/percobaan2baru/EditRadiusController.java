package com.example.percobaan2baru;

import entity.Radius;
import entity.RadiusProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.RadiusRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditRadiusController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<RadiusProperty, String> columnHarga;

    @FXML
    private TableColumn<RadiusProperty, String> columnID;

    @FXML
    private TableColumn<RadiusProperty, String> columnMaxRadius;

    @FXML
    private TableColumn<RadiusProperty, String> columnMinRadius;

    @FXML
    private TextField inputHarga;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputMaxRadius;

    @FXML
    private TextField inputMinRadius;

    @FXML
    private Label label;

    @FXML
    private TableView<RadiusProperty> tabelRadius;

    private ObservableList<RadiusProperty> radius = FXCollections.observableArrayList();
    private RadiusRepository radiusRepository = new RadiusRepository();
    public EditRadiusController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(f -> f.getValue().idRadiusProperty());
        columnMinRadius.setCellValueFactory(f -> f.getValue().minRadiusProperty());
        columnMaxRadius.setCellValueFactory(f -> f.getValue().maxRadiusProperty());
        columnHarga.setCellValueFactory(f -> f.getValue().hargaProperty());
        tabelRadius.setItems(radius);
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() throws SQLException {
        radius = FXCollections.observableArrayList();
        ArrayList<Radius> result;
        try {
            result = radiusRepository.GetRadius();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((r) -> {
            RadiusProperty rp = new RadiusProperty();
            rp.setIdRadius(String.valueOf(r.idRadius));
            rp.setMinRadius(String.valueOf(r.minRadius));
            rp.setMaxRadius(String.valueOf(r.maxRadius));
            rp.setHarga(String.valueOf(r.harga));
            radius.add(rp);
        });
        tabelRadius.setItems(radius);
    }

    @FXML
    void onBtnAddClick(ActionEvent event) throws SQLException {
        int idRadius = Integer.parseInt(inputID.getText());
        int minRadius = Integer.parseInt(inputMinRadius.getText());
        int maxRadius = 0;
        if ((inputMaxRadius.getText() != "")){
            maxRadius = Integer.parseInt(inputMaxRadius.getText());
        } else if ((inputMaxRadius.getText().isEmpty())) {
            maxRadius = 0;
        }
        int harga = Integer.parseInt(inputHarga.getText());
        boolean isInserted = radiusRepository.InsertRadius(
                new Radius(
                        idRadius,
                        minRadius,
                        maxRadius,
                        harga)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputMinRadius.setText(null);
        inputMaxRadius.setText(null);
        inputHarga.setText(null);
    }

    int clickedRow;
    @FXML
    void selectedRow() {
        clickedRow = tabelRadius.getSelectionModel().getSelectedIndex();
        String idRad = tabelRadius.getItems().get(clickedRow).getIdRadius();
        String minRad = tabelRadius.getItems().get(clickedRow).getMinRadius();
        String maxRad = tabelRadius.getItems().get(clickedRow).getMaxRadius();
        String harga = tabelRadius.getItems().get(clickedRow).getHarga();
        inputID.setText(idRad);
        inputMinRadius.setText(minRad);
        inputMaxRadius.setText(maxRad);
        inputHarga.setText(harga);
    }

    @FXML
    void onBtnUpdateClick(ActionEvent event) throws SQLException {
        int idRadius = Integer.parseInt(inputID.getText());
        int minRadius = Integer.parseInt(inputMinRadius.getText());
        int maxRadius = 0;
        if ((inputMaxRadius.getText() != "")){
            maxRadius = Integer.parseInt(inputMaxRadius.getText());
        } else if ((inputMaxRadius.getText().isEmpty())) {
            maxRadius = 0;
        }
        int harga = Integer.parseInt(inputHarga.getText());
        boolean isInserted = radiusRepository.UpdateRadius(clickedRow,
                new Radius(
                        idRadius,
                        minRadius,
                        maxRadius,
                        harga)
        );

            updateTable();

            if (isInserted) {
                label.setText("Data updated successfully");
            } else {
                label.setText("Failed to update data");
            }

            inputID.setText(null);
            inputMinRadius.setText(null);
            inputMaxRadius.setText(null);
            inputHarga.setText(null);
    }

    @FXML
    void onBtnDeleteClick(ActionEvent event) throws SQLException {
        int idRadius = Integer.parseInt(inputID.getText());
        int minRadius = Integer.parseInt(inputMinRadius.getText());
        int maxRadius = Integer.parseInt(inputMaxRadius.getText());
        int harga = Integer.parseInt(inputHarga.getText());
        boolean isInserted = radiusRepository.DeleteRadius(clickedRow,
                new Radius(
                        idRadius,
                        minRadius,
                        maxRadius,
                        harga)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputID.setText(null);
        inputMinRadius.setText(null);
        inputMaxRadius.setText(null);
        inputHarga.setText(null);
    }

    @FXML
    void onBtnBackClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-menu.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}
