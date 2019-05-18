/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.table_models;

import io.models.modelNilai_Mahasiswa;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author User
 */
public class tmNilai_Mahasiswa extends AbstractTableModel{
    private ArrayList<modelNilai_Mahasiswa> data;
    private String[] columnName = {"id","NIM","Nama","Nomor MK","Nama MK","Absensi",
    "Tugas1","Tugas2","Tugas3","UTS","UAS","Tahun Angkatan",
    "Nilai Absen","Nilai Tugas","Nilai UTS","Nilai UAS","Nilai Akhir","Indeks",
    "Keterangan"};
    
    public void setData(ArrayList<modelNilai_Mahasiswa> dt){
        this.data= dt;
    }
    
    @Override
    public int getRowCount() {
        return data.size();  
    }

    @Override
    public int getColumnCount() {
        return columnName.length; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        modelNilai_Mahasiswa mnilai_mahasiswa = data.get(rowIndex);
        switch(columnIndex){
            case 0 : return mnilai_mahasiswa.getId_nilai_mahasiswa();
            case 1 : return mnilai_mahasiswa.getTnilai_nim();
            case 2 : return mnilai_mahasiswa.getTnilai_nama();
            case 3 : return mnilai_mahasiswa.getTnilai_nomor_mk();
            case 4 : return mnilai_mahasiswa.getTnilai_nama_mk();
            case 5 : return mnilai_mahasiswa.getKehadiran();
            case 6 : return mnilai_mahasiswa.getNilai_tugas1();
            case 7 : return mnilai_mahasiswa.getNilai_tugas2();
            case 8 : return mnilai_mahasiswa.getNilai_tugas3();
            case 9 : return mnilai_mahasiswa.getNilai_uts();
            case 10 : return mnilai_mahasiswa.getNilai_uas();
            case 11 : return mnilai_mahasiswa.getTahun_angkatan();
            case 12 : return mnilai_mahasiswa.getTotal_nilai_absen();
            case 13 : return mnilai_mahasiswa.getTotal_nilai_tugas();
            case 14 : return mnilai_mahasiswa.getTotal_nilai_uts();
            case 15 : return mnilai_mahasiswa.getTotal_nilai_uas();
            case 16 : return mnilai_mahasiswa.getTotal_nilai_akhir();
            case 17 : return mnilai_mahasiswa.getIndeks();
            case 18 : return mnilai_mahasiswa.getKeterangan();
            default : return null;
        } 
    }
    
    public String getColumnName(int column){   
        return columnName[column];  
    }
}
