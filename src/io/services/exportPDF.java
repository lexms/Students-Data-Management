/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.services;

import java.io.FileOutputStream;

import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
/**
 *
 * @author AlexanderMS
 */
public class exportPDF {
    
    public static void exportMahasiswa() throws Exception{

        Connection conn = database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM mahasiswa");
        Document pdf_report = new Document();
        PdfWriter.getInstance(pdf_report, new FileOutputStream("reportMahasiswa.pdf"));
        pdf_report.open();            

        PdfPTable report_table = new PdfPTable(5);

        PdfPCell table_cell;

        while (query_set.next()) {                
            String nim=query_set.getString("nim");
            table_cell=new PdfPCell(new Phrase(nim));
            report_table.addCell(table_cell);
            String nama=query_set.getString("nama");
            table_cell=new PdfPCell(new Phrase(nama));
            report_table.addCell(table_cell);
            String tempat_lahir=query_set.getString("tempat_lahir");
            table_cell=new PdfPCell(new Phrase(tempat_lahir));
            report_table.addCell(table_cell);
            String tanggal_lahir=query_set.getString("tanggal_lahir");
            table_cell=new PdfPCell(new Phrase(tanggal_lahir));
            report_table.addCell(table_cell);
            String alamat=query_set.getString("alamat");
            table_cell=new PdfPCell(new Phrase(alamat));
            report_table.addCell(table_cell);
        }

        pdf_report.add(report_table);                       
        pdf_report.close();


        query_set.close();
        stmt.close(); 
        conn.close();               
                
    }
    
    public static void exportMatakuliah() throws Exception{
        Connection conn = database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM matakuliah");
        Document pdf_report = new Document();
        PdfWriter.getInstance(pdf_report, new FileOutputStream("reportMatakuliah.pdf"));
        pdf_report.open();            

        PdfPTable report_table = new PdfPTable(2);
        PdfPCell table_cell;

        while (rs.next()) {                
            String nomor_mk=rs.getString("nomor_mk");
            table_cell=new PdfPCell(new Phrase(nomor_mk));
            report_table.addCell(table_cell);
            String nama_mk=rs.getString("nama_mk");
            table_cell=new PdfPCell(new Phrase(nama_mk));
            report_table.addCell(table_cell);
        }

        pdf_report.add(report_table);                       
        pdf_report.close();

        rs.close();
        stmt.close(); 
        conn.close();             
    }
    
    
    public static void exportNilai() throws Exception{
   
        Connection conn = database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT "
                + "id_nilai_mahasiswa, "
                + "tahun_angkatan, "
                + "nim, "
                + "nama, "
                + "nomor_mk, "
                + "nama_mk, "
                + "kehadiran, "
                + "nilai_tugas1, "
                + "nilai_tugas2, "
                + "nilai_tugas3, "
                + "nilai_uts, "
                + "nilai_uas, "
                + "total_nilai_akhir, "
                + "indeks, "
                + "keterangan "
                + "FROM nilai_mahasiswa");
        Document pdf_report = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(pdf_report, new FileOutputStream("reportNilaiMahasiswa.pdf"));
        pdf_report.open();            

        PdfPTable report_table = new PdfPTable(15);

        PdfPCell table_cell;

