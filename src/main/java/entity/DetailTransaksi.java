package entity;

import java.time.LocalDate;

public class DetailTransaksi {
    public int idDetailTransaksi;
    public String kondisiItem;
    public String jenisLayanan;
    public float diskon;
    public LocalDate tanggalSelesai;
    public String idTransaksi;
    public String idItem;

    public DetailTransaksi(String kondisiItem, String jenisLayanan, LocalDate tanggalSelesai, String idTransaksi, String idItem) {
        this.kondisiItem = kondisiItem;
        this.jenisLayanan = jenisLayanan;
        this.tanggalSelesai = tanggalSelesai;
        this.idTransaksi = idTransaksi;
        this.idItem = idItem;
    }

    public float getDiskon() {
        return diskon;
    }

    public void setDiskon(float diskon) {
        this.diskon = diskon;
    }
}
