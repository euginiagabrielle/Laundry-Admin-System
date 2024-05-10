package entity;

import java.time.LocalDate;

public class Promo {
    public String idPromo;
    public String namaPromo;
    public LocalDate startDate;
    public LocalDate endDate;

    public Promo(String idPromo, String namaPromo, LocalDate startDate, LocalDate endDate) {
        this.idPromo = idPromo;
        this.namaPromo = namaPromo;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
