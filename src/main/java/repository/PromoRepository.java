package repository;

import entity.DetailPromo;
import entity.Promo;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PromoRepository {
    private Connection conn;
    public PromoRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertPromo(Promo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO promo(id_promo, nama_promo, start_date, end_date) VALUES('" + entity.idPromo + "','" + entity.namaPromo + "','" + entity.startDate + "','" + entity.endDate + "');",
                entity.idPromo,
                entity.namaPromo,
                entity.startDate,
                entity.endDate);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean InsertDetailPromo(DetailPromo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO detail_promo(id_detail_promo, diskon, id_kategori, id_promo) VALUES ('%d','%f','%s','%s')",
                entity.idDetailPromo,
                entity.diskon,
                entity.idKategori,
                entity.idPromo);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdatePromo(int clickedRow, Promo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE promo SET nama_promo='" + entity.namaPromo + "', start_date='" + entity.startDate + "', end_date='" + entity.endDate + "' WHERE id_promo ='" + entity.idPromo + "'",
                entity.idPromo,
                entity.namaPromo,
                entity.startDate,
                entity.endDate);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateDetailPromo(int clickedRow, DetailPromo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE detail_promo SET diskon='" + entity.diskon + "', id_kategori='" + entity.idKategori + "', id_promo='" + entity.idPromo + "' WHERE id_detail_promo='" + entity.idDetailPromo +"'",
                entity.diskon,
                entity.idKategori,
                entity.idPromo,
                entity.idDetailPromo);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeletePromo(int clickedRow, Promo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM promo WHERE id_promo='" + entity.idPromo + "'",
                entity.idPromo,
                entity.namaPromo,
                entity.startDate,
                entity.endDate,
                entity.idPromo);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteDetailPromo(int clickedRow, DetailPromo entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM detail_promo WHERE id_promo='" + entity.idPromo + "'",
                entity.idDetailPromo,
                entity.diskon,
                entity.idKategori,
                entity.idPromo);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<Promo> GetPromo() throws SQLException {
        ArrayList<Promo> promo = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM promo");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            promo.add(
                    new Promo(
                            rs.getString("id_promo"),
                            rs.getString("nama_promo"),
                            rs.getDate("start_date").toLocalDate(),
                            rs.getDate("end_date").toLocalDate()
                    )
            );
        }
        return promo;
    }

    public ArrayList<DetailPromo> GetDetailPromo() throws SQLException {
        ArrayList<DetailPromo> detailPromo = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM detail_promo");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            detailPromo.add(
                    new DetailPromo(
                            rs.getInt("id_detail_promo"),
                            rs.getFloat("diskon"),
                            rs.getString("id_kategori"),
                            rs.getString("id_promo")
                    )
            );
        }
        return detailPromo;
    }
}
