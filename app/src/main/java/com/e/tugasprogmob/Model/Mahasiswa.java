package com.e.tugasprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("id")
    private int id;
    @SerializedName("nim")
    private String nim;
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("Foto")
    private String Foto;
    /*public Mahasiswa(String nim, String nama, String gelar, String email){
        this.setNim(nim);
        this.setNama(nama);
        this.setGelar(gelar);
        this.setEmail(email);
    }*/

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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
