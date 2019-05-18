/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.frames;
import io.models.modelSimulasi_Nilai_Mahasiswa;
import io.table_models.tmSimulasi_Nilai_Mahasiswa;
import io.databases.databaseSimulasi_Nilai_Mahasiswa;
import io.services.database;
import io.services.exportPDF;
import io.services.navigation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class frameSimulation extends javax.swing.JFrame {
    public databaseSimulasi_Nilai_Mahasiswa dbsimulasi_nilai_mahasiswa = new databaseSimulasi_Nilai_Mahasiswa();
    public tmSimulasi_Nilai_Mahasiswa tablesimulasi_nilai_mahasiswa = new tmSimulasi_Nilai_Mahasiswa();
    /**
     * Creates new form frameSimulation
     */
    public frameSimulation() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        showTablesimulasi_nilai_mahasiswa();
        add_comboitem_matakuliah();
        disable_all_tools_button();
    }
 
// <editor-fold defaultstate="collapsed" desc="Simulasi">       
    public void enable_all_tools_button(){
        jButton_add.setEnabled(true);
        jButton_delete.setEnabled(true);
        jButton_edit.setEnabled(true);
        jButton_to_pdf.setEnabled(true);
        jButton_back.setEnabled(true);
    }
    public void disable_all_tools_button(){
        jButton_add.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_edit.setEnabled(false);
        jButton_to_pdf.setEnabled(false);
        jButton_back.setEnabled(false);
    }
    
    
    public void clean_textfield_simulasi_nilai_mahasiswa(){
        
        jComboBox_nama_mk.setSelectedIndex(0);
        jTextField_nomor_mk_readonly.setText("");
        
        jFormattedTextField_persen_kehadiran.setText("");
        jFormattedTextField_persen_tugas.setText("");
        jFormattedTextField_persen_uts.setText("");
        jFormattedTextField_persen_uas.setText("");
        
        jFormattedTextField_kehadiran.setText("");
        jFormattedTextField_tugas1.setText("");
        jFormattedTextField_tugas2.setText("");
        jFormattedTextField_tugas3.setText("");
        jFormattedTextField_uts.setText("");
        jFormattedTextField_uas.setText("");
    }
    
    public void toggle_nonactive_textfield_simulasi_nilai_mahasiswa(){
        
        jComboBox_nama_mk.setEnabled(false);
        
        jFormattedTextField_persen_kehadiran.setEnabled(false);
        jFormattedTextField_persen_tugas.setEnabled(false);
        jFormattedTextField_persen_uts.setEnabled(false);
        jFormattedTextField_persen_uas.setEnabled(false);
        
        jFormattedTextField_kehadiran.setEnabled(false);
        jFormattedTextField_tugas1.setEnabled(false);
        jFormattedTextField_tugas2.setEnabled(false);
        jFormattedTextField_tugas3.setEnabled(false);
        jFormattedTextField_uts.setEnabled(false);
        jFormattedTextField_uas.setEnabled(false);
        ;
    }
    
    public void toggle_active_textfield_simulasi_nilai_mahasiswa(){
        
        jComboBox_nama_mk.setEnabled(true);
        
        jFormattedTextField_persen_kehadiran.setEnabled(true);
        jFormattedTextField_persen_tugas.setEnabled(true);
        jFormattedTextField_persen_uts.setEnabled(true);
        jFormattedTextField_persen_uas.setEnabled(true);
        
        jFormattedTextField_kehadiran.setEnabled(true);
        jFormattedTextField_tugas1.setEnabled(true);
        jFormattedTextField_tugas2.setEnabled(true);
        jFormattedTextField_tugas3.setEnabled(true);
        jFormattedTextField_uts.setEnabled(true);
        jFormattedTextField_uas.setEnabled(true);
        
    }
    
    int row_simulasi_nilai_mahasiswa = 0 ;
    public void showfieldsimulasi_nilai_mahasiswa(){
        row_simulasi_nilai_mahasiswa = jTable.getSelectedRow();

        jTextField_nomor_mk_readonly.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 1).toString());
        jComboBox_nama_mk.setSelectedItem(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 2).toString());
        
        jFormattedTextField_persen_kehadiran.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 3).toString());
        jFormattedTextField_persen_tugas.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 4).toString());
        jFormattedTextField_persen_uts.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 5).toString());
        jFormattedTextField_persen_uas.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 6).toString());
        
        jFormattedTextField_kehadiran.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 7).toString());
        jFormattedTextField_tugas1.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 8).toString());
        jFormattedTextField_tugas2.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 9).toString());
        jFormattedTextField_tugas3.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 10).toString());
        jFormattedTextField_uts.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 11).toString());
        jFormattedTextField_uas.setText(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 12).toString());

        
        jButton_save_simulasi_nilai_mahasiswa.setEnabled(false);
        jButton_cancel_simulasi_nilai_mahasiswa.setEnabled(false);
        
        enable_all_tools_button();
        toggle_nonactive_textfield_simulasi_nilai_mahasiswa();
        
        
    }
    
    public void showTablesimulasi_nilai_mahasiswa()
    {
        tablesimulasi_nilai_mahasiswa.setData(dbsimulasi_nilai_mahasiswa.show_database_simulasi_nilai_mahasiswa());
        jTable.setModel(tablesimulasi_nilai_mahasiswa);
    }
    
    public void refreshdatasimulasi_nilai_mahasiswa()
    {
        tablesimulasi_nilai_mahasiswa.setData(dbsimulasi_nilai_mahasiswa.show_database_simulasi_nilai_mahasiswa());
        tablesimulasi_nilai_mahasiswa.fireTableDataChanged();
        jTable.changeSelection(0,0,false,false);
    }
    
    public void call_addrowsimulasi_nilai_mahasiswa(){
        clean_textfield_simulasi_nilai_mahasiswa();
        toggle_active_textfield_simulasi_nilai_mahasiswa();
        jComboBox_nama_mk.requestFocus();
        jButton_save_simulasi_nilai_mahasiswa.setEnabled(true);
        jButton_cancel_simulasi_nilai_mahasiswa.setEnabled(true);  
    }
    
    public void call_deleterowsimulasi_nilai_mahasiswa(){
        try
        {
            int row = jTable.getSelectedRow();
            int id = (Integer)tablesimulasi_nilai_mahasiswa.getValueAt(row,0);

            String nama_mk = (String)tablesimulasi_nilai_mahasiswa.getValueAt(row, 2);
            Object[] pilihan={"Ya","Tidak"};
            int answer = JOptionPane.showOptionDialog(
                    null,"Anda yakin data simulasi nilai mahasiswa dengan nama mk \""+nama_mk+"\" akan dihapus ?",
                    "Peringatan",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,pilihan,pilihan[0]);
            if(answer==0)
            {
                dbsimulasi_nilai_mahasiswa.delete_row_simulasi_nilai_mahasiswa(id);
                clean_textfield_simulasi_nilai_mahasiswa();
                refreshdatasimulasi_nilai_mahasiswa();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"Pilih data yang akan dihapus !!!");        
        }
    }
    
    public void call_editrowsimulasi_nilai_mahasiswa(){
        toggle_active_textfield_simulasi_nilai_mahasiswa();
        jComboBox_nama_mk.requestFocus();
        jButton_save_simulasi_nilai_mahasiswa.setEnabled(true);
        jButton_cancel_simulasi_nilai_mahasiswa.setEnabled(true);  
    }
    
    
    
    
