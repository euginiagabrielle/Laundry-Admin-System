package com.example.percobaan2baru;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.ItemRepository;
import repository.KategoriRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditItemController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ItemProperty, String> columnHarga;

    @FXML
    private TableColumn<ItemProperty, String> columnID;

    @FXML
    private TableColumn<ItemProperty, String> columnKategori;

    @FXML
    private TableColumn<ItemProperty, String> columnLamaKerja;

    @FXML
    private TableColumn<ItemProperty, String> columnNama;

    @FXML
    private TextField inputHarga;

    @FXML
    private TextField inputID;

    @FXML
    private ChoiceBox<String> inputKategori;

    @FXML
    private TextField inputNama;

    @FXML
    private TextField inputPenyelesaian;

    @FXML
    private Label label;

    @FXML
    private TableView<ItemProperty> tabelItem;

    private ObservableList<ItemProperty> items = FXCollections.observableArrayList();
    private ItemRepository itemRepository = new ItemRepository();
    private KategoriRepository kategoriRepository = new KategoriRepository();
    public EditItemController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<Kategori> result;
        try {
            result = kategoriRepository.GetKategori();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((k) -> {
            KategoriProperty kp = new KategoriProperty();
            kp.setIdKategori(k.idKategori);
            list.add(kp.getIdKategori());
        });
        inputKategori.setItems(list);

        columnID.setCellValueFactory(f -> f.getValue().idItemProperty());
        columnNama.setCellValueFactory(f -> f.getValue().namaItemProperty());
        columnHarga.setCellValueFactory(f -> f.getValue().hargaProperty());
        columnLamaKerja.setCellValueFactory(f -> f.getValue().lamaPenyelesaianProperty());
        columnKategori.setCellValueFactory(f -> f.getValue().idKategoriProperty());
        tabelItem.setItems(items);
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() throws SQLException {
        items = FXCollections.observableArrayList();
        ArrayList<Items> result;
        try {
            result = itemRepository.GetItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((i) -> {
            ItemProperty ip = new ItemProperty();
            ip.setIdItem(String.valueOf(i.idItem));
            ip.setNamaItem(i.namaItem);
            ip.setHarga(String.valueOf(i.harga));
            ip.setLamaPenyelesaian(String.valueOf(i.lamaPenyelesaian));
            ip.setIdKategori(i.idKategori);
            items.add(ip);
        });
        tabelItem.setItems(items);
    }

    @FXML
    void onBtnAddClick(ActionEvent event) throws SQLException {
        String idItem = inputID.getText();
        String namaItem = inputNama.getText();
        int harga = Integer.parseInt(inputHarga.getText());
        int penyelesaian = Integer.parseInt(inputPenyelesaian.getText());
        String idKategori = inputKategori.getValue();
        boolean isInserted = itemRepository.InsertItem(
                new Items(
                        idItem,
                        namaItem,
                        harga,
                        penyelesaian,
                        idKategori)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputHarga.setText(null);
        inputPenyelesaian.setText(null);
        inputKategori.setValue(null);
    }

    int clickedRow;
    @FXML
    void selectedRow(MouseEvent event) {
        clickedRow = tabelItem.getSelectionModel().getSelectedIndex();
        String idItem = tabelItem.getItems().get(clickedRow).getIdItem();
        String namaItem = tabelItem.getItems().get(clickedRow).getNamaItem();
        String harga = tabelItem.getItems().get(clickedRow).getHarga();
        String penyelesaian = tabelItem.getItems().get(clickedRow).getLamaPenyelesaian();
        String idKategori = tabelItem.getItems().get(clickedRow).getIdKategori();
        inputID.setText(idItem);
        inputNama.setText(namaItem);
        inputHarga.setText(harga);
        inputPenyelesaian.setText(penyelesaian);
        inputKategori.setValue(idKategori);
    }

    @FXML
    void onBtnDeleteClick(ActionEvent event) throws SQLException {
        String idItem = inputID.getText();
        String namaItem = inputNama.getText();
        int harga = Integer.parseInt(inputHarga.getText());
        int penyelesaian = Integer.parseInt(inputPenyelesaian.getText());
        String idKategori = inputKategori.getValue();
        boolean isInserted = itemRepository.DeleteItem(clickedRow,
                new Items(
                        idItem,
                        namaItem,
                        harga,
                        penyelesaian,
                        idKategori)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputHarga.setText(null);
        inputPenyelesaian.setText(null);
        inputKategori.setValue(null);
    }

    @FXML
    void onBtnUpdateClick(ActionEvent event) throws SQLException {
        String idItem = inputID.getText();
        String namaItem = inputNama.getText();
        int harga = Integer.parseInt(inputHarga.getText());
        int penyelesaian = Integer.parseInt(inputPenyelesaian.getText());
        String idKategori = inputKategori.getValue();
        boolean isInserted = itemRepository.UpdateItem(clickedRow,
                new Items(
                        idItem,
                        namaItem,
                        harga,
                        penyelesaian,
                        idKategori)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data updated successfully");
        } else {
            label.setText("Failed to update data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputHarga.setText(null);
        inputPenyelesaian.setText(null);
        inputKategori.setValue(null);
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
