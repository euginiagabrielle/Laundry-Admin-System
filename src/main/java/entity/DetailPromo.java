package entity;

public class DetailPromo {
    public int idDetailPromo;
    public float diskon;
    public String idKategori;
    public String idPromo;

    public DetailPromo(int idDetailPromo, float diskon, String idKategori, String idPromo) {
        this.idDetailPromo = idDetailPromo;
        this.diskon = diskon;
        this.idKategori = idKategori;
        this.idPromo = idPromo;
    }
}
