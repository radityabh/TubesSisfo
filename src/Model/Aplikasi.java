/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author The Keong's
 */
public class Aplikasi {
    ArrayList<Pegawai> tPegawai;
    ArrayList<Proyek> tProyek;
    Database D;
    public Aplikasi(){
        tPegawai = new ArrayList();
        tProyek = new ArrayList();
        D = new Database();
        tPegawai = D.readPegawai();
        tProyek = D.readProyek();
    }
    /*--------------------------- HRD -------------------- */
    public String insertPegawai(String nama, String alamat, String noHp, long gaji){
        String id = "P-"+(tPegawai.size()+1);
        Pegawai peg = new Pegawai(id, noHp, nama, alamat, noHp, gaji);
        this.tPegawai.add(peg);
        D.savePegawai(id, noHp, nama, alamat, noHp, gaji);
        return "ID : "+id+"\nPass : "+noHp;
    }
    
    public void removePegawai(Pegawai p){
        D.removePegawai(p.getIdPegawai());
        tPegawai.remove(p);
    }
    
    public void updatePegawai(Pegawai p){
        D.updatePegawai(p.getIdPegawai(), p.getNama(), p.getAlamat(), p.getNoHP(), p.getGaji());
    }
    
    public Pegawai cariPegawai1(String id){
        for(int i=0;i<tPegawai.size();i++){
            if(tPegawai.get(i).getIdPegawai().equals(id)){
                return tPegawai.get(i);
            }
        }return null;
    }
    
    public Pegawai cariPegawai2(String id,String pass){
        for(int i=0;i<tPegawai.size();i++){
            if(tPegawai.get(i).getIdPegawai().equals(id) && tPegawai.get(i).getPassword().equals(pass)){
                return tPegawai.get(i);
            }
        }return null;
    }
    
    public Pegawai cariPegawai3(int i){
        return tPegawai.get(i);
    }
    
    public String[][] getListOutPegawai(){
        String out[][] = new String[tPegawai.size()][5];
        for (int i = 0;i < tPegawai.size();i++){
                out[i][0] = tPegawai.get(i).getIdPegawai();
                out[i][1] = tPegawai.get(i).getNama();
                out[i][2] = tPegawai.get(i).getAlamat();
                out[i][3] = tPegawai.get(i).getNoHP();
                out[i][4] = Long.toString(tPegawai.get(i).getGaji());
            }
        return out;
    }
    
    public String[][] getListOutPegawai2(Pegawai p){
        String out[][] = new String[1][5];
        out[0][0] = p.getIdPegawai();
        out[0][1] = p.getNama();
        out[0][2] = p.getAlamat();
        out[0][3] = p.getNoHP();
        out[0][4] = Long.toString(p.getGaji());
        return out;
    }
    
    /*--------------------------- Direktur -------------------- */
    
    public void insertProyek(String Judul,String Detail){
        String id = "PR-"+(tProyek.size()+1);
        Proyek y = new Proyek(id,Judul,Detail,"-","-","-","-");
        D.saveProyek(id, Judul, Detail, "-", "-", "-", "-");
        tProyek.add(y);
    }
    public void removeProyek(Proyek p){
        D.removeProyek(p.getIdProyek());
        tProyek.remove(p);
    }
    public void updateProyek(Proyek p){
        D.updateProyek(p.getIdProyek(), p.getJudulProyek(), p.getDetail(), p.getStatus(), p.getFrontEnd(), p.getBackEnd(), p.getDatabase());
    }
    public Proyek cariProyek1(String id){
        for(int i=0;i<tProyek.size();i++){
            if (tProyek.get(i).getIdProyek().equals(id)){
                return tProyek.get(i);
            }
        }return null;
        
    }
    public Proyek cariProyek2(int i){
        return tProyek.get(i);
    }
    public String[][] getListOutProyek(){
        String out[][] = new String[tProyek.size()][6];
        for (int i = 0;i < tProyek.size();i++){
                out[i][0] = tProyek.get(i).getIdProyek();
                out[i][1] = tProyek.get(i).getJudulProyek();
                out[i][2] = tProyek.get(i).getFrontEnd();
                out[i][3] = tProyek.get(i).getBackEnd();
                out[i][4] = tProyek.get(i).getDatabase();
                out[i][5] = tProyek.get(i).getStatus();
            }
        return out;
    }
    
