package com.example.percobaan2baru;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.CustomerRepository;
import repository.RadiusRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnBack;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputAlamat;

    @FXML
    private TextField inputNama;

    @FXML
    private TextField inputNoTelp;

    @FXML
    private ChoiceBox<Integer> inputRadius = new ChoiceBox<Integer>();

    @FXML
    private TableColumn<CustomerProperty, String> columnID;

    @FXML
    private TableColumn<CustomerProperty, String> columnNama;

    @FXML
    private TableColumn<CustomerProperty, String> columnAlamat;

    @FXML
    private TableColumn<CustomerProperty, String> columnNoTelp;

    @FXML
    private TableColumn<CustomerProperty, String> columnRadius;

    @FXML
    private TableView<CustomerProperty> tabelCustomer;

    private ObservableList<CustomerProperty> customers = FXCollections.observableArrayList();
    private CustomerRepository customerRepository = new CustomerRepository();
    private RadiusRepository radiusRepository = new RadiusRepository();
    public EditCustomerController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        ArrayList<Radius> result;
        try {
            result = radiusRepository.GetRadius();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((m) -> {
            RadiusProperty rp = new RadiusProperty();
            rp.setIdRadius(String.valueOf(m.idRadius));
            list.add(Integer.valueOf(rp.getIdRadius()));
        });
        inputRadius.setItems(list);

        columnID.setCellValueFactory(f -> f.getValue().idProperty());
        columnNama.setCellValueFactory(f -> f.getValue().namaCustomerProperty());
        columnAlamat.setCellValueFactory(f -> f.getValue().alamatProperty());
        columnNoTelp.setCellValueFactory(f -> f.getValue().noTelpProperty());
        columnRadius.setCellValueFactory(f -> f.getValue().idRadiusProperty());
        tabelCustomer.setItems(customers);
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() throws SQLException {
        customers = FXCollections.observableArrayList();
        ArrayList<Customers> result;
        try {
            result = customerRepository.GetCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.forEach((c) -> {
            CustomerProperty cp = new CustomerProperty();
            cp.setId(String.valueOf(c.id));
            cp.setNamaCustomer(c.namaCustomer);
            cp.setAlamat(c.alamat);
            cp.setNoTelp(c.noTelp);
            cp.setIdRadius(String.valueOf(c.idRadius));
            customers.add(cp);
        });
        tabelCustomer.setItems(customers);
    }

    @FXML
    void onBtnAddClick(ActionEvent event) throws SQLException {
        int idCustomer = Integer.parseInt(inputID.getText());
        String namaCustomer = inputNama.getText();
        String alamat = inputAlamat.getText();
        String noTelp = inputNoTelp.getText();
        int radius = inputRadius.getSelectionModel().getSelectedIndex()+1;
        boolean isInserted = customerRepository.InsertCustomer(
                new Customers(
                        idCustomer,
                        namaCustomer,
                        alamat,
                        noTelp,
                        radius)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data added successfully");
        } else {
            label.setText("Failed to add data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputAlamat.setText(null);
        inputNoTelp.setText(null);
        inputRadius.setValue(null);
    }

    int clickedRow;
    @FXML
    void selectedRow() {
        clickedRow = tabelCustomer.getSelectionModel().getSelectedIndex();
        String id = tabelCustomer.getItems().get(clickedRow).getId();
        String nama = tabelCustomer.getItems().get(clickedRow).getNamaCustomer();
        String alamat = tabelCustomer.getItems().get(clickedRow).getAlamat();
        String noTelp = tabelCustomer.getItems().get(clickedRow).getNoTelp();
        int idRadius = Integer.parseInt(tabelCustomer.getItems().get(clickedRow).getIdRadius());
        inputID.setText(id);
        inputNama.setText(nama);
        inputAlamat.setText(alamat);
        inputNoTelp.setText(noTelp);
        inputRadius.setValue(idRadius);
    }

    @FXML
    void onBtnUpdateClick(ActionEvent event) throws SQLException {
        int idCustomer = Integer.parseInt(inputID.getText());
        String namaBaru = inputNama.getText();
        String alamatBaru = inputAlamat.getText();
        String noTelpBaru = inputNoTelp.getText();
        int idRadiusBaru = inputRadius.getSelectionModel().getSelectedIndex()+1;
        boolean isInserted = customerRepository.UpdateCustomer(clickedRow,
                new Customers(
                        idCustomer,
                        namaBaru,
                        alamatBaru,
                        noTelpBaru,
                        idRadiusBaru)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data updated successfully");
        } else {
            label.setText("Failed to update data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputAlamat.setText(null);
        inputNoTelp.setText(null);
        inputRadius.setValue(null);
    }

    @FXML
    void onBtnDeleteClick(ActionEvent event) throws SQLException {
        int idCustomer = Integer.parseInt(inputID.getText());
        String nama = inputNama.getText();
        String alamat = inputAlamat.getText();
        String noTelp = inputNoTelp.getText();
        int idRadius = inputRadius.getSelectionModel().getSelectedIndex()+1;
        boolean isInserted = customerRepository.DeleteCustomer(clickedRow,
                new Customers(
                        idCustomer,
                        nama,
                        alamat,
                        noTelp,
                        idRadius)
        );

        updateTable();

        if (isInserted) {
            label.setText("Data deleted successfully");
        } else {
            label.setText("Failed to delete data");
        }

        inputID.setText(null);
        inputNama.setText(null);
        inputAlamat.setText(null);
        inputNoTelp.setText(null);
        inputRadius.setValue(null);
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
