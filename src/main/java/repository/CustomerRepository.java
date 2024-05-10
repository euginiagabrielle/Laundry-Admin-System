package repository;

import entity.Customers;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerRepository {
    private Connection conn;
    public CustomerRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertCustomer(Customers entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO customers(id_customer, nama_customer, alamat, no_telp, id_radius) VALUES('%d', '%s','%s','%s','%d')",
                entity.id,
                entity.namaCustomer,
                entity.alamat,
                entity.noTelp,
                entity.idRadius);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateCustomer(int clickedRow, Customers entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE customers " + "SET nama_customer='" + entity.namaCustomer + "', alamat='" + entity.alamat + "', no_telp='" + entity.noTelp + "', id_radius='" + entity.idRadius + "' WHERE id_customer='" + entity.id + "'",
                        entity.namaCustomer,
                        entity.alamat,
                        entity.noTelp,
                        entity.idRadius,
                        entity.id);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteCustomer(int clickedRow, Customers entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM customers WHERE id_customer='" + entity.id + "'",
                entity.id,
                entity.namaCustomer,
                entity.alamat,
                entity.noTelp,
                entity.idRadius);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<Customers> GetCustomer() throws SQLException {
        ArrayList<Customers> customers = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM customers");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            customers.add(
                    new Customers(
                            rs.getInt("id_customer"),
                            rs.getString("nama_customer"),
                            rs.getString("alamat"),
                            rs.getString("no_telp"),
                            rs.getInt("id_radius")
                    )
            );
        }
        return customers;
    }
}
