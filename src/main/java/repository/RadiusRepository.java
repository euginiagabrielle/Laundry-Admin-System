package repository;

import entity.Radius;
import helper.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RadiusRepository {
    private Connection conn;
    public RadiusRepository() throws SQLException {
        conn = connection.GetConnection();
    }

    public boolean InsertRadius(Radius entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO radius(id_radius, min_radius, max_radius, harga) VALUES('%d','%d','%d','%d')",
                entity.idRadius,
                entity.minRadius,
                entity.maxRadius,
                entity.harga);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateRadius(int clickedRow, Radius entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE radius SET min_radius='" + entity.minRadius + "', max_radius='" + entity.maxRadius + "', harga='" + entity.harga + "'" + "WHERE id_radius='" + entity.idRadius + "'",
                entity.minRadius,
                entity.maxRadius,
                entity.harga,
                entity.idRadius);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean DeleteRadius(int clickedRow, Radius entity) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = String.format("DELETE FROM radius WHERE id_radius='" + entity.idRadius + "'",
                entity.idRadius,
                entity.minRadius,
                entity.maxRadius,
                entity.harga);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public ArrayList<Radius> GetRadius() throws SQLException {
        ArrayList<Radius> radius = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM radius");
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            radius.add(
                    new Radius(
                            rs.getInt("id_radius"),
                            rs.getInt("min_radius"),
                            rs.getInt("max_radius"),
                            rs.getInt("harga")
                    )
            );
        }
        return radius;
    }
}
