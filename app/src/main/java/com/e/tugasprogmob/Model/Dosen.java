package com.e.tugasprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Dosen {
    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nidn")
    private String nidn;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("email")
    private String email;

    @SerializedName("gelar")
    private String gelar;

    @SerializedName("foto")
    private String foto;
    public Dosen(String nidn, String nama, String alamat, String email){
        this.setNidn(nidn);
        this.setNama(nama);
        this.setAlamat(alamat);
        this.setEmail(email);
    }
    public Dosen(String nidn, String nama, String gelar, String email, String alamat){
        this.setNidn(nidn);
        this.setNama(nama);
        this.setGelar(gelar);
        this.setEmail(email);
        this.setAlamat(alamat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
