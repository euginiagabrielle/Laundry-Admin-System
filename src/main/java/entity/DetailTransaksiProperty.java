package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailTransaksiProperty {
    private final StringProperty idDetail;
    private final StringProperty kondisiItem;
    private final StringProperty jenisLayanan;
    private final StringProperty diskon;
    private final StringProperty hargaSetelahDiskon;
    private final StringProperty tglAmbil;
    private final StringProperty idTransaksi;
    private final StringProperty idItem;

    // Constructor
    public DetailTransaksiProperty() {
        idDetail = new SimpleStringProperty(this, "id detail");
        kondisiItem = new SimpleStringProperty(this, "kondisi item");
        jenisLayanan = new SimpleStringProperty(this, "jenis layanan");
        diskon = new SimpleStringProperty(this, "diskon");
        hargaSetelahDiskon = new SimpleStringProperty(this, "harga setelah diskon");
        tglAmbil = new SimpleStringProperty(this, "tgl ambil");
        idTransaksi = new SimpleStringProperty(this, "id transaksi");
        idItem = new SimpleStringProperty(this, "id item");
    }

    // PAM

    public String getIdDetail() {
        return idDetail.get();
    }

    public StringProperty idDetailProperty() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail.set(idDetail);
    }

    public String getKondisiItem() {
        return kondisiItem.get();
    }

    public StringProperty kondisiItemProperty() {
        return kondisiItem;
    }

    public void setKondisiItem(String kondisiItem) {
        this.kondisiItem.set(kondisiItem);
    }

    public String getJenisLayanan() {
        return jenisLayanan.get();
    }

    public StringProperty jenisLayananProperty() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan.set(jenisLayanan);
    }

    public String getDiskon() {
        return diskon.get();
    }

    public StringProperty diskonProperty() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon.set(diskon);
    }

    public String getHargaSetelahDiskon() {
        return hargaSetelahDiskon.get();
    }

    public StringProperty hargaSetelahDiskonProperty() {
        return hargaSetelahDiskon;
    }

    public void setHargaSetelahDiskon(String hargaSetelahDiskon) {
        this.hargaSetelahDiskon.set(hargaSetelahDiskon);
    }

    public String getTglAmbil() {
        return tglAmbil.get();
    }

    public StringProperty tglAmbilProperty() {
        return tglAmbil;
    }

    public void setTglAmbil(String tglAmbil) {
        this.tglAmbil.set(tglAmbil);
    }

    public String getIdTransaksi() {
        return idTransaksi.get();
    }

    public StringProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public String getIdItem() {
        return idItem.get();
    }

    public StringProperty idItemProperty() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem.set(idItem);
    }
}
