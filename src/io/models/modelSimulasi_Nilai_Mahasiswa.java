/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.models;

import java.sql.Date;


/**
 *
 * @author User
 */
public class modelSimulasi_Nilai_Mahasiswa {
    private int id_simulasi_nilai_mahasiswa;
    private String Tnilai_nomor_mk,Tnilai_nama_mk;
    private double persen_absen,persen_tugas,persen_uts,persen_uas;
    
    private int kehadiran,nilai_tugas1,nilai_tugas2,nilai_tugas3,nilai_uts,nilai_uas;
    
    private double total_nilai_absen, total_nilai_tugas, total_nilai_uts, total_nilai_uas, total_nilai_akhir;
    private String indeks, keterangan;

    public int getId_simulasi_nilai_mahasiswa() {
        return id_simulasi_nilai_mahasiswa;
    }

    public void setId_simulasi_nilai_mahasiswa(int id_simulasi_nilai_mahasiswa) {
        this.id_simulasi_nilai_mahasiswa = id_simulasi_nilai_mahasiswa;
    }

    public String getTnilai_nomor_mk() {
        return Tnilai_nomor_mk;
    }

    public void setTnilai_nomor_mk(String Tnilai_nomor_mk) {
        this.Tnilai_nomor_mk = Tnilai_nomor_mk;
    }

    public String getTnilai_nama_mk() {
        return Tnilai_nama_mk;
    }

    public void setTnilai_nama_mk(String Tnilai_nama_mk) {
        this.Tnilai_nama_mk = Tnilai_nama_mk;
    }

    public double getPersen_absen() {
        return persen_absen;
    }

    public void setPersen_absen(double persen_absen) {
        this.persen_absen = persen_absen;
    }

    public double getPersen_tugas() {
        return persen_tugas;
    }

    public void setPersen_tugas(double persen_tugas) {
        this.persen_tugas = persen_tugas;
    }

    public double getPersen_uts() {
        return persen_uts;
    }

    public void setPersen_uts(double persen_uts) {
        this.persen_uts = persen_uts;
    }

    public double getPersen_uas() {
        return persen_uas;
    }

    public void setPersen_uas(double persen_uas) {
        this.persen_uas = persen_uas;
    }

    public int getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(int kehadiran) {
        this.kehadiran = kehadiran;
    }

    public int getNilai_tugas1() {
        return nilai_tugas1;
    }

    public void setNilai_tugas1(int nilai_tugas1) {
        this.nilai_tugas1 = nilai_tugas1;
    }

    public int getNilai_tugas2() {
        return nilai_tugas2;
    }

    public void setNilai_tugas2(int nilai_tugas2) {
        this.nilai_tugas2 = nilai_tugas2;
    }

    public int getNilai_tugas3() {
        return nilai_tugas3;
    }

    public void setNilai_tugas3(int nilai_tugas3) {
        this.nilai_tugas3 = nilai_tugas3;
    }

    public int getNilai_uts() {
        return nilai_uts;
    }

    public void setNilai_uts(int nilai_uts) {
        this.nilai_uts = nilai_uts;
    }

    public int getNilai_uas() {
        return nilai_uas;
    }

    public void setNilai_uas(int nilai_uas) {
        this.nilai_uas = nilai_uas;
    }

    public double getTotal_nilai_absen() {
        return total_nilai_absen;
    }

    public void setTotal_nilai_absen(double total_nilai_absen) {
        this.total_nilai_absen = total_nilai_absen;
    }

    public double getTotal_nilai_tugas() {
        return total_nilai_tugas;
    }

    public void setTotal_nilai_tugas(double total_nilai_tugas) {
        this.total_nilai_tugas = total_nilai_tugas;
    }

    public double getTotal_nilai_uts() {
        return total_nilai_uts;
    }

    public void setTotal_nilai_uts(double total_nilai_uts) {
        this.total_nilai_uts = total_nilai_uts;
    }

    public double getTotal_nilai_uas() {
        return total_nilai_uas;
    }

    public void setTotal_nilai_uas(double total_nilai_uas) {
        this.total_nilai_uas = total_nilai_uas;
    }

    public double getTotal_nilai_akhir() {
        return total_nilai_akhir;
    }

    public void setTotal_nilai_akhir(double total_nilai_akhir) {
        this.total_nilai_akhir = total_nilai_akhir;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public modelSimulasi_Nilai_Mahasiswa(int id_simulasi_nilai_mahasiswa, String Tnilai_nomor_mk, String Tnilai_nama_mk, double persen_absen, double persen_tugas, double persen_uts, double persen_uas, int kehadiran, int nilai_tugas1, int nilai_tugas2, int nilai_tugas3, int nilai_uts, int nilai_uas, double total_nilai_absen, double total_nilai_tugas, double total_nilai_uts, double total_nilai_uas, double total_nilai_akhir, String indeks, String keterangan) {
        this.id_simulasi_nilai_mahasiswa = id_simulasi_nilai_mahasiswa;
        this.Tnilai_nomor_mk = Tnilai_nomor_mk;
        this.Tnilai_nama_mk = Tnilai_nama_mk;
        this.persen_absen = persen_absen;
        this.persen_tugas = persen_tugas;
        this.persen_uts = persen_uts;
        this.persen_uas = persen_uas;
        this.kehadiran = kehadiran;
        this.nilai_tugas1 = nilai_tugas1;
        this.nilai_tugas2 = nilai_tugas2;
        this.nilai_tugas3 = nilai_tugas3;
        this.nilai_uts = nilai_uts;
        this.nilai_uas = nilai_uas;
        this.total_nilai_absen = total_nilai_absen;
        this.total_nilai_tugas = total_nilai_tugas;
        this.total_nilai_uts = total_nilai_uts;
        this.total_nilai_uas = total_nilai_uas;
        this.total_nilai_akhir = total_nilai_akhir;
        this.indeks = indeks;
        this.keterangan = keterangan;
    }

    
    
    

    
    
    
    
    
}
