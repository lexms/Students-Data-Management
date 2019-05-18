/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.databases;

import io.models.modelMatakuliah;
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
public class databaseMatakuliah {
    public ArrayList<modelMatakuliah> show_database_matakuliah()
    {
        ArrayList<modelMatakuliah> list = new ArrayList<>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from matakuliah";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next())
                {
                    list.add(new modelMatakuliah(rs.getString("nomor_mk"),rs.getString("nama_mk")));
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
    public void add_data_matakuliah(modelMatakuliah m)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="insert into matakuliah values "
                    + "('"+m.getNomor_mk()
                    +"','"+m.getNama_mk()+"')";
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
    
    public void delete_row_matakuliah(String nomor_mk)
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn=database.getConnection();
            stmt=conn.createStatement();
            String sql="delete from matakuliah where nomor_mk='"+nomor_mk+"'";
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
    
    //<editor-fold>
    public modelMatakuliah select_row_matakuliah(String nomor_mk){
        modelMatakuliah m=null;
        Connection conn=null;
        Statement stmt=null;
        
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from matakuliah where nomor_mk= '"+nomor_mk+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                m = new modelMatakuliah(rs.getString("nomor_mk"),rs.getString("nama_mk"));
            }
            else
                m = null;
                
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
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
        
        return m;
    }
    //</editor-fold>
    
    public void update_row_matakuliah(modelMatakuliah m, String id){
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="update matakuliah "
                +"set nomor_mk = '"+m.getNomor_mk()+"',"
                +" nama_mk = '"+m.getNama_mk()+"'"
                +" where nomor_mk = '"+id+"'";
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
    
    public ArrayList<modelMatakuliah> filter_database_matakuliah(String keyword)
    {
        ArrayList<modelMatakuliah> list = new ArrayList<modelMatakuliah>();
        Connection conn=null;
        Statement stmt=null;
        try
        {
            conn = database.getConnection();
            stmt=conn.createStatement();
            String sql;
            sql="select * from matakuliah where nama_mk like '%"+keyword+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                list.add(new modelMatakuliah(rs.getString("nomor_mk"),rs.getString("nama_mk")));
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
