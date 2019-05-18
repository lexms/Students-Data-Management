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
public class modelMahasiswa {
    private String nim, nama, tempat_lahir, alamat;
    private Date tanggal_lahir;

    

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    
    public modelMahasiswa(String nim, String nama, String tempat_lahir, Date tanggal_lahir, String alamat ) {
        this.nim = nim;
        this.nama = nama;
        this.tempat_lahir = tempat_lahir;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
    }
    
}
