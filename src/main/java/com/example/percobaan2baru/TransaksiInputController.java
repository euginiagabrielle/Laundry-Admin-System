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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import repository.CustomerRepository;
import repository.ItemRepository;
import repository.MetodePembayaranRepository;
import repository.TransaksiRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransaksiInputController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnNew;

    @FXML
    private TextField inputDP;

    @FXML
    private TextField inputDriver;

    @FXML
    private ComboBox<String> inputItem;

    @FXML
    private ComboBox<String> inputJenisBayar;

    @FXML
    private ComboBox<String> inputLayanan;
    @FXML
    private ComboBox<String> inputNamaCust;

    @FXML
    private TextField inputKondisi;

    @FXML
    private TextField inputOngkirAntar;

    @FXML
    private TextField inputOngkirPickUp;

    @FXML
    private DatePicker inputTglSelesai;

    @FXML
    private DatePicker inputTglTrans;

    @FXML
    private Label labelCek;

    @FXML
    private Label labelDiskon;

    @FXML
    private Label labelId;

    @FXML
    private Label labelPromo;

    @FXML
    private Label labelTotalHarga;

    private ObservableList<TransaksiProperty> transaksi = FXCollections.observableArrayList();
    private TransaksiRepository transaksiRepository = new TransaksiRepository();
    public TransaksiInputController() throws SQLException {}



    @FXML
    void onBtnBackClick(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transaksi-menu.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    void onBtnInputClick(ActionEvent event) throws SQLException {
        String namaCustomer = inputNamaCust.getValue();
        LocalDate tglTrans = inputTglTrans.getValue();
        int ongkirP = 0;
        if ((inputOngkirPickUp.getText() != "")){
            ongkirP = Integer.parseInt(inputOngkirPickUp.getText());
        } else if ((inputOngkirPickUp.getText().isEmpty())) {
            ongkirP = 0;
        }
        int ongkirA = 0;
        if ((inputOngkirAntar.getText() != null)){
            ongkirA = Integer.parseInt(inputOngkirAntar.getText());
        } else if ((inputOngkirAntar.getText().isEmpty())) {
            ongkirA = 0;
        }
        int Dp = 0;
        if ((inputDP.getText() != null)){
            Dp = Integer.parseInt(inputDP.getText());
        } else if ((inputDP.getText().isEmpty())) {
            Dp = 0;
        }
        String driver = null;
        if ((inputDP.getText() != null)){
            driver = inputDriver.getText();
        } else if ((inputDP.getText().isEmpty())) {
            driver = null;
        }
        String metode = inputJenisBayar.getValue();
        boolean isInserted1 = transaksiRepository.InsertTransaksi(
                new Transaksi(
                        tglTrans,
                        Dp,
                        ongkirP,
                        ongkirA,
                        namaCustomer,
                        driver,
                        metode
                ), labelId
        );

        String kondisi = inputKondisi.getText();
        String layanan = inputLayanan.getValue();
        LocalDate tglSelesai = inputTglSelesai.getValue();
        String item = inputItem.getValue();
        boolean isInserted2 = transaksiRepository.InsertDetail(
                new DetailTransaksi(
                        kondisi,
                        layanan,
                        tglSelesai,
                        labelId.getText(),
                        item)
        );

        String promo = transaksiRepository.GetPromoId(tglTrans, item);
        float diskon = transaksiRepository.GetDiskon(promo);

        if (promo != "") {
            transaksiRepository.UpdateTransaksi(promo, labelId.getText());
            transaksiRepository.UpdatDetail(diskon, labelId.getText());
        }

        if (isInserted1 && isInserted2) {
            labelCek.setText("Data saved");
        } else {
            labelCek.setText("Failed to save data");
        }

        int totalHarga = transaksiRepository.GetTotalHarga(item, labelId.getText())+ongkirA+ongkirP;
        labelTotalHarga.setText("Rp " + totalHarga);

    }

    @FXML
    void onItemExit(KeyEvent event) throws SQLException {
        String item = inputItem.getValue();
        LocalDate tglTrans = inputTglTrans.getValue();
        String promo = transaksiRepository.GetPromoId(tglTrans, item);
        float diskon = transaksiRepository.GetDiskon(promo);
        float temp = diskon * 100;

        if (promo != "") {
            labelPromo.setText(promo);
            labelDiskon.setText(temp + "%");
        }
    }

    private MetodePembayaranRepository metodeRepository = new MetodePembayaranRepository();
    private ItemRepository itemRepository = new ItemRepository();
    private CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> metode = FXCollections.observableArrayList();
        ArrayList<MetodePembayaran> result;
        try {
            result = metodeRepository.GetMetode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((m) -> {
            MetodePembayaranProperty mp = new MetodePembayaranProperty();
            mp.setId(String.valueOf(m.idMetode));
            metode.add(mp.getId());
        });
        inputJenisBayar.setItems(metode);

        ObservableList<String> layanan = FXCollections.observableArrayList("Laundry","Dry Clean");
        inputLayanan.setItems(layanan);

        ObservableList<String> items = FXCollections.observableArrayList();
        ArrayList<Items> result1;
        try {
            result1 = itemRepository.GetItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result1.forEach((i) -> {
            ItemProperty ip = new ItemProperty();
            ip.setIdItem(String.valueOf(i.idItem));
            items.add(ip.getIdItem());
        });
        inputItem.setItems(items);

        ObservableList<String> customers = FXCollections.observableArrayList();
        ArrayList<Customers> result2;
        try {
            result2 = customerRepository.GetCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result2.forEach((c) -> {
            CustomerProperty cp = new CustomerProperty();
            cp.setId(c.namaCustomer);
            customers.add(cp.getId());
        });
        inputNamaCust.setItems(customers);
    }

    @FXML
    void onBtnNewClick(ActionEvent event) {
        inputTglTrans.setValue(null);
        inputNamaCust.setValue(null);
        labelId.setText(null);
        inputItem.setValue(null);
        inputLayanan.setValue(null);
        inputKondisi.setText(null);
        labelPromo.setText(null);
        labelDiskon.setText(null);
        inputOngkirPickUp.setText(null);
        inputOngkirAntar.setText(null);
        inputDriver.setText(null);
        inputJenisBayar.setValue(null);
        labelTotalHarga.setText(null);
        inputTglSelesai.setValue(null);
    }
}
