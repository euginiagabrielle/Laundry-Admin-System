package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailPromoProperty {
    private final StringProperty idDetail;
    private final StringProperty diskon;
    private final StringProperty idKategori;
    private final StringProperty idPromo;

    // Constructor
    public DetailPromoProperty() {
        idDetail = new SimpleStringProperty(this, "id detail promo");
        diskon = new SimpleStringProperty(this, "diskon");
        idKategori = new SimpleStringProperty(this, "id kategori");
        idPromo = new SimpleStringProperty(this, "id promo");
    }

    public String getIdDetail() {
        return idDetail.get();
    }

    public StringProperty idDetailProperty() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail.set(idDetail);
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

    public String getIdKategori() {
        return idKategori.get();
    }

    public StringProperty idKategoriProperty() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori.set(idKategori);
    }

    public String getIdPromo() {
        return idPromo.get();
    }

    public StringProperty idPromoProperty() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo.set(idPromo);
    }
}
