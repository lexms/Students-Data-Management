/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.table_models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import io.models.modelMatakuliah;

/**
 *
 * @author User
 */
public class tmMatakuliah extends AbstractTableModel{
    private ArrayList<modelMatakuliah> data;
    private String[] columnName = {"nomor_mk","nama_mk"};
    
    public void setData(ArrayList<modelMatakuliah> dt){
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
        modelMatakuliah mmatakuliah = data.get(rowIndex);
        switch(columnIndex){
            case 0 : return mmatakuliah.getNomor_mk();
            case 1 : return mmatakuliah.getNama_mk();
            default : return null;
        } 
    }
    
    public String getColumnName(int column){   
        return columnName[column];  
    }
}
