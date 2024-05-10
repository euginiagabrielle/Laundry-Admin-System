package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TransaksiProperty {
    private final StringProperty idTransaksi;
    private final StringProperty tanggalOrder;
    private final StringProperty totalHarga;
    private final StringProperty DP;
    private final StringProperty pelunasan;
    private final StringProperty ongkirPickup;
    private final StringProperty ongkirDeliver;
    private final StringProperty namaCustomer;
    private final StringProperty idPromo;
    private final StringProperty namaDriver;
    private final StringProperty idMetode;

    // Constructor
    public TransaksiProperty() {
        idTransaksi = new SimpleStringProperty(this, "id transaksi");
        tanggalOrder = new SimpleStringProperty(this, "tanggal order");
        totalHarga = new SimpleStringProperty(this, "total harga");
        DP = new SimpleStringProperty(this, "DP");
        pelunasan = new SimpleStringProperty(this, "pelunasan");
        ongkirPickup = new SimpleStringProperty(this, "ongkir pickup");
        ongkirDeliver = new SimpleStringProperty(this, "ongkir deliver");
        namaCustomer = new SimpleStringProperty(this, "nama customer");
        idPromo = new SimpleStringProperty(this, "id promo");
        namaDriver = new SimpleStringProperty(this, "id driver");
        idMetode = new SimpleStringProperty(this, "id metode");
    }

    // PAM
    public String getIdTransaksi() {
        return idTransaksi.get();
    }

    public StringProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public String getTanggalOrder() {
        return tanggalOrder.get();
    }

    public StringProperty tanggalOrderProperty() {
        return tanggalOrder;
    }

    public String getTotalHarga() {
        return totalHarga.get();
    }

    public StringProperty totalHargaProperty() {
        return totalHarga;
    }

    public String getDP() {
        return DP.get();
    }

    public StringProperty DPProperty() {
        return DP;
    }

    public String getPelunasan() {
        return pelunasan.get();
    }

    public StringProperty pelunasanProperty() {
        return pelunasan;
    }

    public void setPelunasan(String pelunasan) {
        this.pelunasan.set(pelunasan);
    }

    public String getOngkirPickup() {
        return ongkirPickup.get();
    }

    public StringProperty ongkirPickupProperty() {
        return ongkirPickup;
    }

    public String getOngkirDeliver() {
        return ongkirDeliver.get();
    }

    public StringProperty ongkirDeliverProperty() {
        return ongkirDeliver;
    }

    public String getNamaCustomer() {
        return namaCustomer.get();
    }

    public StringProperty namaCustomerProperty() {
        return namaCustomer;
    }

    public String getIdPromo() {
        return idPromo.get();
    }

    public StringProperty idPromoProperty() {
        return idPromo;
    }

    public String getNamaDriver() {
        return namaDriver.get();
    }

    public StringProperty namaDriverProperty() {
        return namaDriver;
    }

    public String getIdMetode() {
        return idMetode.get();
    }

    public StringProperty idMetodeProperty() {
        return idMetode;
    }

    public void setTanggalOrder(String tanggalOrder) {
        this.tanggalOrder.set(tanggalOrder);
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga.set(totalHarga);
    }

    public void setDP(String DP) {
        this.DP.set(DP);
    }

    public void setOngkirPickup(String ongkirPickup) {
        this.ongkirPickup.set(ongkirPickup);
    }

    public void setOngkirDeliver(String ongkirDeliver) {
        this.ongkirDeliver.set(ongkirDeliver);
    }

    public void setNamaCustomer(String idCustomer) {
        this.namaCustomer.set(idCustomer);
    }

    public void setIdPromo(String idPromo) {
        this.idPromo.set(idPromo);
    }

    public void setNamaDriver(String idDriver) {
        this.namaDriver.set(idDriver);
    }

    public void setIdMetode(String idMetode) {
        this.idMetode.set(idMetode);
    }
}
