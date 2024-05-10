package repository;

import entity.Items;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemRepository {
    private Connection conn;
    public ItemRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertItem(Items entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO items(id_item, nama_item, harga, lama_penyelesaian, id_kategori) VALUES('%s', '%s','%d','%d','%s')",
                entity.idItem,
                entity.namaItem,
                entity.harga,
                entity.lamaPenyelesaian,
                entity.idKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateItem(int clickedRow, Items entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE items " + "SET nama_item='" + entity.namaItem + "', harga='" + entity.harga + "', lama_penyelesaian='" + entity.lamaPenyelesaian + "', id_kategori='" + entity.idKategori + "' WHERE id_item='" + entity.idItem + "'",
                entity.idItem,
                entity.namaItem,
                entity.harga,
                entity.lamaPenyelesaian,
                entity.idKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteItem(int clickedRow, Items entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM items WHERE id_item='" + entity.idItem + "'",
                entity.idItem,
                entity.namaItem,
                entity.harga,
                entity.lamaPenyelesaian,
                entity.idKategori);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<Items> GetItem() throws SQLException {
        ArrayList<Items> items = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM items");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            items.add(
                    new Items(
                            rs.getString("id_item"),
                            rs.getString("nama_item"),
                            rs.getInt("harga"),
                            rs.getInt("lama_penyelesaian"),
                            rs.getString("id_kategori")
                    )
            );
        }
        return items;
    }
}
