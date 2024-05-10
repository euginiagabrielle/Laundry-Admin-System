package entity;

import java.time.LocalDate;
import java.util.Date;

public class Transaksi {
    public String idTransaksi;
    public LocalDate tglTransaksi;
    public int DP;
//    public int pelunasan;
    public int ongkirPickup;
    public int ongkirDeliver;
    public String namaCustomer;
    public String namaDriver;
    public String idMetode;

    public Transaksi(LocalDate tglTransaksi, int DP, int ongkirPickup, int ongkirDeliver, String namaCustomer, String namaDriver, String idMetode) {
        this.tglTransaksi = tglTransaksi;
        this.DP = DP;
        this.ongkirPickup = ongkirPickup;
        this.ongkirDeliver = ongkirDeliver;
        this.namaCustomer = namaCustomer;
        this.namaDriver = namaDriver;
        this.idMetode = idMetode;
    }

//    public void setPelunasan(int pelunasan) {
//        this.pelunasan = pelunasan;
//    }

}
