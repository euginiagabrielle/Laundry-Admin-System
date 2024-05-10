package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MetodePembayaranProperty {
    private final StringProperty id;
    private final StringProperty namaMetode;

    // COnstructor
    public MetodePembayaranProperty() {
        id = new SimpleStringProperty(this, "id metode");
        namaMetode = new SimpleStringProperty(this, "nama metode");
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

    public String getNamaMetode() {
        return namaMetode.get();
    }

    public StringProperty namaMetodeProperty() {
        return namaMetode;
    }

    public void setNamaMetode(String namaMetode) {
        this.namaMetode.set(namaMetode);
    }
}
