package com.example.percobaan2baru;

import entity.Kategori;
import entity.KategoriProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.KategoriRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditKategoriController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<KategoriProperty, String> columnID;

    @FXML
    private TableColumn<KategoriProperty, String> columnKategori;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputKategori;

    @FXML
    private Label label;

    @FXML
    private TableView<KategoriProperty> tabelKategori;

    private ObservableList<KategoriProperty> kategori = FXCollections.observableArrayList();
    private KategoriRepository kategoriRepository = new KategoriRepository();
    public EditKategoriController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(f -> f.getValue().idKategoriProperty());
        columnKategori.setCellValueFactory(f -> f.getValue().namaKategoriProperty());
        tabelKategori.setItems(kategori);
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() throws SQLException {
        kategori = FXCollections.observableArrayList();
        ArrayList<Kategori> result;
        try {
            result = kategoriRepository.GetKategori();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((k) -> {
            KategoriProperty kp = new KategoriProperty();
            kp.setIdKategori(String.valueOf(k.idKategori));
            kp.setNamaKategori(k.namaKategori);
            kategori.add(kp);
        });
        tabelKategori.setItems(kategori);
    }

    @FXML
    void onBtnAddClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputKategori.getText();
        boolean isInserted = kategoriRepository.InsertKategori(
                new Kategori(
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
        inputKategori.setText(null);
    }

    int clickedRow;
    @FXML
    void selectedRow(MouseEvent event) {
        clickedRow = tabelKategori.getSelectionModel().getSelectedIndex();
        String id = tabelKategori.getItems().get(clickedRow).getIdKategori();
        String nama = tabelKategori.getItems().get(clickedRow).getNamaKategori();
        inputID.setText(id);
        inputKategori.setText(nama);
    }

    @FXML
    void onBtnDeleteClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputKategori.getText();
        boolean isInserted = kategoriRepository.DeleteKategori(clickedRow,
                new Kategori(
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
        inputKategori.setText(null);
    }

    @FXML
    void onBtnUpdateClick(ActionEvent event) throws SQLException {
        String id = inputID.getText();
        String nama = inputKategori.getText();
        boolean isInserted = kategoriRepository.UpdateKategori(clickedRow,
                new Kategori(
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
        inputKategori.setText(null);
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