        while (query_set.next()) {

            String id_nilai_mahasiswa=query_set.getString("id_nilai_mahasiswa");
            table_cell=new PdfPCell(new Phrase(id_nilai_mahasiswa));
            report_table.addCell(table_cell);

            String tahun_angkatan=query_set.getString("tahun_angkatan");
            table_cell=new PdfPCell(new Phrase(tahun_angkatan));
            report_table.addCell(table_cell);

            String nim=query_set.getString("nim");
            table_cell=new PdfPCell(new Phrase(nim));
            report_table.addCell(table_cell);

            String nama=query_set.getString("nama");
            table_cell=new PdfPCell(new Phrase(nama));
            report_table.addCell(table_cell);

            String nomor_mk=query_set.getString("nomor_mk");
            table_cell=new PdfPCell(new Phrase(nomor_mk));
            report_table.addCell(table_cell);

            String nama_mk=query_set.getString("nama_mk");
            table_cell=new PdfPCell(new Phrase(nama_mk));
            report_table.addCell(table_cell);

            String kehadiran=query_set.getString("kehadiran");
            table_cell=new PdfPCell(new Phrase(kehadiran));
            report_table.addCell(table_cell);

            String nilai_tugas1=query_set.getString("nilai_tugas1");
            table_cell=new PdfPCell(new Phrase(nilai_tugas1));
            report_table.addCell(table_cell);

            String nilai_tugas2=query_set.getString("nilai_tugas2");
            table_cell=new PdfPCell(new Phrase(nilai_tugas2));
            report_table.addCell(table_cell);

            String nilai_tugas3=query_set.getString("nilai_tugas3");
            table_cell=new PdfPCell(new Phrase(nilai_tugas3));
            report_table.addCell(table_cell);

            String nilai_uts=query_set.getString("nilai_uts");
            table_cell=new PdfPCell(new Phrase(nilai_uts));
            report_table.addCell(table_cell);

            String nilai_uas=query_set.getString("nilai_uas");
            table_cell=new PdfPCell(new Phrase(nilai_uas));
            report_table.addCell(table_cell);

            String total_nilai_akhir=query_set.getString("total_nilai_akhir");
            table_cell=new PdfPCell(new Phrase(total_nilai_akhir));
            report_table.addCell(table_cell);

            String indeks=query_set.getString("indeks");
            table_cell=new PdfPCell(new Phrase(indeks));
            report_table.addCell(table_cell);

            String keterangan=query_set.getString("keterangan");
            table_cell=new PdfPCell(new Phrase(keterangan));
            report_table.addCell(table_cell);
        }



        pdf_report.add(report_table);                       
        pdf_report.close();


        query_set.close();
        stmt.close(); 
        conn.close();               
                
    }
    
    
    public static void exportSimulasi_Nilai_Mahasiswa() throws Exception{

        Connection conn = database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT "
                + "nomor_mk, "
                + "nama_mk, "
                + "kehadiran, "
                + "nilai_tugas1, "
                + "nilai_tugas2, "
                + "nilai_tugas3, "
                + "nilai_uts, "
                + "nilai_uas, "
                + "total_nilai_akhir, "
                + "indeks, "
                + "keterangan "
                + "FROM simulasi_nilai_mahasiswa");
        Document pdf_report = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(pdf_report, new FileOutputStream("reportSimulasiNilaiMahasiswa.pdf"));
        pdf_report.open();            

        PdfPTable report_table = new PdfPTable(11);

        PdfPCell table_cell;

        while (query_set.next()) {


            String nomor_mk=query_set.getString("nomor_mk");
            table_cell=new PdfPCell(new Phrase(nomor_mk));
            report_table.addCell(table_cell);

            String nama_mk=query_set.getString("nama_mk");
            table_cell=new PdfPCell(new Phrase(nama_mk));
            report_table.addCell(table_cell);

            String kehadiran=query_set.getString("kehadiran");
            table_cell=new PdfPCell(new Phrase(kehadiran));
            report_table.addCell(table_cell);

            String nilai_tugas1=query_set.getString("nilai_tugas1");
            table_cell=new PdfPCell(new Phrase(nilai_tugas1));
            report_table.addCell(table_cell);

            String nilai_tugas2=query_set.getString("nilai_tugas2");
            table_cell=new PdfPCell(new Phrase(nilai_tugas2));
            report_table.addCell(table_cell);

            String nilai_tugas3=query_set.getString("nilai_tugas3");
            table_cell=new PdfPCell(new Phrase(nilai_tugas3));
            report_table.addCell(table_cell);

            String nilai_uts=query_set.getString("nilai_uts");
            table_cell=new PdfPCell(new Phrase(nilai_uts));
            report_table.addCell(table_cell);

            String nilai_uas=query_set.getString("nilai_uas");
            table_cell=new PdfPCell(new Phrase(nilai_uas));
            report_table.addCell(table_cell);

            String total_nilai_akhir=query_set.getString("total_nilai_akhir");
            table_cell=new PdfPCell(new Phrase(total_nilai_akhir));
            report_table.addCell(table_cell);

            String indeks=query_set.getString("indeks");
            table_cell=new PdfPCell(new Phrase(indeks));
            report_table.addCell(table_cell);

            String keterangan=query_set.getString("keterangan");
            table_cell=new PdfPCell(new Phrase(keterangan));
            report_table.addCell(table_cell);

        }

        pdf_report.add(report_table);                       
        pdf_report.close();


        query_set.close();
        stmt.close(); 
        conn.close();               
                
    }
}
