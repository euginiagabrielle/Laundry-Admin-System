package repository;

import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportRepository {
    private Connection conn;
    public ReportRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public ArrayList<String> GetTransaksiTerbanyak() throws SQLException {
        ArrayList<String> customer = new ArrayList<>();
        Statement stmt = connection.GetConnection().createStatement();
        String sql = "SELECT nama_customer \n" +
                "FROM customers c \n" +
                "JOIN transaksi t \n" +
                "ON c.id_customer = t.id_customer \n" +
                "GROUP BY nama_customer\n" +
                "ORDER BY COUNT(t.id_customer) DESC LIMIT 3";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            customer.add(rs.getString("nama_customer"));
        }
        return customer;
    }

    public ArrayList<String> GetItemTerbanyak() throws SQLException {
        ArrayList<String> item = new ArrayList<>();
        Statement stmt = connection.GetConnection().createStatement();
        String sql = "SELECT nama_item \n" +
                "FROM items i \n" +
                "JOIN detail_transaksi dt\n" +
                "ON i.id_item  = dt .id_item  \n" +
                "GROUP BY nama_item\n" +
                "ORDER BY COUNT(dt.id_item) DESC LIMIT 3";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            item.add(rs.getString("nama_item"));
        }
        return item;
    }

    public ArrayList<String> GetLayananTerbanyak() throws SQLException {
        ArrayList<String> item = new ArrayList<>();
        Statement stmt = connection.GetConnection().createStatement();
        String sql = "SELECT jenis_layanan\n" +
                "FROM detail_transaksi\n" +
                "GROUP BY jenis_layanan\n" +
                "ORDER BY COUNT(jenis_layanan) DESC LIMIT 1;";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            item.add(rs.getString("jenis_layanan"));
        }
        return item;
    }

}
