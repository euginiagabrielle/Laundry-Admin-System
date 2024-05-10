package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KategoriProperty {
    private final StringProperty idKategori;
    private final StringProperty namaKategori;

    // Constructor
    public KategoriProperty() {
        idKategori = new SimpleStringProperty(this, "id kategori");
        namaKategori = new SimpleStringProperty(this, "nama kategori");
    }

    // PAM
    public String getIdKategori() {
        return idKategori.get();
    }

    public StringProperty idKategoriProperty() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori.set(idKategori);
    }

    public String getNamaKategori() {
        return namaKategori.get();
    }

    public StringProperty namaKategoriProperty() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori.set(namaKategori);
    }
}
