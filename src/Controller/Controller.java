/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Aplikasi;
import Model.Pegawai;
import Model.Proyek;
import View.Direktur;
import View.EditPegawai;
import View.EditProyek;
import View.HRD;
import View.Login;
import View.lihatProyek;
import View.lihatProyek2;
import View.panelContainer;
import View.vPegawai;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author The Keong's
 */
public class Controller extends MouseAdapter implements ActionListener{
    private Aplikasi model;
    private panelContainer view;
    
    private String currentView;
    private JPanel mainPanel;
    private boolean posisi;
    private Pegawai tmpPeg;
    private Pegawai tmpPeg2;
    private Proyek tmpPro;
    private Proyek tmpPro2;
    private Proyek tmpPro3;
    
    private Login L;
    private HRD H;
    private EditPegawai EP;
    private Direktur D;
    private vPegawai Peg;
    private lihatProyek LP;
    private EditProyek Epr;
    private lihatProyek2 LP2;
    
    public Controller(Aplikasi model){
        this.model = model;
        this.view = new panelContainer();
        
        L = new Login();
        H = new HRD();
        EP = new EditPegawai();
        Peg = new vPegawai();
        D = new Direktur();
        LP = new lihatProyek();
        Epr = new EditProyek();
        LP2 = new lihatProyek2();
        
        L.addListener(this);
        H.addListener(this);
        EP.addListener(this);
        D.addListener(this);
        LP.addListener(this);
        LP2.addListener(this);
        Epr.addListener(this);
        Peg.addListener(this);
        H.addAdapter(this);
        D.addAdapter(this);
        Peg.addAdapter(this);
        
        mainPanel = view.getMainPanel();
        mainPanel.add(L,"0");
        mainPanel.add(H,"1");
        mainPanel.add(EP,"2");
        mainPanel.add(D,"3");
        mainPanel.add(LP,"4");
        mainPanel.add(Epr,"5");
        mainPanel.add(Peg,"6");
        mainPanel.add(LP2,"7");
        currentView = "0";
        
        view.getCardLayout().show(mainPanel, currentView);
        view.setVisible(true);

        
    }
    
       @Override
     public void mousePressed(MouseEvent e){
        Object source = e.getSource();
        if(source.equals(H.getTPegawai()) && H.getSelectPegawai()>=0 && !H.getSelectPegawai2().equals("P-1")){
            tmpPeg = model.cariPegawai1(H.getSelectPegawai2());
        } else if(source.equals(H.getTPegawai()) && H.getSelectPegawai()>=0){
            tmpPeg = model.cariPegawai3(H.getSelectPegawai());
        }else
            tmpPeg = null;
        if(source.equals(D.getTProyek()) && D.getSelectProyek()>=0 && !D.getSelectProyek2().equals("PR-1")){
            tmpPro = model.cariProyek1(D.getSelectProyek2());
        } else if(source.equals(D.getTProyek()) && D.getSelectProyek()>=0){
            tmpPro= model.cariProyek2(D.getSelectProyek());
        } else
            tmpPro= null;
        if(source.equals(Peg.getTProTer())&& Peg.getSelectTPrOter()>=0){
            tmpPro2 = model.cariProyek1(Peg.getSelectTPrOter2());
        } else
            tmpPro2 = null;
        if(source.equals(Peg.getKerja())&& Peg.getSelectKerja()>=0){
            tmpPro3 = model.cariProyek1(Peg.getSelectKerja2());
        }else
            tmpPro3 = null;
     }

