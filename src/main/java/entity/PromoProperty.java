package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PromoProperty {
    private final StringProperty idPromo;
    private final StringProperty namaPromo;
    private final StringProperty startDate;
    private final StringProperty endDate;

    // Constructor
    public PromoProperty() {
        idPromo = new SimpleStringProperty(this, "id promo");
        namaPromo = new SimpleStringProperty(this, "nama promo");
        startDate = new SimpleStringProperty(this, "start date");
        endDate = new SimpleStringProperty(this, "end date");
    }

    // PAM

    public String getIdPromo() {
        return idPromo.get();
    }

    public StringProperty idPromoProperty() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo.set(idPromo);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getNamaPromo() {
        return namaPromo.get();
    }

    public StringProperty namaPromoProperty() {
        return namaPromo;
    }

    public void setNamaPromo(String namaPromo) {
        this.namaPromo.set(namaPromo);
    }
}

