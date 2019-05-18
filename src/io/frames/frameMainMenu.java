/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.frames;
import io.models.*;
import io.databases.*;
import io.services.database;
import io.services.exportPDF;
import io.table_models.*;
import io.services.navigation;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author AlexanderMS
 */
public class frameMainMenu extends javax.swing.JFrame {
    public int Option = 0;
    
    public databaseMahasiswa dbMahasiswa = new databaseMahasiswa();
    public tmMahasiswa tableMahasiswa = new tmMahasiswa();
    
    public databaseMatakuliah dbMatakuliah = new databaseMatakuliah();
    public tmMatakuliah tableMatakuliah = new tmMatakuliah();
    
    public databaseNilai_Mahasiswa dbNilai_Mahasiswa = new databaseNilai_Mahasiswa();
    public tmNilai_Mahasiswa tableNilai_Mahasiswa = new tmNilai_Mahasiswa();
    

    /**
     * Creates new form frameMainMenu
     */
    public frameMainMenu() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
    
        disable_all_tools_button();
        
        add_comboitem_matakuliah();
        add_comboitem_mahasiswa();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="common button management">
    
    public void enable_all_menu_button(){
        jButton_utama.setEnabled(true);
        jButton_data_mahasiswa.setEnabled(true);
        jButton_data_matakuliah.setEnabled(true);
        jButton_data_nilai.setEnabled(true);
        jButton_simulasi.setEnabled(true);
        jButton_logout.setEnabled(true);
    }
    public void disable_all_menu_button(){
        jButton_utama.setEnabled(false);
        jButton_data_mahasiswa.setEnabled(false);
        jButton_data_matakuliah.setEnabled(false);
        jButton_data_nilai.setEnabled(false);
        jButton_simulasi.setEnabled(false);
        jButton_logout.setEnabled(false);
    }
    
    public void enable_all_tools_button(){
        jButton_add.setEnabled(true);
        jButton_delete.setEnabled(true);
        jButton_edit.setEnabled(true);
        jButton_to_pdf.setEnabled(true);
        jButton_search.setEnabled(true);
        jTextField_search.setEnabled(true);
    }
    public void disable_all_tools_button(){
        jButton_add.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_edit.setEnabled(false);
        jButton_to_pdf.setEnabled(false);
        jButton_search.setEnabled(false);
        jTextField_search.setEnabled(false);
    }
    
    // </editor-fold>
    
    
    
    // <editor-fold defaultstate="collapsed" desc="mahasiswa management">
    
    public void clean_textfield_Mahasiswa(){
        jTextField_nim.setText("");
        jTextField_nama.setText("");
        jTextField_tempat_lahir.setText("");
        jFormattedTextField_tanggal_lahir.setCalendar(null);
        jTextArea_alamat.setText("");
    }
    
    public void toggle_nonactive_textfield_Mahasiswa(){
        jTextField_nim.setEnabled(false);
        jTextField_nama.setEnabled(false);
        jTextField_tempat_lahir.setEnabled(false);
        jFormattedTextField_tanggal_lahir.setEnabled(false);
        jTextArea_alamat.setEnabled(false);
    }
    
    public void toggle_active_textfield_Mahasiswa(){
        jTextField_nim.setEnabled(true);
        jTextField_nama.setEnabled(true);
        jTextField_tempat_lahir.setEnabled(true);
        jFormattedTextField_tanggal_lahir.setEnabled(true);
        jTextArea_alamat.setEnabled(true);
    }
    
    int row_mahasiswa = 0 ;
    public void showfieldMahasiswa() throws ParseException{
        row_mahasiswa = jTable.getSelectedRow();
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        
        String tanggalLahir = tableMahasiswa.getValueAt(row_mahasiswa, 3).toString();
        java.util.Date tgl = new SimpleDateFormat(tampilan).parse(tanggalLahir);
        
        jTextField_nim.setText(tableMahasiswa.getValueAt(row_mahasiswa, 0).toString());
        jTextField_nama.setText(tableMahasiswa.getValueAt(row_mahasiswa, 1).toString());
        jTextField_tempat_lahir.setText(tableMahasiswa.getValueAt(row_mahasiswa, 2).toString());
        jFormattedTextField_tanggal_lahir.setDate(tgl);
        jTextArea_alamat.setText(tableMahasiswa.getValueAt(row_mahasiswa, 4).toString());
        
        jButton_save_mahasiswa.setEnabled(false);
        jButton_cancel_mahasiswa.setEnabled(false);
        jButton_add.setEnabled(true);
        jButton_edit.setEnabled(true);
        jButton_delete.setEnabled(true);
        jButton_logout.setEnabled(true);
        toggle_nonactive_textfield_Mahasiswa();
        
        
    }
    
    public void showTableMahasiswa()
    {
        tableMahasiswa.setData(dbMahasiswa.show_database_mahasiswa());
        jTable.setModel(tableMahasiswa);
    }
    
    public void refreshdataMahasiswa()
    {
        tableMahasiswa.setData(dbMahasiswa.show_database_mahasiswa());
        tableMahasiswa.fireTableDataChanged();
        jTable.changeSelection(0,0,false,false);
    }
    
    public void call_addrowMahasiswa(){
        clean_textfield_Mahasiswa();
        toggle_active_textfield_Mahasiswa();
        jTextField_nomor_mk.requestFocus();
        jButton_save_mahasiswa.setEnabled(true);
        jButton_cancel_mahasiswa.setEnabled(true);   
    }
    
    public void call_deleterowMahasiswa(){
        try
        {
            int row = jTable.getSelectedRow();
            String nim = (String)tableMahasiswa.getValueAt(row,0);
            String nama = (String)tableMahasiswa.getValueAt(row, 1);
            Object[] pilihan={"Ya","Tidak"};
            int answer = JOptionPane.showOptionDialog(
                    null,"Anda yakin data mahasiswa dengan nim \""+nim+"\" dengan nama \""+nama+"\" akan dihapus ?",
                    "Peringatan",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,pilihan,pilihan[0]);
            if(answer==0)
            {
                dbMahasiswa.delete_row_mahasiswa(nim);
                clean_textfield_Mahasiswa();
                refreshdataMahasiswa();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"Pilih data yang akan dihapus !!!");        
        }
    }
    
    public void call_editrowMahasiswa(){
        toggle_active_textfield_Mahasiswa();
        jTextField_nim.requestFocus();
        jButton_save_mahasiswa.setEnabled(true);
        jButton_cancel_mahasiswa.setEnabled(true);        
    }
    
    public void call_filterdatabaseMahasiswa(){
        String keyword;
        keyword=jTextField_search.getText();
        if(keyword!=null){
            tableMahasiswa.setData(dbMahasiswa.filter_database_mahasiswa(keyword));
            tableMahasiswa.fireTableDataChanged();
        }
    }
    
    
    
    //  </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="matakuliah management">
    
    public void clean_textfield_Matakuliah(){
        jTextField_nomor_mk.setText("");
        jTextField_nama_mk.setText("");
    }
    
