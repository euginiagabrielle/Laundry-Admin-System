package com.example.percobaan2baru;

import entity.MetodePembayaran;
import entity.MetodePembayaranProperty;
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
import repository.MetodePembayaranRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPembayaranController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<MetodePembayaranProperty, String> columnID;

    @FXML
    private TableColumn<MetodePembayaranProperty, String> columnMetode;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputNamaMetode;

    @FXML
    private Label label;

    @FXML
    private TableView<MetodePembayaranProperty> tabelMetode;

    private ObservableList<MetodePembayaranProperty> metode = FXCollections.observableArrayList();
    private MetodePembayaranRepository metodeRepository = new MetodePembayaranRepository();
    public EditPembayaranController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(f -> f.getValue().idProperty());
        columnMetode.setCellValueFactory(f -> f.getValue().namaMetodeProperty());
        tabelMetode.setItems(metode);
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() throws SQLException {
        metode = FXCollections.observableArrayList();
        ArrayList<MetodePembayaran> result;
        try {
            result = metodeRepository.GetMetode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((m) -> {
            MetodePembayaranProperty mp = new MetodePembayaranProperty();
            mp.setId(String.valueOf(m.idMetode));
            mp.setNamaMetode(m.namaMetode);
            metode.add(mp);
        });
        tabelMetode.setItems(metode);
    }

    @FXML
    void onBtnAddClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputNamaMetode.getText();
        boolean isInserted = metodeRepository.InsertMetode(
                new MetodePembayaran(
                        id,
                        nama)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputNamaMetode.setText(null);
    }

    int clickedRow;
    @FXML
    void selectedRow() {
        clickedRow = tabelMetode.getSelectionModel().getSelectedIndex();
        String id = tabelMetode.getItems().get(clickedRow).getId();
        String nama = tabelMetode.getItems().get(clickedRow).getNamaMetode();
        inputID.setText(id);
        inputNamaMetode.setText(nama);
    }

    @FXML
    void onBtnDeleteClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputNamaMetode.getText();
        boolean isInserted = metodeRepository.DeleteMetode(clickedRow,
                new MetodePembayaran(
                        id,
                        nama)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputID.setText(null);
        inputNamaMetode.setText(null);
    }

    @FXML
    void onBtnUpdateClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputNamaMetode.getText();
        boolean isInserted = metodeRepository.UpdateMetode(clickedRow,
                new MetodePembayaran(
                        id,
                        nama)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data updated successfully");
        } else {
            label.setText("Failed to update data");
        }

        inputID.setText(null);
        inputNamaMetode.setText(null);
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