//    double persen_absen, persen_tugas, persen_uts, persen_uas;
    
    double total_nilai_absen, total_nilai_tugas, total_nilai_uts, total_nilai_uas, total_nilai_akhir;
    String indeks, keterangan;
    
    public void calculate_nilai(double persen_absen, double persen_tugas, double persen_uts, double persen_uas, int kehadiran,int tugas1,int tugas2,int tugas3,int uts,int uas){

        
        total_nilai_absen = (((kehadiran/14.0)*100.0*persen_absen)/100.0);
        
        total_nilai_tugas = ((tugas1+tugas2+tugas3)/3.0*(persen_tugas/100.0));
        
        total_nilai_uts   = uts * persen_uts/100.0;
        
        total_nilai_uas   = uas * persen_uas/100.0;
        
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

// </editor-fold>
    
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
        jButton_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel_tools = new javax.swing.JPanel();
        jLabel_refresh = new javax.swing.JLabel();
        jButton_add = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_edit = new javax.swing.JButton();
        jButton_to_pdf = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jComboBox_nama_mk = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_nomor_mk_readonly = new javax.swing.JTextField();
        jFormattedTextField_kehadiran = new javax.swing.JFormattedTextField();
        jFormattedTextField_uas = new javax.swing.JFormattedTextField();
        jFormattedTextField_uts = new javax.swing.JFormattedTextField();
        jFormattedTextField_tugas3 = new javax.swing.JFormattedTextField();
        jFormattedTextField_tugas2 = new javax.swing.JFormattedTextField();
        jFormattedTextField_tugas1 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField_persen_kehadiran = new javax.swing.JFormattedTextField();
        jFormattedTextField_persen_uas = new javax.swing.JFormattedTextField();
        jFormattedTextField_persen_uts = new javax.swing.JFormattedTextField();
        jFormattedTextField_persen_tugas = new javax.swing.JFormattedTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton_save_simulasi_nilai_mahasiswa = new javax.swing.JButton();
        jButton_cancel_simulasi_nilai_mahasiswa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1239, 760));

        jPanel_logo.setBackground(new java.awt.Color(64, 181, 198));

        jLabel_logo_small.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/logo-small.png"))); // NOI18N

        jButton_back.setBackground(new java.awt.Color(64, 181, 198));
        jButton_back.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_back.setText("BACK");
        jButton_back.setBorder(null);
        jButton_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_logoLayout = new javax.swing.GroupLayout(jPanel_logo);
        jPanel_logo.setLayout(jPanel_logoLayout);
        jPanel_logoLayout.setHorizontalGroup(
            jPanel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_logoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel_logo_small, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_back)
                .addGap(41, 41, 41))
        );
        jPanel_logoLayout.setVerticalGroup(
            jPanel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_logoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_logo_small)
                .addGap(34, 34, 34))
            .addGroup(jPanel_logoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton_back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_tools.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_tools.setPreferredSize(new java.awt.Dimension(1199, 52));

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
                .addGap(47, 47, 47))
        );
        jPanel_toolsLayout.setVerticalGroup(
            jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_toolsLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton_add)
                        .addComponent(jButton_delete)
                        .addComponent(jButton_edit)
                        .addComponent(jButton_to_pdf))
                    .addComponent(jLabel_refresh))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jComboBox_nama_mk.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox_nama_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Nama Mata Kuliah" }));
        jComboBox_nama_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_nama_mkActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(132, 135, 148));
        jLabel12.setText("Nama Mata Kuliah");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(132, 135, 148));
        jLabel13.setText("Nomor Mata Kuliah");

        jTextField_nomor_mk_readonly.setEditable(false);
        jTextField_nomor_mk_readonly.setBackground(new java.awt.Color(255, 255, 255));

        jFormattedTextField_kehadiran.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_kehadiran.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_kehadiran.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_kehadiran.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_uas.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_uas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_uas.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_uas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_uts.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_uts.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_uts.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_uts.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_tugas3.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_tugas3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas3.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_tugas3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_tugas2.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_tugas2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas2.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_tugas2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jFormattedTextField_tugas1.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_tugas1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_tugas1.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_tugas1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(132, 135, 148));
        jLabel15.setText("Tugas 1");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(132, 135, 148));
        jLabel14.setText("Kehadiran");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(132, 135, 148));
        jLabel16.setText("Tugas 2");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(132, 135, 148));
        jLabel17.setText("Tugas 3");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(132, 135, 148));
        jLabel18.setText("UTS");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(132, 135, 148));
        jLabel19.setText("UAS");

        jFormattedTextField_persen_kehadiran.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_persen_kehadiran.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_persen_kehadiran.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_persen_kehadiran.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jFormattedTextField_persen_uas.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_persen_uas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_persen_uas.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_persen_uas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jFormattedTextField_persen_uts.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_persen_uts.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_persen_uts.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_persen_uts.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jFormattedTextField_persen_tugas.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextField_persen_tugas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 200, 220), 1, true));
        jFormattedTextField_persen_tugas.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField_persen_tugas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(132, 135, 148));
        jLabel30.setText("% Tugas");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(132, 135, 148));
        jLabel31.setText("% Kehadiran");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(132, 135, 148));
        jLabel34.setText("% UTS");

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(132, 135, 148));
        jLabel35.setText("% UAS");

        jButton_save_simulasi_nilai_mahasiswa.setBackground(new java.awt.Color(69, 200, 220));
        jButton_save_simulasi_nilai_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_save_simulasi_nilai_mahasiswa.setText("Save");
        jButton_save_simulasi_nilai_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_save_simulasi_nilai_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_save_simulasi_nilai_mahasiswaActionPerformed(evt);
            }
        });

        jButton_cancel_simulasi_nilai_mahasiswa.setBackground(new java.awt.Color(220, 69, 69));
        jButton_cancel_simulasi_nilai_mahasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel_simulasi_nilai_mahasiswa.setText("Cancel");
        jButton_cancel_simulasi_nilai_mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cancel_simulasi_nilai_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancel_simulasi_nilai_mahasiswaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35))
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField_persen_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFormattedTextField_persen_uts)
                                    .addComponent(jFormattedTextField_persen_tugas)
                                    .addComponent(jFormattedTextField_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextField_tugas3)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jFormattedTextField_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTextField_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(jButton_save_simulasi_nilai_mahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_cancel_simulasi_nilai_mahasiswa))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel19))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_nomor_mk_readonly)
                            .addComponent(jComboBox_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField_nomor_mk_readonly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jFormattedTextField_persen_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jFormattedTextField_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19))
                                    .addComponent(jLabel17))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextField_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_cancel_simulasi_nilai_mahasiswa)
                                .addComponent(jButton_save_simulasi_nilai_mahasiswa))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_tools, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_tools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_backActionPerformed

        navigation.showMainMenu(this);
    }//GEN-LAST:event_jButton_backActionPerformed

    private void jLabel_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_refreshMouseClicked
        refreshdatasimulasi_nilai_mahasiswa();
    }//GEN-LAST:event_jLabel_refreshMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        call_addrowsimulasi_nilai_mahasiswa();
        jButton_edit.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_back.setEnabled(false);

    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        call_deleterowsimulasi_nilai_mahasiswa();
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editActionPerformed
        call_editrowsimulasi_nilai_mahasiswa();
        jButton_add.setEnabled(false);
        jButton_delete.setEnabled(false);
        jButton_back.setEnabled(false);

    }//GEN-LAST:event_jButton_editActionPerformed

    private void jButton_to_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_to_pdfActionPerformed
        try {
            exportPDF.exportSimulasi_Nilai_Mahasiswa();
            JOptionPane.showMessageDialog(null, "Export Berhasil disimpan di folder kemahasiswaan_10116347_10116370");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Export Error: " +ex.getMessage());
        }
    }//GEN-LAST:event_jButton_to_pdfActionPerformed

    private void jComboBox_nama_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_nama_mkActionPerformed
        update_textfield_matakuliah();
    }//GEN-LAST:event_jComboBox_nama_mkActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        showfieldsimulasi_nilai_mahasiswa();
    }//GEN-LAST:event_jTableMouseClicked

    private void jButton_save_simulasi_nilai_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_save_simulasi_nilai_mahasiswaActionPerformed
        // TODO add your handling code here:
        if (   
                jComboBox_nama_mk.getSelectedIndex()==0
                || (jFormattedTextField_persen_kehadiran.getText().isEmpty()) 
                || (jFormattedTextField_persen_tugas.getText().isEmpty())
                || (jFormattedTextField_persen_uts.getText().isEmpty())
                || (jFormattedTextField_persen_uas.getText().isEmpty())
                || (jFormattedTextField_kehadiran.getText().isEmpty()) 
                || (jFormattedTextField_tugas1.getText().isEmpty())
                || (jFormattedTextField_tugas2.getText().isEmpty())
                || (jFormattedTextField_tugas3.getText().isEmpty())
                || (jFormattedTextField_uts.getText().isEmpty())
                || (jFormattedTextField_uas.getText().isEmpty())
                
            ) {
            JOptionPane.showMessageDialog(null, "anda belum nama mata kuliah atau data lainnya masih kosong, silahkan dilengkapi");
            jComboBox_nama_mk.requestFocus();
            
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
                        double persen_absen = Double.valueOf(jFormattedTextField_persen_kehadiran.getText());
                        double persen_tugas = Double.valueOf(jFormattedTextField_persen_tugas.getText());
                        double persen_uts = Double.valueOf(jFormattedTextField_persen_uts.getText());
                        double persen_uas = Double.valueOf(jFormattedTextField_persen_uas.getText());

                        int kehadiran = Integer.valueOf(jFormattedTextField_kehadiran.getText());
                        int tugas1    = Integer.valueOf(jFormattedTextField_tugas1.getText());
                        int tugas2    = Integer.valueOf(jFormattedTextField_tugas2.getText());
                        int tugas3    = Integer.valueOf(jFormattedTextField_tugas3.getText());
                        int uts    = Integer.valueOf(jFormattedTextField_uts.getText());
                        int uas    = Integer.valueOf(jFormattedTextField_uas.getText());
                        
                        calculate_nilai(persen_absen, persen_tugas, persen_uts, persen_uas, kehadiran, tugas1, tugas2, tugas3, uts, uas);
                        
                        
                        int lastid = 1;
                        if (tablesimulasi_nilai_mahasiswa.getRowCount()==0) {
                            lastid = 1;
                        }else if (tablesimulasi_nilai_mahasiswa.getRowCount()>=1) {
                            int lastrow = tablesimulasi_nilai_mahasiswa.getRowCount();
          
                            lastid = Integer.valueOf(tablesimulasi_nilai_mahasiswa.getValueAt(lastrow-1, 0).toString())+1;

                        }else{
                            System.out.println("ERROR IN ID CREATION");
                        }

                        dbsimulasi_nilai_mahasiswa.add_data_simulasi_nilai_mahasiswa(new modelSimulasi_Nilai_Mahasiswa(
                                
                                lastid,
                                jTextField_nomor_mk_readonly.getText(),
                                String.valueOf(jComboBox_nama_mk.getSelectedItem()),
                                
                                persen_absen, persen_tugas, persen_uts, persen_uas,
                                
                                kehadiran,tugas1,tugas2,tugas3,uts,uas,
                                total_nilai_absen,
                                total_nilai_tugas,
                                total_nilai_uts,
                                total_nilai_uas,
                                total_nilai_akhir,
                                indeks,
                                keterangan));

                        refreshdatasimulasi_nilai_mahasiswa();
                        clean_textfield_simulasi_nilai_mahasiswa();
                        enable_all_tools_button();
                        toggle_nonactive_textfield_simulasi_nilai_mahasiswa();
                        
                        
                    }else if (jButton_edit.getModel().isEnabled()){ //if edit
                        double persen_absen = Double.valueOf(jFormattedTextField_persen_kehadiran.getText());
                        double persen_tugas = Double.valueOf(jFormattedTextField_persen_tugas.getText());
                        double persen_uts = Double.valueOf(jFormattedTextField_persen_uts.getText());
                        double persen_uas = Double.valueOf(jFormattedTextField_persen_uas.getText());

                        int kehadiran = Integer.valueOf(jFormattedTextField_kehadiran.getText());
                        int tugas1    = Integer.valueOf(jFormattedTextField_tugas1.getText());
                        int tugas2    = Integer.valueOf(jFormattedTextField_tugas2.getText());
                        int tugas3    = Integer.valueOf(jFormattedTextField_tugas3.getText());
                        int uts    = Integer.valueOf(jFormattedTextField_uts.getText());
                        int uas    = Integer.valueOf(jFormattedTextField_uas.getText());
                        
                        calculate_nilai(persen_absen, persen_tugas, persen_uts, persen_uas, kehadiran, tugas1, tugas2, tugas3, uts, uas);
                        
                        dbsimulasi_nilai_mahasiswa.update_row_simulasi_nilai_mahasiswa(new modelSimulasi_Nilai_Mahasiswa(
                                
                            Integer.valueOf(tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 0).toString()),
                            jTextField_nomor_mk_readonly.getText(),
                            String.valueOf(jComboBox_nama_mk.getSelectedItem()),

                            persen_absen, persen_tugas, persen_uts, persen_uas,

                            kehadiran,tugas1,tugas2,tugas3,uts,uas,
                            total_nilai_absen,
                            total_nilai_tugas,
                            total_nilai_uts,
                            total_nilai_uas,
                            total_nilai_akhir,
                            indeks,
                            keterangan),

                            tablesimulasi_nilai_mahasiswa.getValueAt(row_simulasi_nilai_mahasiswa, 0).toString());

                        refreshdatasimulasi_nilai_mahasiswa();
                        clean_textfield_simulasi_nilai_mahasiswa();
                        enable_all_tools_button();
                        toggle_nonactive_textfield_simulasi_nilai_mahasiswa();
                        
                    }else{
                        System.out.println("no button pressed");
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Nilai Hanya dari 0-100");
                }
        
            }else{
                JOptionPane.showMessageDialog(null, "Kehadiran Hanya dari 0-14");
        }
    }//GEN-LAST:event_jButton_save_simulasi_nilai_mahasiswaActionPerformed

    private void jButton_cancel_simulasi_nilai_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancel_simulasi_nilai_mahasiswaActionPerformed
        clean_textfield_simulasi_nilai_mahasiswa();
        enable_all_tools_button();
    }//GEN-LAST:event_jButton_cancel_simulasi_nilai_mahasiswaActionPerformed

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
//            java.util.logging.Logger.getLogger(frameSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frameSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frameSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frameSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frameSimulation().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_back;
    private javax.swing.JButton jButton_cancel_simulasi_nilai_mahasiswa;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_edit;
    private javax.swing.JButton jButton_save_simulasi_nilai_mahasiswa;
    private javax.swing.JButton jButton_to_pdf;
    private javax.swing.JComboBox<String> jComboBox_nama_mk;
    private javax.swing.JFormattedTextField jFormattedTextField_kehadiran;
    private javax.swing.JFormattedTextField jFormattedTextField_persen_kehadiran;
    private javax.swing.JFormattedTextField jFormattedTextField_persen_tugas;
    private javax.swing.JFormattedTextField jFormattedTextField_persen_uas;
    private javax.swing.JFormattedTextField jFormattedTextField_persen_uts;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas1;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas2;
    private javax.swing.JFormattedTextField jFormattedTextField_tugas3;
    private javax.swing.JFormattedTextField jFormattedTextField_uas;
    private javax.swing.JFormattedTextField jFormattedTextField_uts;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel_logo_small;
    private javax.swing.JLabel jLabel_refresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_logo;
    private javax.swing.JPanel jPanel_tools;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField_nomor_mk_readonly;
    // End of variables declaration//GEN-END:variables
}