    public void toggle_nonactive_textfield_Matakuliah(){
        jTextField_nomor_mk.setEnabled(false);
        jTextField_nama_mk.setEnabled(false);
    }
    
    public void toggle_active_textfield_Matakuliah(){
        jTextField_nomor_mk.setEnabled(true);
        jTextField_nama_mk.setEnabled(true);
    }
    
    int row_matakuliah = 0 ;
    public void showfieldMatakuliah(){
        row_matakuliah = jTable.getSelectedRow();
        jTextField_nomor_mk.setText(tableMatakuliah.getValueAt(row_matakuliah, 0).toString());
        jTextField_nama_mk.setText(tableMatakuliah.getValueAt(row_matakuliah, 1).toString());
        
        jButton_save_matakuliah.setEnabled(false);
        jButton_cancel_matakuliah.setEnabled(false);
        jButton_add.setEnabled(true);
        jButton_edit.setEnabled(true);
        jButton_delete.setEnabled(true);
        jButton_logout.setEnabled(true);
        toggle_nonactive_textfield_Matakuliah();    
    }
    
    public void showTableMatakuliah()
    {
        tableMatakuliah.setData(dbMatakuliah.show_database_matakuliah());
        jTable.setModel(tableMatakuliah);
    }
    
    public void refreshdataMatakuliah()
    {
        tableMatakuliah.setData(dbMatakuliah.show_database_matakuliah());
        tableMatakuliah.fireTableDataChanged();
        jTable.changeSelection(0,0,false,false);
    }
    
    public void call_addrowMatakuliah(){
        clean_textfield_Matakuliah();
        toggle_active_textfield_Matakuliah();
        jTextField_nomor_mk.requestFocus();
        jButton_save_matakuliah.setEnabled(true);
        jButton_cancel_matakuliah.setEnabled(true);
    }
    
    public void call_deleterowMatakuliah(){
        try
        {
            int row = jTable.getSelectedRow();
            String nomor_mk = (String)tableMatakuliah.getValueAt(row,0);
            String nama_mk = (String)tableMatakuliah.getValueAt(row, 1);
            Object[] pilihan={"Ya","Tidak"};
            int answer = JOptionPane.showOptionDialog(
                    null,"Anda yakin data mata kuliah dengan nomor_mk \""+nomor_mk+"\" dengan nama \""+nama_mk+"\" akan dihapus ?",
                    "Peringatan",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,pilihan,pilihan[0]);
            if(answer==0)
            {
                dbMatakuliah.delete_row_matakuliah(nomor_mk);
                clean_textfield_Matakuliah();
                refreshdataMatakuliah();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"Pilih data yang akan dihapus !!!");        
        }
    }
    
    public void call_editrowMatakuliah(){
        toggle_active_textfield_Matakuliah();
        jTextField_nomor_mk.requestFocus();
        jButton_save_matakuliah.setEnabled(true);
        jButton_cancel_matakuliah.setEnabled(true);  
    }
    
    public void call_filterdatabaseMatakuliah(){
        String keyword;
        keyword=jTextField_search.getText();
        if(keyword!=null){
            tableMatakuliah.setData(dbMatakuliah.filter_database_matakuliah(keyword));
            tableMatakuliah.fireTableDataChanged();
        }
    }
    
    
    
    
    //  </editor-fold>
   
    
    // <editor-fold defaultstate="collapsed" desc="nilai management">
    
    public void clean_textfield_Nilai_Mahasiswa(){
        
        jComboBox_nama.setSelectedIndex(0);
        jComboBox_nama_mk.setSelectedIndex(0);
        
        jTextField_nomor_mk_readonly.setText("");
        jTextField_nim_readonly.setText("");
        
        jFormattedTextField_kehadiran.setText("");
        jFormattedTextField_tugas1.setText("");
        jFormattedTextField_tugas2.setText("");
        jFormattedTextField_tugas3.setText("");
        jFormattedTextField_uts.setText("");
        jFormattedTextField_uas.setText("");
        jFormattedTextField_tanggal_lahir.setCalendar(null);
    }
    
    public void toggle_nonactive_textfield_Nilai_Mahasiswa(){
        
        jComboBox_nama.setEnabled(false);
        jComboBox_nama_mk.setEnabled(false);
        
        jFormattedTextField_kehadiran.setEnabled(false);
        jFormattedTextField_tugas1.setEnabled(false);
        jFormattedTextField_tugas2.setEnabled(false);
        jFormattedTextField_tugas3.setEnabled(false);
        jFormattedTextField_uts.setEnabled(false);
        jFormattedTextField_uas.setEnabled(false);
        jFormattedTextField_angkatan.setEnabled(false);
    }
    
    public void toggle_active_textfield_Nilai_Mahasiswa(){
        
        jComboBox_nama.setEnabled(true);
        jComboBox_nama_mk.setEnabled(true);
        
        jFormattedTextField_kehadiran.setEnabled(true);
        jFormattedTextField_tugas1.setEnabled(true);
        jFormattedTextField_tugas2.setEnabled(true);
        jFormattedTextField_tugas3.setEnabled(true);
        jFormattedTextField_uts.setEnabled(true);
        jFormattedTextField_uas.setEnabled(true);
        jFormattedTextField_angkatan.setEnabled(true);
    }
    
    int row_nilai_mahasiswa = 0 ;
    public void showfieldNilai_Mahasiswa(){
        row_nilai_mahasiswa = jTable.getSelectedRow();
        
        jTextField_nim_readonly.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 1).toString());
        jComboBox_nama.setSelectedItem(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 2).toString());
        
        jTextField_nomor_mk_readonly.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 3).toString());
        jComboBox_nama_mk.setSelectedItem(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 4).toString());
        
        jFormattedTextField_kehadiran.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 5).toString());
        jFormattedTextField_tugas1.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 6).toString());
        jFormattedTextField_tugas2.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 7).toString());
        jFormattedTextField_tugas3.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 8).toString());
        jFormattedTextField_uts.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 9).toString());
        jFormattedTextField_uas.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 10).toString());
