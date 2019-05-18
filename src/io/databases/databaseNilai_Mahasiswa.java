/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.databases;

import io.models.modelNilai_Mahasiswa;
import io.services.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class databaseNilai_Mahasiswa {
    public ArrayList<modelNilai_Mahasiswa> show_database_nilai_mahasiswa()
    {
        ArrayList<modelNilai_Mahasiswa> list = new ArrayList<>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from nilai_mahasiswa";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next())
                {
                    list.add(new modelNilai_Mahasiswa(
                            rs.getInt("id_nilai_mahasiswa"),
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("nomor_mk"),
                            rs.getString("nama_mk"),
                            rs.getInt("kehadiran"),
                            rs.getInt("nilai_tugas1"),        
                            rs.getInt("nilai_tugas2"),
                            rs.getInt("nilai_tugas3"),
                            rs.getInt("nilai_uts"),
                            rs.getInt("nilai_uas"),
                            rs.getInt("tahun_angkatan"),
                            rs.getDouble("total_nilai_absen"),
                            rs.getDouble("total_nilai_tugas"),
                            rs.getDouble("total_nilai_uts"),
                            rs.getDouble("total_nilai_uas"),
                            rs.getDouble("total_nilai_akhir"),
                            rs.getString("indeks"),
                            rs.getString("keterangan")
                            
                    ));
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                stmt.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
        return list;
    }

    public void add_data_nilai_mahasiswa(modelNilai_Mahasiswa m)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="insert into nilai_mahasiswa values "
                    + "('"+m.getId_nilai_mahasiswa()
                    + "','"+m.getTnilai_nim()
                    + "','"+m.getTnilai_nama()
                    + "','"+m.getTnilai_nomor_mk()
                    + "','"+m.getTnilai_nama_mk()
                    + "','"+m.getKehadiran()
                    + "','"+m.getNilai_tugas1()
                    + "','"+m.getNilai_tugas2()
                    + "','"+m.getNilai_tugas3()
                    + "','"+m.getNilai_uts()
                    + "','"+m.getNilai_uas()
                    + "','"+m.getTahun_angkatan()
                    + "','"+m.getTotal_nilai_absen()
                    + "','"+m.getTotal_nilai_tugas()
                    + "','"+m.getTotal_nilai_uts()
                    + "','"+m.getTotal_nilai_uas()
                    + "','"+m.getTotal_nilai_akhir()
                    + "','"+m.getIndeks()
                    + "','"+m.getKeterangan()+"')";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
        finally
        {
            try
            {
                stmt.close();
            }
            catch(Exception e)
            {}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    
    public void delete_row_nilai_mahasiswa(int id)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="delete from nilai_mahasiswa where id_nilai_mahasiswa='"+id+"'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                stmt.close();
            }
            catch(Exception e)
            {}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
    }
    
    
    public void update_row_nilai_mahasiswa(modelNilai_Mahasiswa m, String id){
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="update nilai_mahasiswa "
                +" set kehadiran = '"+m.getKehadiran()+"',"
                +" nilai_tugas1 = '"+m.getNilai_tugas1()+"',"
                +" nilai_tugas2 = '"+m.getNilai_tugas2()+"',"
                +" nilai_tugas3 = '"+m.getNilai_tugas3()+"',"
                +" nilai_uts = '"+m.getNilai_uts()+"',"
                +" nilai_uas = '"+m.getNilai_uas()+"',"                    
                +" tahun_angkatan = '"+m.getTahun_angkatan()+"',"
                +" total_nilai_absen = '"+m.getTotal_nilai_absen()+"',"   
                +" total_nilai_tugas = '"+m.getTotal_nilai_tugas()+"',"
                +" total_nilai_uts = '"+m.getTotal_nilai_uts()+"',"
                +" total_nilai_uas = '"+m.getTotal_nilai_uas()+"',"
                +" total_nilai_akhir = '"+m.getTotal_nilai_akhir()+"',"
                +" indeks = '"+m.getIndeks()+"',"
                +" keterangan = '"+m.getKeterangan()+"'"    
                +" where id_nilai_mahasiswa = "+id+"";
            
            stmt.executeUpdate(sql);
            
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                stmt.close();
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
    
    
    public ArrayList<modelNilai_Mahasiswa> filter_database_nilai_mahasiswa(String keyword)
    {
        ArrayList<modelNilai_Mahasiswa> list = new ArrayList<modelNilai_Mahasiswa>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from nilai_mahasiswa where nama like '%"+keyword+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                list.add(new modelNilai_Mahasiswa(
                            rs.getInt("id_nilai_mahasiswa"),
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("nomor_mk"),
                            rs.getString("nama_mk"),
                            rs.getInt("kehadiran"),
                            rs.getInt("nilai_tugas1"),        
                            rs.getInt("nilai_tugas2"),
                            rs.getInt("nilai_tugas3"),
                            rs.getInt("nilai_uts"),
                            rs.getInt("nilai_uas"),
                            rs.getInt("tahun_angkatan"),
                            rs.getDouble("total_nilai_absen"),
                            rs.getDouble("total_nilai_tugas"),
                            rs.getDouble("total_nilai_uts"),
                            rs.getDouble("total_nilai_uas"),
                            rs.getDouble("total_nilai_akhir"),
                            rs.getString("indeks"),
                            rs.getString("keterangan")
                    ));
            }
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                stmt.close();
            }
            catch(Exception e){}
            try
            {
                conn.close();
            }
            catch(Exception e)
            {}
        }
        return list;
    }
    
    
    
}
