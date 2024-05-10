package repository;

import entity.MetodePembayaran;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MetodePembayaranRepository {
    private Connection conn;
    public MetodePembayaranRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertMetode(MetodePembayaran entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO metode_pembayaran(id_metode, nama_metode) VALUES('%s', '%s')",
                entity.idMetode,
                entity.namaMetode);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateMetode(int clickedRow, MetodePembayaran entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE metode_pembayaran " + "SET nama_metode='" + entity.namaMetode + "' WHERE id_metode='" + entity.idMetode + "'",
                entity.idMetode,
                entity.namaMetode);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteMetode(int clickedRow, MetodePembayaran entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM metode_pembayaran WHERE id_metode='" + entity.idMetode + "'",
                entity.idMetode,
                entity.namaMetode);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<MetodePembayaran> GetMetode() throws SQLException {
        ArrayList<MetodePembayaran> metode = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM metode_pembayaran");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            metode.add(
                    new MetodePembayaran(
                            rs.getString("id_metode"),
                            rs.getString("nama_metode")
                    )
            );
        }
        return metode;
    }
}
