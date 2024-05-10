package com.example.percobaan2baru;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import repository.ReportRepository;
import repository.TransaksiRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnData;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnLayanan;

    @FXML
    private Button btnPendapatan;

    @FXML
    private Button btnTransaksi;

    @FXML
    private Label labelIsi;

    @FXML
    private Label labelTitle;

    private ReportRepository reportRepository = new ReportRepository();
    private TransaksiRepository transaksiRepository = new TransaksiRepository();
    public HelloController() throws SQLException {
    }

    @FXML
    void onBtnCustomerClick(ActionEvent event) throws SQLException {
        labelTitle.setText("Customer dengan transaksi terbanyak :");
        ObservableList<String> customer = FXCollections.observableArrayList();
        ArrayList<String> result;
        try {
            result = reportRepository.GetTransaksiTerbanyak();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((nama) -> {
            customer.add(nama);
        });
        labelIsi.setText("1. " + customer.get(0) + "\n" + "2. " + customer.get(1) + "\n" + "3. " + customer.get(2));
    }

    @FXML
    void onBtnItemClick(ActionEvent event) {
        labelTitle.setText("Item yang paling banyak di Laundry/Dry Clean :");
        ObservableList<String> item = FXCollections.observableArrayList();
        ArrayList<String> result;
        try {
            result = reportRepository.GetItemTerbanyak();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((nama) -> {
            item.add(nama);
        });
        labelIsi.setText("1. " + item.get(0) + "\n" + "2. " + item.get(1) + "\n" + "3. " + item.get(2));
    }

    @FXML
    void onBtnLayananClick(ActionEvent event) {
        labelTitle.setText("Layanan yang paling sering digunakan :");
        ObservableList<String> layanan = FXCollections.observableArrayList();
        ArrayList<String> result;
        try {
            result = reportRepository.GetLayananTerbanyak();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((nama) -> {
            layanan.add(nama);
        });
        labelIsi.setText(layanan.get(0));
    }

    @FXML
    void onBtnPendapatanClick(ActionEvent event) throws SQLException {
        ArrayList<String> idTrans = transaksiRepository.GetIdTransaksi();
        int totalHarga = 0;
        for (int i = 0; i < idTrans.size(); i++) {
            String item = transaksiRepository.GetIdItem(idTrans.get(i));
            totalHarga += transaksiRepository.GetTotalHarga(item, idTrans.get(i))+transaksiRepository.GetOngkirP(idTrans.get(i))+transaksiRepository.GetOngkirA(idTrans.get(i));
        }
        labelTitle.setText("Pendapatan : ");
        labelIsi.setText("Rp " + totalHarga);

    }

    @FXML
    void onBtnDataClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-menu.fxml"));
            Stage stage = (Stage) btnData.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    void onBtnTransaksiClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transaksi-menu.fxml"));
            Stage stage = (Stage) btnTransaksi.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}
