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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.KategoriRepository;
import repository.PromoRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPromoController implements Initializable {

    int clickedRow;

    @FXML
    private Button btnAddDetail;

    @FXML
    private Button btnAddPromo;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeleteDetail;

    @FXML
    private Button btnDeletePromo;

    @FXML
    private Button btnUpdateDetail;

    @FXML
    private Button btnUpdatePromo;

    @FXML
    private TableColumn<DetailPromoProperty, String> columnNoDetail;

    @FXML
    private TableColumn<DetailPromoProperty, String> columnIDPromoDetail;

    @FXML
    private TableColumn<DetailPromoProperty, String> columnDiskon;

    @FXML
    private TableColumn<DetailPromoProperty, String> columnKategori;

    @FXML
    private TableColumn<PromoProperty, String> columnID;

    @FXML
    private TableColumn<PromoProperty, String> columnNamaPromo;

    @FXML
    private TableColumn<PromoProperty, String> columnTglMulai;

    @FXML
    private TableColumn<PromoProperty, String> columnTglSelesai;

    @FXML
    private TextField inputDiskon;

    @FXML
    private TextField inputID;

    @FXML
    private ComboBox<String> inputKategori;

    @FXML
    private TextField inputNamaPromo;

    @FXML
    private TextField inputNoDetail;

    @FXML
    private DatePicker inputTglMulai;

    @FXML
    private DatePicker inputTglSelesai;

    @FXML
    private Label label;

    @FXML
    private TableView<PromoProperty> tabelPromo;

    @FXML
    private TableView<DetailPromoProperty> tabelDetail;

    private ObservableList<PromoProperty> promo = FXCollections.observableArrayList();
    private ObservableList<DetailPromoProperty> detailPromo = FXCollections.observableArrayList();
    private PromoRepository promoRepository = new PromoRepository();
    private KategoriRepository kategoriRepository = new KategoriRepository();

    public EditPromoController() throws SQLException {}

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

        columnID.setCellValueFactory(f -> f.getValue().idPromoProperty());
        columnNamaPromo.setCellValueFactory(f -> f.getValue().namaPromoProperty());
        columnTglMulai.setCellValueFactory(f -> f.getValue().startDateProperty());
        columnTglSelesai.setCellValueFactory(f -> f.getValue().endDateProperty());
        columnNoDetail.setCellValueFactory(f -> f.getValue().idDetailProperty());
        columnIDPromoDetail.setCellValueFactory(f -> f.getValue().idPromoProperty());
        columnDiskon.setCellValueFactory(f -> f.getValue().diskonProperty());
        columnKategori.setCellValueFactory(f -> f.getValue().idKategoriProperty());
        tabelPromo.setItems(promo);
        tabelDetail.setItems(detailPromo);
        try {
            updateTablePromo();
            updateTableDetailPromo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTablePromo() throws SQLException {
        promo = FXCollections.observableArrayList();
        ArrayList<Promo> result;
        try {
            result = promoRepository.GetPromo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((p) -> {
            PromoProperty pp = new PromoProperty();
            pp.setIdPromo(p.idPromo);
            pp.setNamaPromo(p.namaPromo);
            pp.setStartDate(String.valueOf(p.startDate));
            pp.setEndDate(String.valueOf(p.endDate));
            promo.add(pp);
        });

        tabelPromo.setItems(promo);
    }

    private void updateTableDetailPromo() throws SQLException {
        detailPromo = FXCollections.observableArrayList();
        ArrayList<DetailPromo> result;
        try {
            result = promoRepository.GetDetailPromo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((p) -> {
            DetailPromoProperty pp = new DetailPromoProperty();
            pp.setIdDetail(String.valueOf(p.idDetailPromo));
            pp.setDiskon(String.valueOf(p.diskon));
            pp.setIdKategori(p.idKategori);
            pp.setIdPromo(p.idPromo);
            detailPromo.add(pp);
        });
        tabelDetail.setItems(detailPromo);
    }

    @FXML
    void onBtnAddDetailClick(ActionEvent event) throws SQLException {
        String idPromo = inputID.getText();
        float diskon = Float.parseFloat(inputDiskon.getText());
        String idKategori = inputKategori.getValue();
        int noDetail = Integer.parseInt(inputNoDetail.getText());
        boolean isInserted = promoRepository.InsertDetailPromo(
                new DetailPromo(
                        noDetail,
                        diskon,
                        idKategori,
                        idPromo)
        );

        updateTableDetailPromo();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputNoDetail.setText(null);
        inputDiskon.setText(null);
        inputKategori.setValue(null);
    }

    @FXML
    void onBtnAddPromoClick(ActionEvent event) throws SQLException {
        String idPromo = inputID.getText();
        String namaPromo = inputNamaPromo.getText();
        LocalDate startDate = inputTglMulai.getValue();
        LocalDate endDate = inputTglSelesai.getValue();
        boolean isInserted = promoRepository.InsertPromo(
                new Promo(
                        idPromo,
                        namaPromo,
                        startDate,
                        endDate)
        );

        updateTablePromo();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputNamaPromo.setText(null);
        inputTglMulai.setValue(null);
        inputTglSelesai.setValue(null);
    }

    @FXML
    void selectedRow(MouseEvent event) {
        clickedRow = tabelPromo.getSelectionModel().getSelectedIndex();
        String idPromo = tabelPromo.getItems().get(clickedRow).getIdPromo();
        String namaPromo = tabelPromo.getItems().get(clickedRow).getNamaPromo();
        LocalDate startDate = LocalDate.parse(tabelPromo.getItems().get(clickedRow).getStartDate());
        LocalDate endDate = LocalDate.parse(tabelPromo.getItems().get(clickedRow).getEndDate());
        inputID.setText(idPromo);
        inputNamaPromo.setText(namaPromo);
        inputTglMulai.setValue(startDate);
        inputTglSelesai.setValue(endDate);
    }

    @FXML
    void onBtnDeletePromoClick(ActionEvent event) throws SQLException {
        String idPromo = inputID.getText();
        String namaPromo = inputNamaPromo.getText();
        LocalDate startDate = inputTglMulai.getValue();
        LocalDate endDate = inputTglSelesai.getValue();
        boolean isInserted = promoRepository.DeletePromo(clickedRow,
                new Promo(
                        idPromo,
                        namaPromo,
                        startDate,
                        endDate)
        );

        updateTablePromo();
        updateTableDetailPromo();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputID.setText(null);
        inputNamaPromo.setText(null);
        inputTglMulai.setValue(null);
        inputTglSelesai.setValue(null);

    }

    @FXML
    void onBtnUpdatePromoClick(ActionEvent event) throws SQLException {
        String idPromo = inputID.getText();
        String namaPromo = inputNamaPromo.getText();
        LocalDate startDate = inputTglMulai.getValue();
        LocalDate endDate = inputTglSelesai.getValue();
        boolean isInserted = promoRepository.UpdatePromo(clickedRow,
                new Promo(
                        idPromo,
                        namaPromo,
                        startDate,
                        endDate)
        );

        updateTablePromo();
        updateTableDetailPromo();

        if (isInserted) {
            label.setText("Data updated successfully");
        } else {
            label.setText("Failed to update data");
        }

        inputID.setText(null);
        inputNamaPromo.setText(null);
        inputTglMulai.setValue(null);
        inputTglSelesai.setValue(null);
    }

    @FXML
    void selectedRowDetail(MouseEvent event) {
        clickedRow = tabelDetail.getSelectionModel().getSelectedIndex();
        String noDetail = tabelDetail.getItems().get(clickedRow).getIdDetail();
        String diskon = tabelDetail.getItems().get(clickedRow).getDiskon();
        String idKategori = tabelDetail.getItems().get(clickedRow).getIdKategori();
        String idPromo = tabelDetail.getItems().get(clickedRow).getIdPromo();
        inputID.setText(idPromo);
        inputNoDetail.setText(noDetail);
        inputDiskon.setText(diskon);
        inputKategori.setValue(idKategori);
    }

    @FXML
    void onBtnDeleteDetailClick(ActionEvent event) throws SQLException {
        int noDetail = Integer.parseInt(inputNoDetail.getText());
        float diskon = Float.parseFloat(inputDiskon.getText());
        String idKategori = inputKategori.getValue();
        String idPromo = inputID.getText();
        boolean isInserted = promoRepository.DeleteDetailPromo(clickedRow,
                new DetailPromo(
                        noDetail,
                        diskon,
                        idKategori,
                        idPromo)
        );

        updateTableDetailPromo();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputNoDetail.setText(null);
        inputDiskon.setText(null);
        inputKategori.setValue(null);
        inputID.setText(null);
    }

    @FXML
    void onBtnUpdateDetailClick(ActionEvent event) throws SQLException {
        int noDetail = Integer.parseInt(inputNoDetail.getText());
        float diskon = Float.parseFloat(inputDiskon.getText());
        String idKategori = inputKategori.getValue();
        String idPromo = inputID.getText();
        boolean isInserted = promoRepository.UpdateDetailPromo(clickedRow,
                new DetailPromo(
                        noDetail,
                        diskon,
                        idKategori,
                        idPromo)
        );

        updateTableDetailPromo();

        if (isInserted) {
            label.setText("Data updated successfully");
        } else {
            label.setText("Failed to update data");
        }

        inputID.setText(null);
        inputNoDetail.setText(null);
        inputDiskon.setText(null);
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
