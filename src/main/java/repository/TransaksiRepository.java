package repository;

import entity.DetailTransaksi;
import entity.Transaksi;
import helper.connection;
import javafx.scene.control.Label;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransaksiRepository {
    private Connection conn;

    public TransaksiRepository() throws SQLException {
        conn = connection.GetConnection();
    }
    public boolean InsertTransaksi(Transaksi entity, Label label) throws SQLException {
        CallableStatement cStmt = conn.prepareCall("{CALL transaksi_insert(?, ?, ?, ?, ?, ?, ?, ?)}");
        cStmt.setString(1,entity.tglTransaksi.toString());
        cStmt.setString(2, entity.namaCustomer);
        cStmt.setInt(3, entity.ongkirPickup);
        cStmt.setInt(4, entity.ongkirDeliver);
        cStmt.setString(5, entity.namaDriver);
        cStmt.setString(6, entity.idMetode);
        cStmt.setInt(7, entity.DP);
        cStmt.registerOutParameter(8, Types.VARCHAR);
        int success = cStmt.executeUpdate();

        String nomor = cStmt.getString(8);
        label.setText(nomor);
        return success > 0;
    }

    public boolean InsertDetail(DetailTransaksi entity) throws SQLException {
        CallableStatement cStmt = conn.prepareCall("{CALL detail_transaksi_insert(?, ?, ?, ?, ?)}");
        cStmt.setString(1,entity.kondisiItem);
        cStmt.setString(2, entity.jenisLayanan);
        cStmt.setString(3, entity.tanggalSelesai.toString());
        cStmt.setString(4, entity.idTransaksi);
        cStmt.setString(5, entity.idItem);
        int success = cStmt.executeUpdate();
        return success > 0;
    }

    public String GetPromoId(LocalDate tgl, String itemId) throws SQLException{
        CallableStatement cStmt = conn.prepareCall("{CALL get_promo(?, ?, ?)}");
        cStmt.setString(1, tgl.toString());
        cStmt.setString(2, itemId);
        cStmt.registerOutParameter(3, Types.VARCHAR);
        cStmt.executeUpdate();
        return  cStmt.getString(3);
    }

    public float GetDiskon(String promoId) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT diskon\n" +
                "FROM detail_promo dp\n" +
                "JOIN promo p ON dp.id_promo=p.id_promo\n" +
                "WHERE p.id_promo='" + promoId + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getFloat(1);
        } else {
            return 0;
        }
    }

    public boolean UpdateTransaksi(String idPromo, String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE transaksi SET id_promo = '%s' WHERE id_transaksi='%s'",
                idPromo,
                idTransaksi);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdatDetail(float diskon, String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE detail_transaksi SET diskon = %f WHERE id_transaksi='%s'",
                diskon,
                idTransaksi);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public boolean UpdateLunas(int pelunasan, String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE transaksi SET pelunasan = %d WHERE id_transaksi='%s'",
                pelunasan,
                idTransaksi);
        System.out.println(sql);
        return (stmt.executeUpdate(sql)) > 0;
    }

    public int GetTotalHarga(String idItem, String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT DISTINCT harga*diskon \n" +
                "FROM items i\n" +
                "JOIN detail_transaksi dt \n" +
                "ON i.id_item = dt.id_item\n" +
                "WHERE dt.id_item = '" + idItem +"' and dt.id_transaksi = '" + idTransaksi + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {;
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public int GetOngkirP(String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT ongkir_pickup FROM transaksi  WHERE id_transaksi ='" + idTransaksi + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {;
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public int GetOngkirA(String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT ongkir_deliver FROM transaksi  WHERE id_transaksi ='" + idTransaksi + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {;
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public int GetDP(String idTransaksi) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT DP FROM transaksi  WHERE id_transaksi ='" + idTransaksi + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {;
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public ArrayList<String> GetIdTransaksi() throws SQLException {
        ArrayList<String> transaksi = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT id_transaksi FROM transaksi ORDER BY id_transaksi ASC";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            transaksi.add(rs.getString("id_transaksi"));
        }
        return transaksi;
    }

    public String GetIdItem(String idTransaksi) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT id_item \n" +
                "FROM detail_transaksi \n" +
                "WHERE id_transaksi = '" + idTransaksi + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            ;
            return rs.getString(1);
        } else {
            return null;
        }
    }


}
