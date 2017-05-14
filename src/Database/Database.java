/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.Aplikasi;
import Model.Pegawai;
import Model.Proyek;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Keong's
 */
public class Database {
    private String dbUser = "root";
    private String dbPass = "";
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    
    public Database() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/sisfo", dbUser, dbPass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getData(String SQLString) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void query(String SQLString) throws SQLException {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(SQLString);
        } catch (SQLException c) {
            throw new SQLException("Error eksekusi query");
        }
    }
    
    public ArrayList<Pegawai> readPegawai(){
        ArrayList<Pegawai> dPegawai = new ArrayList();
        String s = "select id, password, nama, alamat, nohp, gaji from pegawai";
        ResultSet rs = getData(s);
        try {
            while(rs.next()){
                Pegawai p;
                p = new Pegawai(rs.getString("id"),rs.getString("password"),rs.getString("nama"),rs.getString("alamat"),rs.getString("nohp"),rs.getInt("gaji"));
                dPegawai.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dPegawai;
    }
    
    public ArrayList<Proyek> readProyek(){
        ArrayList<Proyek> dProyek = new ArrayList();
        String s = "select id, judul, fe, be, data, status, detail from proyek";
        ResultSet rs = getData(s);
        try {
            while(rs.next()){
                Proyek p;
                p = new Proyek(rs.getString("id"),rs.getString("judul"),rs.getString("detail"),rs.getString("status"),rs.getString("fe"),rs.getString("be"),rs.getString("data"));
                dProyek.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dProyek;
    }
    
    public void savePegawai(String idPegawai, String password, String nama, String alamat, String noHP, long gaji){
        String s = "INSERT INTO `pegawai` (`id`, `password`, `nama`, `alamat`, `nohp`, `gaji`) VALUES ('"
                +idPegawai+"', '"+password+"', '"+nama+"', '"+alamat+"', '"+noHP+"', '"+gaji+"');";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveProyek(String idProyek, String judulProyek, String detail, String status, String frontEnd, String backEnd, String database){
        String s = "INSERT INTO `proyek` (`id`, `judul`, `fe`, `be`, `data`, `status`, `detail`) VALUES ('"+
                idProyek+"', '"+judulProyek+"', '"+frontEnd+"', '"+backEnd+"', '"+database+"', '"+status+"', '"+detail+"');";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updatePegawai(String idPegawai, String nama, String alamat, String noHP, long gaji){
        String s = "UPDATE pegawai SET nama = '"+nama+"', password = '"+noHP+"',alamat = '"+alamat+
                "' ,nohp = '"+noHP+"',gaji= "+gaji+" WHERE id = '"+idPegawai+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateProyek(String idProyek, String judulProyek, String detail, String status, String frontEnd, String backEnd, String database){
        String s = "update proyek set judul = '"+judulProyek+"', fe = '"+frontEnd+"', be = '"+backEnd+
                "', data = '"+database+"', status = '"+status+"', detail = '"+detail+"' where id = '"+idProyek+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removePegawai(String id){
        String s = "delete from pegawai where id = '"+id+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeProyek(String id){
        String s = "delete from proyek where id = '"+id+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void pilihKerja(int i,String id,String nama){
        String s="";
        if (i==1){
            s = "update proyek set fe = '"+nama+"' where id ='"+id+"';";
        }else if (i==2){
            s = "update proyek set be = '"+nama+"' where id ='"+id+"';";
        }else if(i==3){
            s = "update proyek set data = '"+nama+"' where id ='"+id+"';";
        }
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateStatus(String id,String status){
        String s = "update proyek set status = '"+status+"' where id ='"+id+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
