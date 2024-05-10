package entity;

public class Items {
    public String idItem;
    public String namaItem;
    public int harga;
    public int lamaPenyelesaian;
    public String idKategori;

    public Items(String idItem, String namaItem, int harga, int lamaPenyelesaian, String id_kategori) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.harga = harga;
        this.lamaPenyelesaian = lamaPenyelesaian;
        this.idKategori = id_kategori;
    }
}