    @Override
    public void actionPerformed(ActionEvent ae) {
       Object source = ae.getSource();
       if (currentView.equals("0")){
           if(source.equals(L.btnMasuk())){
               if (L.getIdLogin().equals("")||L.getPassLogin().equals("")){
                   JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE); 
               } else if (L.getIdLogin().equals("hrd") && L.getPassLogin().equals("hrd")){
                   currentView="1";
                   H.refresh();
                   view.getCardLayout().show(mainPanel, currentView);
               } else if (L.getIdLogin().equals("direktur") && L.getPassLogin().equals("direktur")){
                   currentView="3";
                   view.getCardLayout().show(mainPanel, currentView);
               } else if (model.cariPegawai2(L.getIdLogin(), L.getPassLogin())!=null){
                   currentView="6";
                   Peg.setListProTer(model.getListOutProyek3());
                   tmpPeg2 = model.cariPegawai2(L.getIdLogin(), L.getPassLogin());
                   Peg.setListKerja(model.getListOutProyek4(tmpPeg2));
                   view.getCardLayout().show(mainPanel, currentView);
               } else
                    JOptionPane.showMessageDialog(null, "User tidak ada atau username dan pass salah", "Peringatan", JOptionPane.ERROR_MESSAGE);
               
           }
           
       }else if (currentView.equals("1")){
           String s="";
           if(source.equals(H.getSubmit())){
               if (H.getNama().equals("")||H.getAlamat().equals("")||H.getNohp().equals("")){
                   JOptionPane.showMessageDialog(null, "Inputan tidak boleh ada yang kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   s = model.insertPegawai(H.getNama(), H.getAlamat(), H.getNohp(), H.getGaji());
                   H.refresh();
                   JOptionPane.showMessageDialog(null, "Pendaftaran Berhasil \n"+s);
           } else if(source.equals(H.getBtnAll())){
               H.setListPegawai(model.getListOutPegawai());
           } else if(source.equals(H.getBtnCari())){
               if(H.getSrcNama().equals("")){
                   JOptionPane.showMessageDialog(null, "Inputan tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else if (model.cariPegawai1(H.getSrcNama())==null){
                   JOptionPane.showMessageDialog(null, "Data Dengan ID Pegawai "+H.getSrcNama()+" Tidak ditemukan", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else{
                   tmpPeg=model.cariPegawai1(H.getSrcNama());
                   H.setListPegawai(model.getListOutPegawai2(tmpPeg));
               }
           } else if(source.equals(H.getBtnEdit())){
               if(tmpPeg==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Ubah", "Peringatan", JOptionPane.ERROR_MESSAGE);
               } else
                  currentView="2";
                  EP.setInpNama(tmpPeg.getNama());
                  EP.setInpAlamat(tmpPeg.getAlamat());
                  EP.setInpGaji(tmpPeg.getGaji());
                  EP.setInpNohp(tmpPeg.getNoHP());
                  view.getCardLayout().show(mainPanel, currentView);
           } else if(source.equals(H.getBtnDelete())){
               if(tmpPeg==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Hapus", "Peringatan", JOptionPane.ERROR_MESSAGE);
               } else
                   JOptionPane.showMessageDialog(null, "Data Dengan Nama "+tmpPeg.getNama()+" Berhasil di Hapus");
                   model.removePegawai(tmpPeg);
                   H.setListPegawai(model.getListOutPegawai());
           } else if(source.equals(H.LoPress())){
               currentView="0";
               L.refresh();
               view.getCardLayout().show(mainPanel, currentView);
           }
       }else if (currentView.equals("2")){
           if(source.equals(EP.getBack())){
               currentView="1";
               H.refresh();
               H.setListPegawai(model.getListOutPegawai());
               view.getCardLayout().show(mainPanel, currentView);
           }else if(source.equals(EP.getSubmit())){
               tmpPeg.setAlamat(EP.getAlamat());
               tmpPeg.setGaji(EP.getGaji());
               tmpPeg.setNama(EP.getNama());
               tmpPeg.setNoHP(EP.getNohp());
               model.updatePegawai(tmpPeg);
               JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil di Sunting");
               EP.refresh();
           }
       }else if (currentView.equals("3")){
           if(source.equals(D.getBtnSubmit())){
               if (D.getJudul().equals("")||D.getDetail().equals("")){
                   JOptionPane.showMessageDialog(null, "Inputan tidak boleh ada yang kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   model.insertProyek(D.getJudul(), D.getDetail());
                   JOptionPane.showMessageDialog(null, "Proyek dengan judul "+D.getJudul() +" Berhasil diTambahkan");
                   D.refresh();
           }else if(source.equals(D.getBtnAll())){
               D.setListProyek(model.getListOutProyek());
           }else if(source.equals(D.getBtnCari())){
               if(D.getSrcNama().equals("")){
                   JOptionPane.showMessageDialog(null, "Inputan tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else if (model.cariProyek1(D.getSrcNama())==null){
                   JOptionPane.showMessageDialog(null, "Data Dengan ID Pegawai "+D.getSrcNama()+" Tidak ditemukan", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else{
                   tmpPro = model.cariProyek1(D.getSrcNama());
                   D.setListProyek(model.getListOutProyek2(tmpPro));
               }
           }else if(source.equals(D.getBtnLihat())){
               if(tmpPro==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Lihat", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else{
                   currentView="4";
                   LP.setJudul(tmpPro.getJudulProyek());
                   LP.setDetail(tmpPro.getDetail());
                   view.getCardLayout().show(mainPanel, currentView);
               }
           } else if(source.equals(D.getBtnEdit())){
               if(tmpPro==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Ubah", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   currentView="5";
                   Epr.setEditBE(tmpPro.getBackEnd());
                   Epr.setEditData(tmpPro.getDatabase());
                   Epr.setEditDetail(tmpPro.getDetail());
                   Epr.setEditFE(tmpPro.getFrontEnd());
                   Epr.setEditJudul(tmpPro.getJudulProyek());
                   Epr.setEditStatus(tmpPro.getStatus());
                   model.updateProyek(tmpPro);
                   view.getCardLayout().show(mainPanel, currentView);
           }else if(source.equals(D.getBtnDelete())){
               if(tmpPro==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Hapus", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   model.removeProyek(tmpPro);
                   JOptionPane.showMessageDialog(null, "Proyek Dengan Judul "+tmpPro.getJudulProyek()+" Berhasil di Hapus");
                   D.setListProyek(model.getListOutProyek());
           }else if(source.equals(D.LoPress())){
               currentView="0";
               L.refresh();
               view.getCardLayout().show(mainPanel, currentView);
           }
       }else if (currentView.equals("4")){
           if(source.equals(LP.getbtnBack())){
               currentView="3";
               D.refresh();
               view.getCardLayout().show(mainPanel, currentView);
           }
       }else if(currentView.equals("5")){
           if(source.equals(Epr.getBack())){
               currentView="3";
               D.refresh();
               D.setListProyek(model.getListOutProyek());
               view.getCardLayout().show(mainPanel, currentView);
           } else if(source.equals(Epr.getSubmit())){
               tmpPro.setBackEnd(Epr.getEditBE());
               tmpPro.setDatabase(Epr.getEditData());
               tmpPro.setDetail(Epr.getEditDetail());
               tmpPro.setFrontEnd(Epr.getEditFE());
               tmpPro.setJudulProyek(Epr.getEditJudul());
               tmpPro.setStatus(Epr.getEditStatus());
               model.updateProyek(tmpPro);
               JOptionPane.showMessageDialog(null, "Data Proyek Berhasil di Sunting");
               Epr.refresh();
           }
       }else if(currentView.equals("6")){
           String s="";
           if(source.equals(Peg.LoPress())){
               currentView="0";
               L.refresh();
               Peg.refresh();
               view.getCardLayout().show(mainPanel, currentView);
           } else if (source.equals(Peg.getAmbil())){
               if(tmpPro2==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Lihat", "Peringatan", JOptionPane.ERROR_MESSAGE);
               } else {
                   s=model.pilihKerja(Peg.getPilihan(), tmpPro2, tmpPeg2);
                   Peg.setListProTer(model.getListOutProyek3());
                   model.Dikerjakan(tmpPro2);
                   Peg.setListKerja(model.getListOutProyek4(tmpPeg2));
                   JOptionPane.showMessageDialog(null, s);
                   Peg.refresh();
               }
           } else if (source.equals(Peg.getLihat())){
               if(tmpPro2==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Lihat", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   currentView="7";
                   LP2.setJudul(tmpPro2.getJudulProyek());
                   LP2.setDetail(tmpPro2.getDetail());
                   view.getCardLayout().show(mainPanel, currentView);
           }else if (source.equals(Peg.getbtnLihat2())){
               if(tmpPro3==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Lihat", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   currentView="7";
                   LP2.setJudul(tmpPro3.getJudulProyek());
                   LP2.setDetail(tmpPro3.getDetail());
                   view.getCardLayout().show(mainPanel, currentView);
           }else if(source.equals(Peg.getSelesai())){
               if(tmpPro3==null){
                   JOptionPane.showMessageDialog(null, "Pilih Data Yang akan di Lihat", "Peringatan", JOptionPane.ERROR_MESSAGE);
               }else
                   s=model.selesai(tmpPro3);
                   Peg.setListKerja(model.getListOutProyek4(tmpPeg2));
                   Peg.setListProTer(model.getListOutProyek3());
                   JOptionPane.showMessageDialog(null, s);
           }
       }else if(currentView.equals("7")){
           if(source.equals(LP2.getbtnBack())){
               currentView="6";
                   Peg.setListProTer(model.getListOutProyek3());
                   tmpPeg2 = model.cariPegawai2(L.getIdLogin(), L.getPassLogin());
                   Peg.setListKerja(model.getListOutProyek4(tmpPeg2));
                   view.getCardLayout().show(mainPanel, currentView);
           }
       }
    }
}
