package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerProperty {
    private final StringProperty id;
    private final StringProperty namaCustomer;
    private final StringProperty alamat;
    private final StringProperty noTelp;
    private final StringProperty idRadius;

    // Constructor
    public CustomerProperty() {
        id = new SimpleStringProperty(this, "id");
        namaCustomer = new SimpleStringProperty(this, "nama customer");
        alamat = new SimpleStringProperty(this, "alamat");
        noTelp = new SimpleStringProperty(this, "no telp");
        idRadius = new SimpleStringProperty(this, "id radius");
    }

    // PAM
    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }


    public String getNamaCustomer() {
        return namaCustomer.get();
    }
    public StringProperty namaCustomerProperty() {
        return namaCustomer;
    }
    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer.set(namaCustomer);
    }


    public String getAlamat() {
        return alamat.get();
    }
    public StringProperty alamatProperty() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }


    public String getNoTelp() {
        return noTelp.get();
    }
    public StringProperty noTelpProperty() {
        return noTelp;
    }
    public void setNoTelp(String noTelp) {
        this.noTelp.set(noTelp);
    }


    public String getIdRadius() {
        return idRadius.get();
    }
    public StringProperty idRadiusProperty() {
        return idRadius;
    }
    public void setIdRadius(String idRadius) {
        this.idRadius.set(idRadius);
    }
}
