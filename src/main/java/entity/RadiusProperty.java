package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RadiusProperty {
    private final StringProperty idRadius;
    private final StringProperty minRadius;
    private final StringProperty maxRadius;
    private final StringProperty harga;

    // Constructor
    public RadiusProperty() {
        idRadius = new SimpleStringProperty(this, "id radius");
        minRadius = new SimpleStringProperty(this, "min radius");
        maxRadius= new SimpleStringProperty(this, "max radius");
        harga = new SimpleStringProperty(this, "harga");
    }

    // PAM
    public String getIdRadius() {
        return idRadius.get();
    }
    public StringProperty idRadiusProperty() {
        return idRadius;
    }
    public void setIdRadius(String idRadius) {
        this.idRadius.set(idRadius);
    }


    public String getMinRadius() {
        return minRadius.get();
    }
    public StringProperty minRadiusProperty() {
        return minRadius;
    }
    public void setMinRadius(String minRadius) {
        this.minRadius.set(minRadius);
    }


    public String getMaxRadius() {
        return maxRadius.get();
    }
    public StringProperty maxRadiusProperty() {
        return maxRadius;
    }
    public void setMaxRadius(String maxRadius) {
        this.maxRadius.set(maxRadius);
    }


    public String getHarga() {
        return harga.get();
    }
    public StringProperty hargaProperty() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga.set(harga);
    }
}