//        jFormattedTextField_angkatan.setText(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 11).toString());
        jFormattedTextField_angkatan.setYear(Integer.valueOf(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 11).toString()));

        jButton_save_nilai_mahasiswa.setEnabled(false);
        jButton_cancel_nilai_mahasiswa.setEnabled(false);
        jButton_add.setEnabled(true);
        jButton_edit.setEnabled(true);
        jButton_delete.setEnabled(true);
        jButton_logout.setEnabled(true);
        toggle_nonactive_textfield_Nilai_Mahasiswa();
        
        
    }
    
    public void showTableNilai_Mahasiswa()
    {
        tableNilai_Mahasiswa.setData(dbNilai_Mahasiswa.show_database_nilai_mahasiswa());
        jTable.setModel(tableNilai_Mahasiswa);
    }
    
    public void refreshdataNilai_Mahasiswa()
    {
        tableNilai_Mahasiswa.setData(dbNilai_Mahasiswa.show_database_nilai_mahasiswa());
        tableNilai_Mahasiswa.fireTableDataChanged();
        jTable.changeSelection(0,0,false,false);
    }
    
    public void call_addrowNilai_Mahasiswa(){
        clean_textfield_Nilai_Mahasiswa();
        toggle_active_textfield_Nilai_Mahasiswa();
        jComboBox_nama.requestFocus();
        jButton_save_nilai_mahasiswa.setEnabled(true);
        jButton_cancel_nilai_mahasiswa.setEnabled(true);  
    }
    
    
    public void call_deleterowNilai_Mahasiswa(){
        try
        {
            int row = jTable.getSelectedRow();
            int id = (Integer)tableNilai_Mahasiswa.getValueAt(row,0);
            String nim = (String)tableNilai_Mahasiswa.getValueAt(row, 1);
            String nama = (String)tableNilai_Mahasiswa.getValueAt(row, 2);
            String nama_mk = (String)tableNilai_Mahasiswa.getValueAt(row, 4);
            Object[] pilihan={"Ya","Tidak"};
            int answer = JOptionPane.showOptionDialog(
                    null,"Anda yakin data nilai mahasiswa dengan nim \""+nim+"\" dan nama \""+nama+"\" pada mata kuliah \""+nama_mk+"\"  akan dihapus ?",
                    "Peringatan",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,pilihan,pilihan[0]);
            if(answer==0)
            {
                dbNilai_Mahasiswa.delete_row_nilai_mahasiswa(id);
                clean_textfield_Nilai_Mahasiswa();
                refreshdataNilai_Mahasiswa();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"Pilih data yang akan dihapus !!!");        
        }
    }
    
    public void call_editrowNilai_Mahasiswa(){
        toggle_active_textfield_Nilai_Mahasiswa();
        jComboBox_nama.requestFocus();
        jButton_save_nilai_mahasiswa.setEnabled(true);
        jButton_cancel_nilai_mahasiswa.setEnabled(true);  
    }
    
    public void call_filterdatabaseNilai_Mahasiswa(){
        String keyword;
        keyword=jTextField_search.getText();
        if(keyword!=null){
            tableNilai_Mahasiswa.setData(dbNilai_Mahasiswa.filter_database_nilai_mahasiswa(keyword));
            tableNilai_Mahasiswa.fireTableDataChanged();
        }
    }
    
    
    public void add_comboitem_mahasiswa(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = database.getConnection();
            ps = conn.prepareStatement("select nama from mahasiswa");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBox_nama.addItem(rs.getString("nama"));             
            }          
        }catch(SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    public void update_textfield_mahasiswa(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = database.getConnection();
            ps = conn.prepareStatement("select nim from mahasiswa where nama=?");
            ps.setString(1, (String)jComboBox_nama.getSelectedItem());
            ResultSet rs = ps.executeQuery();    
            while (rs.next()) {
                jTextField_nim_readonly.setText(rs.getString("nim"));       
            }
            
        }catch(SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    public void add_comboitem_matakuliah(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = database.getConnection();
            ps = conn.prepareStatement("select nama_mk from matakuliah");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                jComboBox_nama_mk.addItem(rs.getString("nama_mk"));
                
            }
            
        }catch(SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    public void update_textfield_matakuliah(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = database.getConnection();
            ps = conn.prepareStatement("select nomor_mk from matakuliah where nama_mk=?");
            ps.setString(1, (String)jComboBox_nama_mk.getSelectedItem());
           
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                jTextField_nomor_mk_readonly.setText(rs.getString("nomor_mk"));
                
            }
            
        }catch(SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    double total_nilai_absen, total_nilai_tugas, total_nilai_uts, total_nilai_uas, total_nilai_akhir;
    String indeks, keterangan;
    public void calculate_nilai(int kehadiran,int tugas1,int tugas2,int tugas3,int uts,int uas){
        total_nilai_absen = (((kehadiran/14.0)*100.0*5.0)/100.0);       
        total_nilai_tugas = ((tugas1+tugas2+tugas3)/3.0*(25.0/100.0));
        total_nilai_uts   = uts * 0.3;
        total_nilai_uas   = uas * 0.4;
        total_nilai_akhir = total_nilai_absen + total_nilai_tugas + total_nilai_uts + total_nilai_uas;
        if (total_nilai_akhir>=80 && total_nilai_akhir<=100) {
            indeks = "A";
        }else if (total_nilai_akhir>=68 && total_nilai_akhir<=79){
            indeks = "B";
        }else if (total_nilai_akhir>=56 && total_nilai_akhir<=67){
            indeks = "C";
        }else if (total_nilai_akhir>=45 && total_nilai_akhir<=55){
            indeks = "D";
        }else{
            indeks = "E";
        }
        if ("A".equals(indeks) || "B".equals(indeks) || "C".equals(indeks)) {
            keterangan = "Lulus";
        }else if ("D".equals(indeks) || "E".equals(indeks)){
            keterangan = "Tidak Lulus";
        }else{
            JOptionPane.showMessageDialog(null, "Error in Nilai Calculation");
        }
        if (kehadiran<=10) {
            keterangan = "Tidak Lulus";
        }        
    }
    
    
    
    
    
    
    
    //  </editor-fold>
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_logo = new javax.swing.JPanel();
        jLabel_logo_small = new javax.swing.JLabel();
        jPanel_side_navigation = new javax.swing.JPanel();
        jButton_utama = new javax.swing.JButton();
        jButton_data_mahasiswa = new javax.swing.JButton();
        jButton_data_matakuliah = new javax.swing.JButton();
        jButton_data_nilai = new javax.swing.JButton();
        jButton_simulasi = new javax.swing.JButton();
        jPanel_header = new javax.swing.JPanel();
        jButton_logout = new javax.swing.JButton();
        jPanel_tools = new javax.swing.JPanel();
        jTextField_search = new javax.swing.JTextField();
        jLabel_refresh = new javax.swing.JLabel();
        jButton_search = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_edit = new javax.swing.JButton();
        jButton_to_pdf = new javax.swing.JButton();
        jPanel_edit = new javax.swing.JPanel();
        jPanel_main = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel_update_mahasiswa = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField_nama = new javax.swing.JTextField();
        jTextField_nim = new javax.swing.JTextField();
        jTextField_tempat_lahir = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_alamat = new javax.swing.JTextArea();
        jButton_cancel_mahasiswa = new javax.swing.JButton();
        jButton_save_mahasiswa = new javax.swing.JButton();
        jFormattedTextField_tanggal_lahir = new com.toedter.calendar.JDateChooser();
        jPanel_update_matakuliah = new javax.swing.JPanel();
        jTextField_nama_mk = new javax.swing.JTextField();
        jTextField_nomor_mk = new javax.swing.JTextField();
        jButton_cancel_matakuliah = new javax.swing.JButton();
        jButton_save_matakuliah = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_update_nilai = new javax.swing.JPanel();
        jButton_cancel_nilai_mahasiswa = new javax.swing.JButton();
        jButton_save_nilai_mahasiswa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_nomor_mk_readonly = new javax.swing.JTextField();
        jComboBox_nama = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_nama_mk = new javax.swing.JComboBox<>();
        jTextField_nim_readonly = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jFormattedTextField_kehadiran = new javax.swing.JFormattedTextField();
        jFormattedTextField_tugas1 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField_tugas2 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField_tugas3 = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextField_uts = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jFormattedTextField_uas = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jFormattedTextField_angkatan = new com.toedter.calendar.JYearChooser();
        jPanel_table = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane_table = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1366, 762));

        jPanel_logo.setBackground(new java.awt.Color(64, 181, 198));

        jLabel_logo_small.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/logo-small.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_logoLayout = new javax.swing.GroupLayout(jPanel_logo);
        jPanel_logo.setLayout(jPanel_logoLayout);
        jPanel_logoLayout.setHorizontalGroup(
            jPanel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_logoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_logo_small, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_logoLayout.setVerticalGroup(
            jPanel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_logoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_logo_small)
                .addContainerGap())
        );

        jPanel_side_navigation.setBackground(new java.awt.Color(39, 42, 51));

        jButton_utama.setBackground(new java.awt.Color(39, 42, 51));
        jButton_utama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/home.png"))); // NOI18N
        jButton_utama.setBorder(null);
        jButton_utama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_utama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_utamaActionPerformed(evt);
            }
        });

        jButton_data_mahasiswa.setBackground(new java.awt.Color(39, 42, 51));
        jButton_data_mahasiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/data-mahasiswa.png"))); // NOI18N
        jButton_data_mahasiswa.setBorder(null);
        jButton_data_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_data_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_data_mahasiswaActionPerformed(evt);
            }
        });

        jButton_data_matakuliah.setBackground(new java.awt.Color(39, 42, 51));
        jButton_data_matakuliah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/data-matakuliah.png"))); // NOI18N
        jButton_data_matakuliah.setBorder(null);
        jButton_data_matakuliah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_data_matakuliah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_data_matakuliahActionPerformed(evt);
            }
        });

        jButton_data_nilai.setBackground(new java.awt.Color(39, 42, 51));
        jButton_data_nilai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/data-nilai.png"))); // NOI18N
        jButton_data_nilai.setBorder(null);
        jButton_data_nilai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_data_nilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_data_nilaiActionPerformed(evt);
            }
        });

        jButton_simulasi.setBackground(new java.awt.Color(39, 42, 51));
        jButton_simulasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/simulasi.png"))); // NOI18N
        jButton_simulasi.setBorder(null);
        jButton_simulasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_simulasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_simulasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_side_navigationLayout = new javax.swing.GroupLayout(jPanel_side_navigation);
        jPanel_side_navigation.setLayout(jPanel_side_navigationLayout);
        jPanel_side_navigationLayout.setHorizontalGroup(
            jPanel_side_navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_data_matakuliah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_data_mahasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_simulasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_data_nilai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_side_navigationLayout.setVerticalGroup(
            jPanel_side_navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_side_navigationLayout.createSequentialGroup()
                .addComponent(jButton_utama, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_data_mahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_data_matakuliah, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_data_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_simulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel_header.setBackground(new java.awt.Color(69, 200, 220));
        jPanel_header.setPreferredSize(new java.awt.Dimension(1199, 45));

        jButton_logout.setBackground(new java.awt.Color(69, 200, 220));
        jButton_logout.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_logout.setForeground(new java.awt.Color(255, 255, 255));
        jButton_logout.setText("LOG OUT");
        jButton_logout.setBorder(null);
        jButton_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_headerLayout = new javax.swing.GroupLayout(jPanel_header);
        jPanel_header.setLayout(jPanel_headerLayout);
        jPanel_headerLayout.setHorizontalGroup(
            jPanel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_logout)
                .addGap(51, 51, 51))
        );
        jPanel_headerLayout.setVerticalGroup(
            jPanel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_logout)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel_tools.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_tools.setPreferredSize(new java.awt.Dimension(1199, 52));

        jTextField_search.setToolTipText("name search");

        jLabel_refresh.setForeground(new java.awt.Color(102, 102, 102));
        jLabel_refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/refresh.png"))); // NOI18N
        jLabel_refresh.setText("REFRESH TABLE");
        jLabel_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_refreshMouseClicked(evt);
            }
        });

        jButton_search.setBackground(new java.awt.Color(241, 241, 241));
        jButton_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/search.png"))); // NOI18N
        jButton_search.setToolTipText("Name Search");
        jButton_search.setAutoscrolls(true);
        jButton_search.setBorder(null);
        jButton_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jButton_add.setBackground(new java.awt.Color(241, 241, 241));
        jButton_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/add.png"))); // NOI18N
        jButton_add.setBorder(null);
        jButton_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(241, 241, 241));
        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/delete.png"))); // NOI18N
        jButton_delete.setBorder(null);
        jButton_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_edit.setBackground(new java.awt.Color(241, 241, 241));
        jButton_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/edit.png"))); // NOI18N
        jButton_edit.setBorder(null);
        jButton_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editActionPerformed(evt);
            }
        });

        jButton_to_pdf.setBackground(new java.awt.Color(241, 241, 241));
        jButton_to_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/pdf.png"))); // NOI18N
        jButton_to_pdf.setBorder(null);
        jButton_to_pdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_to_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_to_pdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_toolsLayout = new javax.swing.GroupLayout(jPanel_tools);
        jPanel_tools.setLayout(jPanel_toolsLayout);
        jPanel_toolsLayout.setHorizontalGroup(
            jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_toolsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel_refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_add)
                .addGap(31, 31, 31)
                .addComponent(jButton_delete)
                .addGap(34, 34, 34)
                .addComponent(jButton_edit)
                .addGap(27, 27, 27)
                .addComponent(jButton_to_pdf)
                .addGap(18, 18, 18)
                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_search)
                .addGap(48, 48, 48))
        );
        jPanel_toolsLayout.setVerticalGroup(
            jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_toolsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_add)
                    .addComponent(jButton_delete)
                    .addComponent(jButton_edit)
                    .addComponent(jButton_to_pdf)
                    .addGroup(jPanel_toolsLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_search, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_search)))
                    .addComponent(jLabel_refresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_edit.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_edit.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_edit.setToolTipText("");
        jPanel_edit.setPreferredSize(new java.awt.Dimension(1119, 99));
        jPanel_edit.setLayout(new java.awt.CardLayout());

        jPanel_main.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_main.setPreferredSize(new java.awt.Dimension(1199, 99));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/lukman(200px).jpg"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/alex(200px).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel_mainLayout = new javax.swing.GroupLayout(jPanel_main);
        jPanel_main.setLayout(jPanel_mainLayout);
        jPanel_mainLayout.setHorizontalGroup(
            jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mainLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(246, 246, 246))
        );
        jPanel_mainLayout.setVerticalGroup(
            jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mainLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addContainerGap())
        );

        jPanel_edit.add(jPanel_main, "panel_main");

        jPanel_update_mahasiswa.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_update_mahasiswa.setPreferredSize(new java.awt.Dimension(1199, 99));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(132, 135, 148));
        jLabel7.setText("NIM");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(132, 135, 148));
        jLabel8.setText("Nama");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(132, 135, 148));
        jLabel9.setText("Tempat Lahir");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(132, 135, 148));
        jLabel10.setText("Tanggal Lahir");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(132, 135, 148));
        jLabel11.setText("Alamat");

        jTextArea_alamat.setColumns(20);
        jTextArea_alamat.setRows(5);
        jScrollPane1.setViewportView(jTextArea_alamat);

        jButton_cancel_mahasiswa.setBackground(new java.awt.Color(220, 69, 69));
        jButton_cancel_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel_mahasiswa.setText("Cancel");
        jButton_cancel_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cancel_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancel_mahasiswaActionPerformed(evt);
            }
        });

        jButton_save_mahasiswa.setBackground(new java.awt.Color(69, 200, 220));
        jButton_save_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_save_mahasiswa.setText("Save");
        jButton_save_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_save_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_save_mahasiswaActionPerformed(evt);
            }
        });

        jFormattedTextField_tanggal_lahir.setDateFormatString("dd-MMMM-yyyy");

        javax.swing.GroupLayout jPanel_update_mahasiswaLayout = new javax.swing.GroupLayout(jPanel_update_mahasiswa);
        jPanel_update_mahasiswa.setLayout(jPanel_update_mahasiswaLayout);
        jPanel_update_mahasiswaLayout.setHorizontalGroup(
            jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                        .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_update_mahasiswaLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(78, 78, 78)
                                .addComponent(jTextField_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_update_mahasiswaLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_tempat_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jFormattedTextField_tanggal_lahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                        .addComponent(jButton_save_mahasiswa)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_cancel_mahasiswa)))
                .addContainerGap(589, Short.MAX_VALUE))
        );
        jPanel_update_mahasiswaLayout.setVerticalGroup(
            jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jFormattedTextField_tanggal_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_update_mahasiswaLayout.createSequentialGroup()
                        .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField_tempat_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_update_mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_save_mahasiswa)
                    .addComponent(jButton_cancel_mahasiswa))
                .addContainerGap())
        );

        jPanel_edit.add(jPanel_update_mahasiswa, "panel_update_mahasiswa");

        jPanel_update_matakuliah.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_update_matakuliah.setPreferredSize(new java.awt.Dimension(1199, 99));

        jButton_cancel_matakuliah.setBackground(new java.awt.Color(220, 69, 69));
        jButton_cancel_matakuliah.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel_matakuliah.setText("Cancel");
        jButton_cancel_matakuliah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cancel_matakuliah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancel_matakuliahActionPerformed(evt);
            }
        });

        jButton_save_matakuliah.setBackground(new java.awt.Color(69, 200, 220));
        jButton_save_matakuliah.setForeground(new java.awt.Color(255, 255, 255));
        jButton_save_matakuliah.setText("Save");
        jButton_save_matakuliah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_save_matakuliah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_save_matakuliahActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(132, 135, 148));
        jLabel1.setText("Nomor MK");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(132, 135, 148));
        jLabel2.setText("Nama MK");

        javax.swing.GroupLayout jPanel_update_matakuliahLayout = new javax.swing.GroupLayout(jPanel_update_matakuliah);
        jPanel_update_matakuliah.setLayout(jPanel_update_matakuliahLayout);
        jPanel_update_matakuliahLayout.setHorizontalGroup(
            jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_update_matakuliahLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_update_matakuliahLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(58, 58, 58)
                        .addComponent(jTextField_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_update_matakuliahLayout.createSequentialGroup()
                        .addComponent(jButton_save_matakuliah)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_cancel_matakuliah))
                    .addGroup(jPanel_update_matakuliahLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addComponent(jTextField_nomor_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_update_matakuliahLayout.setVerticalGroup(
            jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_update_matakuliahLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_nomor_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_update_matakuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_save_matakuliah)
                    .addComponent(jButton_cancel_matakuliah))
                .addGap(16, 16, 16))
        );

        jPanel_edit.add(jPanel_update_matakuliah, "panel_update_matakuliah");

        jPanel_update_nilai.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_update_nilai.setPreferredSize(new java.awt.Dimension(1199, 99));

        jButton_cancel_nilai_mahasiswa.setBackground(new java.awt.Color(220, 69, 69));
        jButton_cancel_nilai_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel_nilai_mahasiswa.setText("Cancel");
        jButton_cancel_nilai_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cancel_nilai_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancel_nilai_mahasiswaActionPerformed(evt);
            }
        });

        jButton_save_nilai_mahasiswa.setBackground(new java.awt.Color(69, 200, 220));
        jButton_save_nilai_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_save_nilai_mahasiswa.setText("Save");
        jButton_save_nilai_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_save_nilai_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_save_nilai_mahasiswaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(132, 135, 148));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(132, 135, 148));
        jLabel4.setText("NIM");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(132, 135, 148));
        jLabel12.setText("Nama Mata Kuliah");

        jTextField_nomor_mk_readonly.setEditable(false);
        jTextField_nomor_mk_readonly.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox_nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Nama" }));
        jComboBox_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_namaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(132, 135, 148));
        jLabel13.setText("Nomor Mata Kuliah");

        jComboBox_nama_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Nama Mata Kuliah" }));
        jComboBox_nama_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_nama_mkActionPerformed(evt);
            }
        });

        jTextField_nim_readonly.setEditable(false);
        jTextField_nim_readonly.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(132, 135, 148));
        jLabel14.setText("Kehadiran");

        jFormattedTextField_kehadiran.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_kehadiran.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_tugas1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(132, 135, 148));
        jLabel15.setText("Tugas 1");

        jFormattedTextField_tugas2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(132, 135, 148));
        jLabel16.setText("Tugas 2");

        jFormattedTextField_tugas3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(132, 135, 148));
        jLabel17.setText("Tugas 3");

        jFormattedTextField_uts.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_uts.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(132, 135, 148));
        jLabel18.setText("UTS");

        jFormattedTextField_uas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_uas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(132, 135, 148));
        jLabel19.setText("UAS");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(132, 135, 148));
        jLabel20.setText("Angkatan");

        jFormattedTextField_angkatan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));

        javax.swing.GroupLayout jPanel_update_nilaiLayout = new javax.swing.GroupLayout(jPanel_update_nilai);
        jPanel_update_nilai.setLayout(jPanel_update_nilaiLayout);
        jPanel_update_nilaiLayout.setHorizontalGroup(
            jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14)
                    .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField_kehadiran, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton_save_nilai_mahasiswa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_nama, 0, 180, Short.MAX_VALUE)
                                    .addComponent(jTextField_nim_readonly))
                                .addGap(46, 46, 46))
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tugas1))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tugas2))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tugas3))
                                .addGap(34, 34, 34))))
                    .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancel_nilai_mahasiswa)
                        .addGap(199, 199, 199)))
                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addComponent(jFormattedTextField_uts)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel19))
                            .addComponent(jFormattedTextField_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField_angkatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(638, 638, 638))
                    .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nomor_mk_readonly, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(419, 419, 419))))
        );
        jPanel_update_nilaiLayout.setVerticalGroup(
            jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_update_nilaiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_nim_readonly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField_nomor_mk_readonly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jFormattedTextField_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_update_nilaiLayout.createSequentialGroup()
                            .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextField_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextField_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel_update_nilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_save_nilai_mahasiswa)
                    .addComponent(jButton_cancel_nilai_mahasiswa))
                .addGap(21, 21, 21))
        );

        jPanel_edit.add(jPanel_update_nilai, "panel_update_nilai");

        jPanel_table.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_table.setName(""); // NOI18N
        jPanel_table.setPreferredSize(new java.awt.Dimension(900, 421));
        jPanel_table.setLayout(new java.awt.CardLayout(22, 22));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 421));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 421));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("10116347 / LUKMANNUDIN");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel21.setText("10116370 / ALEXANDER M S ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("WELCOME");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("WELCOME");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("WELCOME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(154, 154, 154))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel6))
                .addGap(121, 121, 121)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel5)
                    .addComponent(jLabel25))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel_table.add(jPanel1, "panel_table_hide");

        jScrollPane_table.setOpaque(false);
        jScrollPane_table.setPreferredSize(new java.awt.Dimension(500, 421));

        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable.setPreferredSize(new java.awt.Dimension(500, 421));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane_table.setViewportView(jTable);

        jPanel_table.add(jScrollPane_table, "panel_table_show");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_side_navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel_tools, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE)
                            .addComponent(jPanel_edit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, Short.MAX_VALUE)
                            .addComponent(jPanel_header, javax.swing.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_side_navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_tools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_refreshMouseClicked
        // Refresh all table
        refreshdataMatakuliah();
        refreshdataMahasiswa();
        refreshdataNilai_Mahasiswa();
    }//GEN-LAST:event_jLabel_refreshMouseClicked

    private void jButton_save_matakuliahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_save_matakuliahActionPerformed
        // Check empty, add, or edit       
        if ((jTextField_nomor_mk.getText().isEmpty()) || (jTextField_nama_mk.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "data kosong, silahkan dilengkapi");
            jTextField_nomor_mk.requestFocus();
        }else if(jTextField_nomor_mk.getText().matches("[A-Z][A-Z][0-9][0-9][0-9]")){
                if (jButton_add.getModel().isEnabled()) {//if add            
                    dbMatakuliah.add_data_matakuliah(new modelMatakuliah(jTextField_nomor_mk.getText(), jTextField_nama_mk.getText()));
                    refreshdataMatakuliah();
                    clean_textfield_Matakuliah();
                    enable_all_menu_button();
                    enable_all_tools_button();
                }else if (jButton_edit.getModel().isEnabled()){ //if edit
                    dbMatakuliah.update_row_matakuliah(new modelMatakuliah(jTextField_nomor_mk.getText(), 
                            jTextField_nama_mk.getText()), tableMatakuliah.getValueAt(row_matakuliah, 0).toString());
                    refreshdataMatakuliah();
                    clean_textfield_Matakuliah();
                    enable_all_menu_button();
                    enable_all_tools_button();
                }else{
                    System.out.println("no button pressed");
                }
        }else{
            JOptionPane.showMessageDialog(null, "Format Nomor MK harus diawali dengan 2 huruf kapital dan diikuti 3 angka");
        }
        
    }//GEN-LAST:event_jButton_save_matakuliahActionPerformed

    
    
    // <editor-fold defaultstate="collapsed" desc="tools management">
    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        
        switch (Option){
            case 1: call_filterdatabaseMahasiswa();
                    break;
            case 2: call_filterdatabaseMatakuliah();
                    break;
            case 3: call_filterdatabaseNilai_Mahasiswa();
                    break;  
        }
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed

        switch (Option){
            case 1: call_addrowMahasiswa();
                    break;
            case 2: call_addrowMatakuliah();
                    break;
            case 3: call_addrowNilai_Mahasiswa();
                    break;        
        }
        
        disable_all_menu_button();
        jButton_edit.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_logout.setEnabled(false);
        

    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_to_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_to_pdfActionPerformed
        try {
            switch (Option){
            case 1: exportPDF.exportMahasiswa();
                    break;
            case 2: exportPDF.exportMatakuliah();
                    break;
            case 3: exportPDF.exportNilai();
                    break;

            }
            JOptionPane.showMessageDialog(null, "Export Berhasil disimpan di folder kemahasiswaan_10116347_10116370");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Export Error: "+ex.getMessage()); 
        }
    }//GEN-LAST:event_jButton_to_pdfActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        
        switch (Option){
            case 1: call_deleterowMahasiswa();
                    break;
            case 2: call_deleterowMatakuliah();
                    break;
            case 3: call_deleterowNilai_Mahasiswa();
                    break;
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editActionPerformed
        
        switch (Option){
            case 1: call_editrowMahasiswa();
                    
                    break;
            case 2: call_editrowMatakuliah();
                    break;
            case 3: call_editrowNilai_Mahasiswa();
                    break;
        }
        
        disable_all_menu_button();
        jButton_add.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_logout.setEnabled(false);
        
    }//GEN-LAST:event_jButton_editActionPerformed

      //  </editor-fold>
    
    
    
    private void jButton_cancel_matakuliahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancel_matakuliahActionPerformed
        
        clean_textfield_Matakuliah();
        
        enable_all_tools_button();
        enable_all_menu_button();
        
    }//GEN-LAST:event_jButton_cancel_matakuliahActionPerformed

    private void jButton_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_logoutActionPerformed
        
        navigation.showLogin(this);
    }//GEN-LAST:event_jButton_logoutActionPerformed

    
    
    // <editor-fold defaultstate="collapsed" desc="menu management">
    
    private void jButton_utamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_utamaActionPerformed
        Option = 0;
        
        
    
        disable_all_tools_button();
        jButton_logout.setEnabled(true);
        
        
        
        CardLayout card_edit = (CardLayout)jPanel_edit.getLayout();
        card_edit.show(jPanel_edit, "panel_main");
        
        CardLayout card_table = (CardLayout)jPanel_table.getLayout();
        card_table.show(jPanel_table, "panel_table_hide");
    }//GEN-LAST:event_jButton_utamaActionPerformed

    private void jButton_data_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_data_mahasiswaActionPerformed
        Option = 1;
        
        
        enable_all_tools_button();
        toggle_nonactive_textfield_Mahasiswa();
        jButton_save_mahasiswa.setEnabled(false);
        jButton_cancel_mahasiswa.setEnabled(false);
        
        CardLayout card_edit = (CardLayout)jPanel_edit.getLayout();
        card_edit.show(jPanel_edit, "panel_update_mahasiswa");
        
        CardLayout card_table = (CardLayout)jPanel_table.getLayout();
        card_table.show(jPanel_table, "panel_table_show");
        
        showTableMahasiswa();
    }//GEN-LAST:event_jButton_data_mahasiswaActionPerformed

    private void jButton_data_matakuliahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_data_matakuliahActionPerformed
        Option = 2;
        
        enable_all_tools_button();
        toggle_nonactive_textfield_Matakuliah();
        jButton_save_matakuliah.setEnabled(false);
        jButton_cancel_matakuliah.setEnabled(false);
        
        CardLayout card_edit = (CardLayout)jPanel_edit.getLayout();
        card_edit.show(jPanel_edit, "panel_update_matakuliah");
        
        CardLayout card_table = (CardLayout)jPanel_table.getLayout();
        card_table.show(jPanel_table, "panel_table_show");
        
        showTableMatakuliah();
    }//GEN-LAST:event_jButton_data_matakuliahActionPerformed

    private void jButton_data_nilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_data_nilaiActionPerformed
        Option = 3;
        
        
        enable_all_tools_button();
        toggle_nonactive_textfield_Nilai_Mahasiswa();
        jButton_save_nilai_mahasiswa.setEnabled(false);
        jButton_cancel_nilai_mahasiswa.setEnabled(false);
        
        CardLayout card_edit = (CardLayout)jPanel_edit.getLayout();
        card_edit.show(jPanel_edit, "panel_update_nilai");
        
        CardLayout card_table = (CardLayout)jPanel_table.getLayout();
        card_table.show(jPanel_table, "panel_table_show");
        
        
        showTableNilai_Mahasiswa();
    }//GEN-LAST:event_jButton_data_nilaiActionPerformed

    private void jButton_simulasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_simulasiActionPerformed
        navigation.showSimulation(this);
    }//GEN-LAST:event_jButton_simulasiActionPerformed

    
    //  </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="save n cancel btn management">
    private void jButton_cancel_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancel_mahasiswaActionPerformed
        clean_textfield_Mahasiswa();
        
        enable_all_tools_button();
        enable_all_menu_button();
    }//GEN-LAST:event_jButton_cancel_mahasiswaActionPerformed

    private void jButton_save_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_save_mahasiswaActionPerformed
        // Check empty, add, or edit       
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        
        String tgl = String.valueOf(format.format(jFormattedTextField_tanggal_lahir.getDate()));
        
        if ((jTextField_nim.getText().isEmpty()) 
                || (jTextField_nama.getText().isEmpty())
                || (jTextField_tempat_lahir.getText().isEmpty())
                || (tgl.isEmpty())
                || (jTextArea_alamat.getText().isEmpty())
            ) {
            JOptionPane.showMessageDialog(null, "data kosong, silahkan dilengkapi");
            jTextField_nim.requestFocus();
        }else if(jTextField_nim.getText().matches("^[0-9]*$")){
        
                if (jButton_add.getModel().isEnabled()) {//if add            
                    dbMahasiswa.add_data_mahasiswa(new modelMahasiswa(jTextField_nim.getText(), jTextField_nama.getText(),
                    jTextField_tempat_lahir.getText(), Date.valueOf(tgl), jTextArea_alamat.getText()));

                    refreshdataMahasiswa();
                    clean_textfield_Mahasiswa();
                    enable_all_menu_button();
                    enable_all_tools_button();
                }else if (jButton_edit.getModel().isEnabled()){
                    dbMahasiswa.update_row_mahasiswa(new modelMahasiswa(jTextField_nim.getText(), jTextField_nama.getText(),
                    jTextField_tempat_lahir.getText(), Date.valueOf(tgl), jTextArea_alamat.getText()), 
                            tableMahasiswa.getValueAt(row_mahasiswa, 0).toString());


                    refreshdataMahasiswa();
                    clean_textfield_Mahasiswa();
                    enable_all_menu_button();
                    enable_all_tools_button();
                }else{
                    System.out.println("no button pressed");
                }
        }else{
            JOptionPane.showMessageDialog(null, "NIM hanya menerima angka");
        }
    }//GEN-LAST:event_jButton_save_mahasiswaActionPerformed

    private void jButton_cancel_nilai_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancel_nilai_mahasiswaActionPerformed
        clean_textfield_Nilai_Mahasiswa();
        
        enable_all_tools_button();
        enable_all_menu_button();
    }//GEN-LAST:event_jButton_cancel_nilai_mahasiswaActionPerformed

    private void jButton_save_nilai_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_save_nilai_mahasiswaActionPerformed
        // Check empty, add, or edit      
        
        
        if (   
                jComboBox_nama.getSelectedIndex()==0 || jComboBox_nama_mk.getSelectedIndex()==0
                || (jFormattedTextField_kehadiran.getText().isEmpty()) 
                || (jFormattedTextField_tugas1.getText().isEmpty())
                || (jFormattedTextField_tugas2.getText().isEmpty())
                || (jFormattedTextField_tugas3.getText().isEmpty())
                || (jFormattedTextField_uts.getText().isEmpty())
                || (jFormattedTextField_uas.getText().isEmpty())
            ) {
            JOptionPane.showMessageDialog(null, "anda belum memilih nama atau nama mata kuliah atau data lainnya masih kosong, silahkan dilengkapi");
            jComboBox_nama.requestFocus();
            
        }else 
            if(jFormattedTextField_kehadiran.getText().matches("^[0-9][0-4]?$")) //0-14
            {
                if (jFormattedTextField_tugas1.getText().matches("^0$|^[1-9][0-9]?$|^100$") //0-100
                    && jFormattedTextField_tugas2.getText().matches("^0$|^[1-9][0-9]?$|^100$")
                    && jFormattedTextField_tugas3.getText().matches("^0$|^[1-9][0-9]?$|^100$")
                    && jFormattedTextField_uts.getText().matches("^0$|^[1-9][0-9]?$|^100$")
                    && jFormattedTextField_uas.getText().matches("^0$|^[1-9][0-9]?$|^100$")) 
                {
                    
                    if (jButton_add.getModel().isEnabled()) {//if add
                        int kehadiran = Integer.valueOf(jFormattedTextField_kehadiran.getText());
                        int tugas1    = Integer.valueOf(jFormattedTextField_tugas1.getText());
                        int tugas2    = Integer.valueOf(jFormattedTextField_tugas2.getText());
                        int tugas3    = Integer.valueOf(jFormattedTextField_tugas3.getText());
                        int uts    = Integer.valueOf(jFormattedTextField_uts.getText());
                        int uas    = Integer.valueOf(jFormattedTextField_uas.getText());
                        int thn_angkatan = jFormattedTextField_angkatan.getYear();
                        calculate_nilai(kehadiran, tugas1, tugas2, tugas3, uts, uas);
                        
                        int lastid = 1;
                        if (tableNilai_Mahasiswa.getRowCount()==0) {
                            lastid = 1;
                        }else if (tableNilai_Mahasiswa.getRowCount()>=1) {
                            int lastrow = tableNilai_Mahasiswa.getRowCount();
                            lastid = Integer.valueOf(tableNilai_Mahasiswa.getValueAt(lastrow-1, 0).toString())+1;
                        }else{
                            System.out.println("ERROR IN ID CREATION");
                        }

                        dbNilai_Mahasiswa.add_data_nilai_mahasiswa(new modelNilai_Mahasiswa(
                    
                                lastid,
                                jTextField_nim_readonly.getText(),
                                String.valueOf(jComboBox_nama.getSelectedItem()),
                                jTextField_nomor_mk_readonly.getText(),
                                String.valueOf(jComboBox_nama_mk.getSelectedItem()),
                                
                                kehadiran,tugas1,tugas2,tugas3,uts,uas,
                                
                                Integer.valueOf(thn_angkatan),
                                
                                total_nilai_absen,
                                total_nilai_tugas,
                                total_nilai_uts,
                                total_nilai_uas,
                                total_nilai_akhir,
                                indeks,
                                keterangan)
                        
                        );

                        refreshdataNilai_Mahasiswa();
                        clean_textfield_Nilai_Mahasiswa();
                        enable_all_menu_button();
                        enable_all_tools_button();
                        
                        
                        jTable.requestFocus();
                        
                    }else if (jButton_edit.getModel().isEnabled()){ //if edit
                        int kehadiran = Integer.valueOf(jFormattedTextField_kehadiran.getText());
                        int tugas1    = Integer.valueOf(jFormattedTextField_tugas1.getText());
                        int tugas2    = Integer.valueOf(jFormattedTextField_tugas2.getText());
                        int tugas3    = Integer.valueOf(jFormattedTextField_tugas3.getText());
                        int uts    = Integer.valueOf(jFormattedTextField_uts.getText());
                        int uas    = Integer.valueOf(jFormattedTextField_uas.getText());
                        int thn_angkatan = jFormattedTextField_angkatan.getYear();
                        calculate_nilai(kehadiran, tugas1, tugas2, tugas3, uts, uas);

                        dbNilai_Mahasiswa.update_row_nilai_mahasiswa(new modelNilai_Mahasiswa(
                                
                                Integer.valueOf(tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 0).toString()),
                                jTextField_nim_readonly.getText(),
                                String.valueOf(jComboBox_nama.getSelectedItem()),
                                jTextField_nomor_mk_readonly.getText(),
                                String.valueOf(jComboBox_nama_mk.getSelectedItem()),
                                
                                kehadiran,tugas1,tugas2,tugas3,uts,uas,
                                
                                Integer.valueOf(thn_angkatan),
                                total_nilai_absen,
                                total_nilai_tugas,
                                total_nilai_uts,
                                total_nilai_uas,
                                total_nilai_akhir,
                                indeks,
                                keterangan),
                                
                                tableNilai_Mahasiswa.getValueAt(row_nilai_mahasiswa, 0).toString());
                                
    

                        refreshdataNilai_Mahasiswa();
                        clean_textfield_Nilai_Mahasiswa();
                        enable_all_menu_button();
                        enable_all_tools_button();
                        
                    }else{
                        System.out.println("no button pressed");
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Nilai Hanya dari 0-100");
                }
        
            }else{
                JOptionPane.showMessageDialog(null, "Kehadiran Hanya dari 0-14");
        }
        
        
        
    }//GEN-LAST:event_jButton_save_nilai_mahasiswaActionPerformed

    //</editor-fold>
    
    
    
    
    private void jComboBox_nama_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_nama_mkActionPerformed
        update_textfield_matakuliah();
    }//GEN-LAST:event_jComboBox_nama_mkActionPerformed

    private void jComboBox_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_namaActionPerformed
        update_textfield_mahasiswa();
    }//GEN-LAST:event_jComboBox_namaActionPerformed

    
    
    
    
    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // show field on click

        enable_all_menu_button();
        if(evt.getClickCount()==1){

            switch (Option){
                    case 1:   try {
                        showfieldMahasiswa();
                    } catch (ParseException ex) {
                        Logger.getLogger(frameMainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                break;
                case 2: showfieldMatakuliah();
                break;
                case 3: showfieldNilai_Mahasiswa();
                break;
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frameMainMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_cancel_mahasiswa;
    private javax.swing.JButton jButton_cancel_matakuliah;
    private javax.swing.JButton jButton_cancel_nilai_mahasiswa;
    private javax.swing.JButton jButton_data_mahasiswa;
    private javax.swing.JButton jButton_data_matakuliah;
    private javax.swing.JButton jButton_data_nilai;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_edit;
    private javax.swing.JButton jButton_logout;
    private javax.swing.JButton jButton_save_mahasiswa;
    private javax.swing.JButton jButton_save_matakuliah;
    private javax.swing.JButton jButton_save_nilai_mahasiswa;
    private javax.swing.JButton jButton_search;
    private javax.swing.JButton jButton_simulasi;
    private javax.swing.JButton jButton_to_pdf;
    private javax.swing.JButton jButton_utama;
    private javax.swing.JComboBox<String> jComboBox_nama;
    private javax.swing.JComboBox<String> jComboBox_nama_mk;
    private com.toedter.calendar.JYearChooser jFormattedTextField_angkatan;
    private javax.swing.JFormattedTextField jFormattedTextField_kehadiran;
    private com.toedter.calendar.JDateChooser jFormattedTextField_tanggal_lahir;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas1;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas2;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas3;
    private javax.swing.JFormattedTextField jFormattedTextField_uas;
    private javax.swing.JFormattedTextField jFormattedTextField_uts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_logo_small;
    private javax.swing.JLabel jLabel_refresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_edit;
    private javax.swing.JPanel jPanel_header;
    private javax.swing.JPanel jPanel_logo;
    private javax.swing.JPanel jPanel_main;
    private javax.swing.JPanel jPanel_side_navigation;
    private javax.swing.JPanel jPanel_table;
    private javax.swing.JPanel jPanel_tools;
    private javax.swing.JPanel jPanel_update_mahasiswa;
    private javax.swing.JPanel jPanel_update_matakuliah;
    private javax.swing.JPanel jPanel_update_nilai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_table;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jTextArea_alamat;
    private javax.swing.JTextField jTextField_nama;
    private javax.swing.JTextField jTextField_nama_mk;
    private javax.swing.JTextField jTextField_nim;
    private javax.swing.JTextField jTextField_nim_readonly;
    private javax.swing.JTextField jTextField_nomor_mk;
    private javax.swing.JTextField jTextField_nomor_mk_readonly;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JTextField jTextField_tempat_lahir;
    // End of variables declaration//GEN-END:variables
}
