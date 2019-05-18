/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.databases;

import io.models.modelMahasiswa;
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
public class databaseMahasiswa {
    public ArrayList<modelMahasiswa> show_database_mahasiswa()
    {
        ArrayList<modelMahasiswa> list = new ArrayList<>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from mahasiswa";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next())
                {
                    list.add(new modelMahasiswa(rs.getString("nim"),rs.getString("nama"),
                            rs.getString("tempat_lahir"),rs.getDate("tanggal_lahir"),rs.getString("alamat")));
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
    
    public void add_data_mahasiswa(modelMahasiswa m)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="insert into mahasiswa values "
                    + "('"+m.getNim()
                    + "','"+m.getNama()
                    + "','"+m.getTempat_lahir()
                    + "','"+m.getTanggal_lahir()
                    + "','"+m.getAlamat()+"')";
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
    
    public void delete_row_mahasiswa(String nim)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="delete from mahasiswa where nim='"+nim+"'";
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
    
    public void update_row_mahasiswa(modelMahasiswa m, String id){
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="update mahasiswa "
                +" set nim = '"+m.getNim()+"',"
                +" nama = '"+m.getNama()+"',"
                +" tempat_lahir = '"+m.getTempat_lahir()+"',"
                +" tanggal_lahir = '"+m.getTanggal_lahir()+"',"
                +" alamat = '"+m.getAlamat()+"'"
                +" where nim = '"+id+"'";
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
    
    public ArrayList<modelMahasiswa> filter_database_mahasiswa(String keyword)
    {
        ArrayList<modelMahasiswa> list = new ArrayList<modelMahasiswa>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from mahasiswa where nama like '%"+keyword+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                list.add(new modelMahasiswa(rs.getString("nim"),rs.getString("nama"),
                            rs.getString("tempat_lahir"),rs.getDate("tanggal_lahir"),rs.getString("alamat")));
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
