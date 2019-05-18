/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.table_models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import io.models.modelMahasiswa;
/**
 *
 * @author User
 */
public class tmMahasiswa extends AbstractTableModel {
    private ArrayList<modelMahasiswa> data;
    private String[] columnName = {"nim","nama","tempat_lahir","tanggal_lahir","alamat"};
    
    public void setData(ArrayList<modelMahasiswa> dt){
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
        modelMahasiswa mmahasiswa = data.get(rowIndex);
        switch(columnIndex){
            case 0 : return mmahasiswa.getNim();
            case 1 : return mmahasiswa.getNama();
            case 2 : return mmahasiswa.getTempat_lahir();
            case 3 : return mmahasiswa.getTanggal_lahir();
            case 4 : return mmahasiswa.getAlamat();
            default : return null;
        } 
    }
    
    public String getColumnName(int column){   
        return columnName[column];  
    }
}
