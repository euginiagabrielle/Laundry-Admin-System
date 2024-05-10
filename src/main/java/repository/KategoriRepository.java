package repository;

import entity.Kategori;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KategoriRepository {
    private Connection conn;
    public KategoriRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertKategori(Kategori entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO kategori(id_kategori, nama_kategori) VALUES('%s', '%s')",
                entity.idKategori,
                entity.namaKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateKategori(int clickedRow, Kategori entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE kategori " + "SET nama_kategori='" + entity.namaKategori + "' WHERE id_kategori='" + entity.idKategori + "'",
                entity.idKategori,
                entity.namaKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteKategori(int clickedRow, Kategori entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM kategori WHERE id_kategori='" + entity.idKategori + "'",
                entity.idKategori,
                entity.namaKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<Kategori> GetKategori() throws SQLException {
        ArrayList<Kategori> kategori = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM kategori");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            kategori.add(
                    new Kategori(
                            rs.getString("id_kategori"),
                            rs.getString("nama_kategori")
                    )
            );
        }
        return kategori;
    }
}
