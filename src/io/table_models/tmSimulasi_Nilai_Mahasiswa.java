/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.table_models;

import io.models.modelSimulasi_Nilai_Mahasiswa;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author User
 */
public class tmSimulasi_Nilai_Mahasiswa extends AbstractTableModel{
    private ArrayList<modelSimulasi_Nilai_Mahasiswa> data;
    private String[] columnName = {"id",
        "Nomor MK",
        "Nama MK",
        
        "persen_absen",
        "persen_tugas",
        "persen_uts",
        "persen_uas",
        
        "Absensi",
        "Tugas1",
        "Tugas2",
        "Tugas3",
        "UTS",
        "UAS",
        "Nilai Absen",
        "Nilai Tugas",
        "Nilai UTS",
        "Nilai UAS",
        "Nilai Akhir",
        "Indeks",
        "Keterangan"};

    public void setData(ArrayList<modelSimulasi_Nilai_Mahasiswa> dt){
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
        modelSimulasi_Nilai_Mahasiswa msimulais_nilai_mahasiswa = data.get(rowIndex);
        switch(columnIndex){
            case 0 : return msimulais_nilai_mahasiswa.getId_simulasi_nilai_mahasiswa();
            case 1 : return msimulais_nilai_mahasiswa.getTnilai_nomor_mk();
            case 2 : return msimulais_nilai_mahasiswa.getTnilai_nama_mk();
            case 3 : return msimulais_nilai_mahasiswa.getPersen_absen();
            case 4 : return msimulais_nilai_mahasiswa.getPersen_tugas();
            case 5 : return msimulais_nilai_mahasiswa.getPersen_uts();
            case 6 : return msimulais_nilai_mahasiswa.getPersen_uas();
            case 7 : return msimulais_nilai_mahasiswa.getKehadiran();
            case 8 : return msimulais_nilai_mahasiswa.getNilai_tugas1();
            case 9 : return msimulais_nilai_mahasiswa.getNilai_tugas2();
            case 10 : return msimulais_nilai_mahasiswa.getNilai_tugas3();
            case 11 : return msimulais_nilai_mahasiswa.getNilai_uts();
            case 12 : return msimulais_nilai_mahasiswa.getNilai_uas();
            case 13 : return msimulais_nilai_mahasiswa.getTotal_nilai_absen();
            case 14 : return msimulais_nilai_mahasiswa.getTotal_nilai_tugas();
            case 15 : return msimulais_nilai_mahasiswa.getTotal_nilai_uts();
            case 16 : return msimulais_nilai_mahasiswa.getTotal_nilai_uas();
            case 17 : return msimulais_nilai_mahasiswa.getTotal_nilai_akhir();
            case 18 : return msimulais_nilai_mahasiswa.getIndeks();
            case 19 : return msimulais_nilai_mahasiswa.getKeterangan();
            default : return null;
        } 
    }
    
    public String getColumnName(int column){   
        return columnName[column];  
    }
}
