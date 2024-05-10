package com.example.percobaan2baru;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import repository.TransaksiRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransaksiPelunasanController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnOkLunas;

    @FXML
    private Button btnClear;

    @FXML
    private ComboBox<String> inputIdTransaksi;

    @FXML
    private Label labelCek;

    @FXML
    private Label labelDP;

    @FXML
    private Label labelPelunasan;

    @FXML
    private Label labelTotalHarga;

    private TransaksiRepository transaksiRepository = new TransaksiRepository();

    public TransaksiPelunasanController() throws SQLException {
    }

    @FXML
    void onBtnBackClick(ActionEvent event) {
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
    void onBtnCekClick(ActionEvent event) throws SQLException {
        String idTrans = inputIdTransaksi.getValue();
        String item = transaksiRepository.GetIdItem(idTrans);
        int totalHarga = transaksiRepository.GetTotalHarga(item, idTrans)+transaksiRepository.GetOngkirP(idTrans)+transaksiRepository.GetOngkirA(idTrans);
        int DP = transaksiRepository.GetDP(idTrans);
        int pelunasan = totalHarga - DP;
        boolean isLunas = transaksiRepository.UpdateLunas(pelunasan, idTrans);
        if (isLunas) {
            System.out.println("Data saved");
        } else {
            System.out.println("Failed to save data");
        }
    }

    @FXML
    void onIdExit(KeyEvent event) throws SQLException {
        String idTrans = inputIdTransaksi.getValue();
        String item = transaksiRepository.GetIdItem(idTrans);
        int totalHarga = transaksiRepository.GetTotalHarga(item, idTrans)+transaksiRepository.GetOngkirP(idTrans)+transaksiRepository.GetOngkirA(idTrans);
        int DP = transaksiRepository.GetDP(idTrans);
        if (DP != 0) {
            int pelunasan = totalHarga - DP;
            labelTotalHarga.setText("Rp " + totalHarga);
            labelDP.setText("Rp " + DP);
            labelPelunasan.setText("Rp " + pelunasan);
        } else {
            labelCek.setText("Sudah Lunas");
        }
    }


    @FXML
    void onBtnClearClick(ActionEvent event) {
        labelPelunasan.setText(null);
        labelDP.setText(null);
        labelTotalHarga.setText(null);
        inputIdTransaksi.setValue(null);
        labelCek.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> idTrans = FXCollections.observableArrayList();
        ArrayList<String> result;
        try {
            result = transaksiRepository.GetIdTransaksi();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((id) -> {
            idTrans.add(id);
        });
        inputIdTransaksi.setItems(idTrans);
    }
}
