package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemProperty {
    private final StringProperty idItem;
    private final StringProperty namaItem;
    private final StringProperty harga;
    private final StringProperty lamaPenyelesaian;
    private final StringProperty idKategori;

    // Constructor
    public ItemProperty() {
        idItem = new SimpleStringProperty(this, "id item");
        namaItem = new SimpleStringProperty(this, "nama item");
        harga = new SimpleStringProperty(this, "harga");
        lamaPenyelesaian = new SimpleStringProperty(this, "lama penyelesaian");
        idKategori = new SimpleStringProperty(this, "id kategori");
    }

    // PAM
    public String getIdItem() {
        return idItem.get();
    }

    public StringProperty idItemProperty() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem.set(idItem);
    }

    public String getNamaItem() {
        return namaItem.get();
    }

    public StringProperty namaItemProperty() {
        return namaItem;
    }

    public String getHarga() {
        return harga.get();
    }

    public StringProperty hargaProperty() {
        return harga;
    }

    public String getLamaPenyelesaian() {
        return lamaPenyelesaian.get();
    }

    public StringProperty lamaPenyelesaianProperty() {
        return lamaPenyelesaian;
    }

    public String getIdKategori() {
        return idKategori.get();
    }

    public StringProperty idKategoriProperty() {
        return idKategori;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem.set(namaItem);
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }

    public void setLamaPenyelesaian(String lamaPenyelesaian) {
        this.lamaPenyelesaian.set(lamaPenyelesaian);
    }

    public void setIdKategori(String idKategori) {
        this.idKategori.set(idKategori);
    }
}