    public String[][] getListOutProyek2(Proyek p){
        String out[][] = new String[1][6];
        out[0][0] = p.getIdProyek();
        out[0][1] = p.getJudulProyek();
        out[0][2] = p.getFrontEnd();
        out[0][3] = p.getBackEnd();
        out[0][4] = p.getDatabase();
        out[0][5] = p.getStatus();
        return out;
    }
    
    /*--------------------------- Pegawai -------------------- */
    public String[][] getListOutProyek3(){
        String out[][] = new String[tProyek.size()][5];
        for (int i = 0;i < tProyek.size();i++){
            if (tProyek.get(i).getStatus().equals("-")){
                out[i][0] = tProyek.get(i).getIdProyek();
                out[i][1] = tProyek.get(i).getJudulProyek();
                out[i][2] = tProyek.get(i).getFrontEnd();
                out[i][3] = tProyek.get(i).getBackEnd();
                out[i][4] = tProyek.get(i).getDatabase();
            }
        }
        return out;
    }
    public String[][] getListOutProyek4(Pegawai p){
        String out[][] = new String[tProyek.size()][6];
        for (int i = 0;i < tProyek.size();i++){
            if (tProyek.get(i).getFrontEnd().equals(p.getNama())||tProyek.get(i).getBackEnd().equals(p.getNama())||tProyek.get(i).getDatabase().equals(p.getNama())){
                out[i][0] = tProyek.get(i).getIdProyek();
                out[i][1] = tProyek.get(i).getJudulProyek();
                out[i][2] = tProyek.get(i).getFrontEnd();
                out[i][3] = tProyek.get(i).getBackEnd();
                out[i][4] = tProyek.get(i).getDatabase();
                out[i][5] = tProyek.get(i).getStatus();
            }
        }
        return out;
    }
    
    public String pilihKerja(int i,Proyek P,Pegawai pg){
        String s ="";
        if (i==1 && !P.getFrontEnd().equals("-")){
            s = "Front end sudah terisi";
        } else if(i==2 && !P.getBackEnd().equals("-")){
            s = "Back end sudah terisi";
        } else if (i==3 && !P.getDatabase().equals("-")){
            s = "Database sudah terisi";
        } else if(i==1){
            D.pilihKerja(i, P.getIdProyek(), pg.getNama());
            P.setFrontEnd(pg.getNama());
            s = "Anda terdaftar sebagai Front end";
        } else if(i==2){
            D.pilihKerja(i, P.getIdProyek(), pg.getNama());
            P.setBackEnd(pg.getNama());
            s = "Anda terdaftar sebagai Back end";
        } else if(i==3){
            D.pilihKerja(i, P.getIdProyek(), pg.getNama());
            P.setDatabase(pg.getNama());
            s = "Anda terdaftar sebagai Database";
        }
        return s;
    }
    public String selesai(Proyek P){
       if(P.getStatus().equals("DI KERJAKAN")){
           D.updateStatus(P.getIdProyek(), "SELESAI");
           P.setStatus("SELESAI");
           return "Selesai";
        }else if (P.getStatus().equals("SELESAI")){
                return "Pekerjaan sudah selesai sebelumnya";
        }
        return "Proyek belum Dikerjakan";
    }
    public void Dikerjakan(Proyek p){
        if(!p.getBackEnd().equals("-")&& !p.getDatabase().equals("-") && !p.getFrontEnd().equals("-")){
            p.setStatus("DI KERJAKAN");
            D.updateStatus(p.getIdProyek(), p.getStatus());
        }
    }
}
