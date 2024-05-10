package entity;

public class Customers {
    public int id;
    public String namaCustomer;
    public String alamat;
    public String noTelp;
    public int idRadius;

    public Customers(int id, String namaCustomer, String alamat, String noTelp, int idRadius) {
        this.id = id;
        this.namaCustomer = namaCustomer;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.idRadius = idRadius;
    }
}
