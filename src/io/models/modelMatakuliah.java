/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.models;

/**
 *
 * @author User
 */
public class modelMatakuliah {
    private String nomor_mk;
    private String nama_mk;

    public String getNomor_mk() {
        return nomor_mk;
    }

    public void setNomor_mk(String nomor_mk) {
        this.nomor_mk = nomor_mk;
    }

    public String getNama_mk() {
        return nama_mk;
    }

    public void setNama_mk(String nama_mk) {
        this.nama_mk = nama_mk;
    }

    public modelMatakuliah(String nomor_mk, String nama_mk) {
        this.nomor_mk = nomor_mk;
        this.nama_mk = nama_mk;
    }
}
