/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author The Keong's
 */
public class Pegawai {
    private String idPegawai;
    private String password;
    private String nama;
    private String alamat;
    private String noHP;
    private long gaji;

    public Pegawai(String idPegawai, String password, String nama, String alamat, String noHP, long gaji) {
        this.idPegawai = idPegawai;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
        this.gaji = gaji;
    }
    
    

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public long getGaji() {
        return gaji;
    }

    public void setGaji(Long gaji) {
        this.gaji = gaji;
    }
    
}
